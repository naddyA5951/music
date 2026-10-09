package com.example.audio

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.sin

object AudioSynthesizer {

    private const val SAMPLE_RATE = 44100
    private const val NUM_CHANNELS = 2
    private const val BITS_PER_SAMPLE = 16

    suspend fun getOrCreateAudioFile(
        context: Context,
        trackId: String,
        genre: String,
        durationSeconds: Int = 180,
        onProgress: (Float) -> Unit = {}
    ): File = withContext(Dispatchers.IO) {
        val audioDir = File(context.filesDir, "audio_cache").apply { mkdirs() }
        val targetFile = File(audioDir, "track_$trackId.wav")

        if (targetFile.exists() && targetFile.length() > 44) {
            onProgress(1f)
            return@withContext targetFile
        }

        // Generate synthetic song
        generateWavAudio(targetFile, genre, durationSeconds, onProgress)
        targetFile
    }

    suspend fun generateDownloadFile(
        context: Context,
        trackId: String,
        genre: String,
        durationSeconds: Int = 180,
        onProgress: (Float) -> Unit = {}
    ): File = withContext(Dispatchers.IO) {
        val downloadDir = File(context.filesDir, "audio_downloads").apply { mkdirs() }
        val targetFile = File(downloadDir, "download_$trackId.wav")

        if (targetFile.exists() && targetFile.length() > 44) {
            onProgress(1f)
            return@withContext targetFile
        }

        generateWavAudio(targetFile, genre, durationSeconds, onProgress)
        targetFile
    }

    private fun generateWavAudio(
        file: File,
        genre: String,
        durationSeconds: Int,
        onProgress: (Float) -> Unit
    ) {
        val totalSamples = SAMPLE_RATE * durationSeconds
        val bytesPerSample = NUM_CHANNELS * (BITS_PER_SAMPLE / 8)
        val dataChunkSize = totalSamples * bytesPerSample

        FileOutputStream(file).use { fos ->
            // Write placeholder WAV header (44 bytes)
            fos.write(ByteArray(44))

            // Chunk generation
            val chunkSizeSamples = 44100 // 1 second at a time
            val buffer = ByteBuffer.allocate(chunkSizeSamples * bytesPerSample).order(ByteOrder.LITTLE_ENDIAN)

            // Musical chords / scale notes depending on genre
            val (bpm, chordProgressions) = getMusicalParams(genre)
            val beatsPerSecond = bpm / 60.0
            val samplesPerBeat = (SAMPLE_RATE / beatsPerSecond).toInt()

            var currentSample = 0
            while (currentSample < totalSamples) {
                buffer.clear()
                val samplesToWrite = minOf(chunkSizeSamples, totalSamples - currentSample)

                for (s in 0 until samplesToWrite) {
                    val globalSample = currentSample + s
                    val beatIndex = (globalSample / samplesPerBeat) % 16
                    val beatFraction = (globalSample % samplesPerBeat).toDouble() / samplesPerBeat

                    // Current chord root
                    val chord = chordProgressions[(beatIndex / 4) % chordProgressions.size]

                    // Synthesize multi-timbre waveform: Bass + Chords + Lead Melody
                    val bassFreq = chord[0] * 0.5
                    val midFreq1 = chord[0]
                    val midFreq2 = chord[1]
                    val midFreq3 = chord[2]

                    // Lead melody based on arpeggio
                    val arpIndex = (beatIndex * 2 + (beatFraction * 2).toInt()) % chord.size
                    val leadFreq = chord[arpIndex] * 2.0

                    // Time in seconds
                    val t = globalSample.toDouble() / SAMPLE_RATE

                    // Envelopes
                    val beatDecay = (1.0 - beatFraction * 0.6).coerceIn(0.2, 1.0)
                    val bassWave = sin(2.0 * Math.PI * bassFreq * t) * 0.35 * beatDecay
                    val padWave = (
                        sin(2.0 * Math.PI * midFreq1 * t) +
                        sin(2.0 * Math.PI * midFreq2 * t) +
                        sin(2.0 * Math.PI * midFreq3 * t)
                    ) * 0.12

                    val leadWave = sin(2.0 * Math.PI * leadFreq * t) * 0.22 * (1.0 - (beatFraction % 0.5))

                    // Mix and apply slight stereo separation
                    val mixMono = (bassWave + padWave + leadWave).coerceIn(-1.0, 1.0)
                    val left = (mixMono * 0.95 * 32767).toInt().toShort()
                    val right = (mixMono * 1.0 * 32767).toInt().toShort()

                    buffer.putShort(left)
                    buffer.putShort(right)
                }

                fos.write(buffer.array(), 0, samplesToWrite * bytesPerSample)
                currentSample += samplesToWrite
                onProgress((currentSample.toFloat() / totalSamples).coerceIn(0f, 1f))
            }
        }

        // Fill in real WAV header length
        RandomAccessFile(file, "rw").use { raf ->
            writeWavHeader(raf, dataChunkSize)
        }
    }

    private fun getMusicalParams(genre: String): Pair<Double, List<List<Double>>> {
        return when (genre.lowercase()) {
            "synthwave" -> Pair(
                118.0,
                listOf(
                    listOf(130.81, 164.81, 196.00), // C3
                    listOf(110.00, 138.59, 164.81), // A2
                    listOf(87.31, 110.00, 130.81),  // F2
                    listOf(98.00, 123.47, 146.83)   // G2
                )
            )
            "lo-fi", "lofi" -> Pair(
                82.0,
                listOf(
                    listOf(146.83, 174.61, 220.00), // Dm7
                    listOf(196.00, 246.94, 293.66), // G7
                    listOf(130.81, 164.81, 196.00), // Cmaj7
                    listOf(110.00, 130.81, 164.81)  // Am7
                )
            )
            "acoustic", "indie" -> Pair(
                95.0,
                listOf(
                    listOf(196.00, 246.94, 293.66), // G
                    listOf(146.83, 185.00, 220.00), // D
                    listOf(164.81, 196.00, 246.94), // Em
                    listOf(130.81, 164.81, 196.00)  // C
                )
            )
            "pop" -> Pair(
                120.0,
                listOf(
                    listOf(130.81, 164.81, 196.00), // C
                    listOf(98.00, 123.47, 146.83),  // G
                    listOf(110.00, 130.81, 164.81), // Am
                    listOf(87.31, 110.00, 130.81)   // F
                )
            )
            "hip-hop", "rap", "trap" -> Pair(
                135.0,
                listOf(
                    listOf(82.41, 123.47, 164.81),  // E minor
                    listOf(73.42, 110.00, 146.83),  // D minor
                    listOf(65.41, 98.00, 130.81),   // C major
                    listOf(61.74, 92.50, 123.47)    // B minor
                )
            )
            "r&b", "soul" -> Pair(
                88.0,
                listOf(
                    listOf(138.59, 174.61, 207.65), // Db maj7
                    listOf(123.47, 155.56, 185.00), // B maj7
                    listOf(110.00, 138.59, 164.81), // Bb m7
                    listOf(103.83, 130.81, 155.56)  // Ab m7
                )
            )
            "rock", "metal" -> Pair(
                132.0,
                listOf(
                    listOf(82.41, 123.47, 164.81),  // E5 Power chord
                    listOf(98.00, 146.83, 196.00),  // G5
                    listOf(110.00, 164.81, 220.00), // A5
                    listOf(87.31, 130.81, 174.61)   // F5
                )
            )
            "edm", "dance", "house" -> Pair(
                128.0,
                listOf(
                    listOf(130.81, 164.81, 196.00), // C
                    listOf(110.00, 130.81, 164.81), // Am
                    listOf(87.31, 110.00, 130.81),  // F
                    listOf(98.00, 123.47, 146.83)   // G
                )
            )
            "ambient", "meditation" -> Pair(
                60.0,
                listOf(
                    listOf(130.81, 196.00, 261.63), // C fifth
                    listOf(110.00, 164.81, 220.00), // A fifth
                    listOf(87.31, 130.81, 174.61),  // F fifth
                    listOf(98.00, 146.83, 196.00)   // G fifth
                )
            )
            "latin", "reggaeton" -> Pair(
                96.0,
                listOf(
                    listOf(110.00, 130.81, 164.81), // Am
                    listOf(87.31, 110.00, 130.81),  // F
                    listOf(130.81, 164.81, 196.00), // C
                    listOf(98.00, 123.47, 146.83)   // G
                )
            )
            "pakistani", "pakistani pop", "coke studio", "sufi", "qawwali", "ghazal", "punjabi" -> Pair(
                92.0, // Tabla / Dholak and Harmonium acoustic frequencies (Raag Bhairavi / Yaman)
                listOf(
                    listOf(146.83, 174.61, 220.00), // D minor
                    listOf(130.81, 164.81, 196.00), // C major
                    listOf(116.54, 146.83, 174.61), // Bb major
                    listOf(110.00, 138.59, 164.81)  // A minor
                )
            )
            "classical" -> Pair(
                72.0,
                listOf(
                    listOf(146.83, 174.61, 220.00), // Dm
                    listOf(174.61, 220.00, 261.63), // F
                    listOf(164.81, 207.65, 246.94), // E dim
                    listOf(110.00, 138.59, 164.81)  // A
                )
            )
            "jazz" -> Pair(
                105.0,
                listOf(
                    listOf(174.61, 220.00, 261.63), // F maj 7
                    listOf(146.83, 174.61, 220.00), // Dm 7
                    listOf(196.00, 233.08, 293.66), // Gm 7
                    listOf(130.81, 164.81, 196.00)  // C 7
                )
            )
            else -> Pair( // Cyber / Electronic
                124.0,
                listOf(
                    listOf(110.00, 130.81, 164.81), // Am
                    listOf(87.31, 110.00, 130.81),  // F
                    listOf(130.81, 164.81, 196.00), // C
                    listOf(98.00, 123.47, 146.83)   // G
                )
            )
        }
    }

    private fun writeWavHeader(raf: RandomAccessFile, dataChunkSize: Int) {
        raf.seek(0)
        val byteRate = SAMPLE_RATE * NUM_CHANNELS * (BITS_PER_SAMPLE / 8)
        val blockAlign = NUM_CHANNELS * (BITS_PER_SAMPLE / 8)

        raf.writeBytes("RIFF")
        raf.writeInt(Integer.reverseBytes(dataChunkSize + 36))
        raf.writeBytes("WAVE")
        raf.writeBytes("fmt ")
        raf.writeInt(Integer.reverseBytes(16)) // Subchunk1Size (16 for PCM)
        raf.writeShort(java.lang.Short.reverseBytes(1.toShort()).toInt()) // AudioFormat 1 = PCM
        raf.writeShort(java.lang.Short.reverseBytes(NUM_CHANNELS.toShort()).toInt())
        raf.writeInt(Integer.reverseBytes(SAMPLE_RATE))
        raf.writeInt(Integer.reverseBytes(byteRate))
        raf.writeShort(java.lang.Short.reverseBytes(blockAlign.toShort()).toInt())
        raf.writeShort(java.lang.Short.reverseBytes(BITS_PER_SAMPLE.toShort()).toInt())
        raf.writeBytes("data")
        raf.writeInt(Integer.reverseBytes(dataChunkSize))
    }
}
