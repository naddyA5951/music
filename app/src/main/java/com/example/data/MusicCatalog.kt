package com.example.data

import com.example.model.LyricLine
import com.example.model.Track

object MusicCatalog {

    private val baseTracks: List<Track> = listOf(
        // Track 1 - Synthwave
        Track(
            id = "track_1",
            title = "Midnight Velocity",
            artist = "Neon Horizon",
            album = "Cyber Retrowave 2099",
            durationMs = 192000L,
            genre = "Synthwave",
            coverGradientStart = 0xFF7C3AED,
            coverGradientEnd = 0xFFEC4899,
            iconCategory = "synth",
            bitrateKbps = 320,
            audioFormat = "Hi-Res FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Instrumental Intro - Neon Arpeggio]"),
                LyricLine(8000L, "City lights flicker across the rain-slick glass"),
                LyricLine(16000L, "Chasing shadows from memories of the past"),
                LyricLine(24000L, "Hold the wheel, accelerate into the night"),
                LyricLine(32000L, "We are chasing an electric violet light"),
                LyricLine(42000L, "Midnight velocity, burning through the wire"),
                LyricLine(50000L, "Every heartbeat echoes like a synthetic fire"),
                LyricLine(60000L, "Can you feel the pulse? (Can you feel it now?)"),
                LyricLine(70000L, "Nothing is holding us down"),
                LyricLine(80000L, "[Synthesizer Solo & Deep Bass Drop]"),
                LyricLine(100000L, "Miles unwind beneath the chrome highway sky"),
                LyricLine(112000L, "No destination, no questions asking why"),
                LyricLine(124000L, "Just the analog dream in a digital sea"),
                LyricLine(136000L, "Forever moving at midnight velocity"),
                LyricLine(150000L, "[Harmonic Outro - Synth Fade]")
            )
        ),

        // Track 2 - Lo-Fi
        Track(
            id = "track_2",
            title = "Rainy Kyoto Cafe",
            artist = "Sakura Beats",
            album = "Lofi Study Diaries",
            durationMs = 168000L,
            genre = "Lo-Fi",
            coverGradientStart = 0xFF3B82F6,
            coverGradientEnd = 0xFF10B981,
            iconCategory = "lofi",
            bitrateKbps = 320,
            audioFormat = "Master Quality 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Soft Vinyl Crackle & Rain on Windowpane]"),
                LyricLine(10000L, "Gentle drops falling on the cedar eaves"),
                LyricLine(20000L, "Steam rising slowly from warm green tea leaves"),
                LyricLine(32000L, "Lofi chords drift softly in the air"),
                LyricLine(44000L, "Pages turning, peaceful without a care"),
                LyricLine(58000L, "Listen to the quiet rhythm of the afternoon"),
                LyricLine(72000L, "Watching raindrops dance beneath the paper moon"),
                LyricLine(88000L, "[Rhodes Electric Piano Solo]"),
                LyricLine(104000L, "Time stands still inside this quiet room"),
                LyricLine(118000L, "Gentle melodies dispel the twilight gloom"),
                LyricLine(135000L, "Breathe in peace, exhale the stress away"),
                LyricLine(150000L, "[Warm Bass & Fading Rain]")
            )
        ),

        // Track 3 - Acoustic
        Track(
            id = "track_3",
            title = "Golden Hour Glow",
            artist = "Cedar & String",
            album = "Acoustic Whispers",
            durationMs = 184000L,
            genre = "Acoustic",
            coverGradientStart = 0xFFF59E0B,
            coverGradientEnd = 0xFFEF4444,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Direct DSD / Lossless",
            lyrics = listOf(
                LyricLine(0L, "[Fingerpicked Acoustic Guitar Prelude]"),
                LyricLine(12000L, "Golden amber spreading over autumn hills"),
                LyricLine(24000L, "A silent dusk where the restless river stills"),
                LyricLine(36000L, "Strum a wooden chord, sing an honest line"),
                LyricLine(48000L, "Everything feels sacred in the sunset wine"),
                LyricLine(62000L, "Here in the golden hour glow"),
                LyricLine(74000L, "Where the gentle breezes blow"),
                LyricLine(86000L, "You and me and strings vibrating true"),
                LyricLine(98000L, "All the universe reflects in you"),
                LyricLine(114000L, "[Melodic Harmonica & Harmonics]"),
                LyricLine(132000L, "Shadows lengthen, stars begin to gleam"),
                LyricLine(146000L, "Living inside an audiophile dream"),
                LyricLine(160000L, "[Acoustic Fadeout]")
            )
        ),

        // Track 4 - Electronic
        Track(
            id = "track_4",
            title = "Cybernetic Pulse",
            artist = "Aether Wave",
            album = "Neural Subsystems",
            durationMs = 210000L,
            genre = "Electronic",
            coverGradientStart = 0xFF06B6D4,
            coverGradientEnd = 0xFF8B5CF6,
            iconCategory = "cyber",
            bitrateKbps = 320,
            audioFormat = "Hi-Res FLAC 96kHz/24-bit",
            lyrics = listOf(
                LyricLine(0L, "[System Online: Frequency Calibration]"),
                LyricLine(14000L, "Data streams surging through optical veins"),
                LyricLine(28000L, "Synthetic dopamine cleanses human pains"),
                LyricLine(42000L, "Frequency modulating in 8D surround sound"),
                LyricLine(56000L, "Sub-bass vibration shaking the ground"),
                LyricLine(70000L, "[Bass Drop - Cybernetic Resonance]"),
                LyricLine(88000L, "We are connected, node by node"),
                LyricLine(102000L, "Decrypting beauty in the rhythm code"),
                LyricLine(120000L, "Pure clarity, audiophile precision"),
                LyricLine(136000L, "A crystalline acoustic vision"),
                LyricLine(154000L, "[Phase Filter Sweep & Glitch Arpeggios]"),
                LyricLine(175000L, "Dissolving in the waves of digital light"),
                LyricLine(192000L, "[Terminal Power Down]")
            )
        ),

        // Track 5 - Classical
        Track(
            id = "track_5",
            title = "Nocturne in C# Minor",
            artist = "Frederic V. Encores",
            album = "Grand Steinway Concert",
            durationMs = 240000L,
            genre = "Classical",
            coverGradientStart = 0xFF6366F1,
            coverGradientEnd = 0xFF0F172A,
            iconCategory = "piano",
            bitrateKbps = 1411,
            audioFormat = "Lossless FLAC 1411 kbps",
            lyrics = listOf(
                LyricLine(0L, "[Lento con gran espressione - Piano Solo]"),
                LyricLine(20000L, "[Soft Melancholy Arpeggios in the Left Hand]"),
                LyricLine(45000L, "[Poignant Melody Ascends with Delicate Touch]"),
                LyricLine(75000L, "[Dynamic Swell - Forte Emotional Crescendo]"),
                LyricLine(110000L, "[Appassionato Theme with Rapid Trills]"),
                LyricLine(150000L, "[Dolce Cantabile - Gentle Reflection]"),
                LyricLine(190000L, "[Cascading Scales in Triplets]"),
                LyricLine(220000L, "[Pianissimo Final Chord Resolving in Peace]")
            )
        ),

        // Track 6 - Jazz
        Track(
            id = "track_6",
            title = "Blue Velvet Lounge",
            artist = "Miles Quintet",
            album = "Late Night Manhattan",
            durationMs = 195000L,
            genre = "Jazz",
            coverGradientStart = 0xFFD946EF,
            coverGradientEnd = 0xFF3B82F6,
            iconCategory = "sax",
            bitrateKbps = 320,
            audioFormat = "Studio Master 24-bit",
            lyrics = listOf(
                LyricLine(0L, "[Muted Trumpet & Upright Walking Bass]"),
                LyricLine(15000L, "Smoke swirls in the dim spotlight glow"),
                LyricLine(30000L, "The saxophone whispers deep and slow"),
                LyricLine(45000L, "Bourbon clinks in crystal across the bar"),
                LyricLine(60000L, "Someone's reminiscing about a shooting star"),
                LyricLine(78000L, "In the blue velvet lounge tonight"),
                LyricLine(92000L, "Where every broken heart feels right"),
                LyricLine(110000L, "[Tenor Saxophone Virtuoso Solo]"),
                LyricLine(138000L, "Syncopated rhythm, timeless grace"),
                LyricLine(154000L, "No worry can invade this intimate space"),
                LyricLine(172000L, "[Muted Brass Harmonizing to Finale]")
            )
        ),

        // Track 7 - Indie
        Track(
            id = "track_7",
            title = "Echoes of the Night",
            artist = "The Velvet Echo",
            album = "Starlight Transmission",
            durationMs = 186000L,
            genre = "Indie",
            coverGradientStart = 0xFF14B8A6,
            coverGradientEnd = 0xFF84CC16,
            iconCategory = "rock",
            bitrateKbps = 320,
            audioFormat = "320 kbps High Fidelity",
            lyrics = listOf(
                LyricLine(0L, "[Indie Drum Beat & Jangle Guitar Riff]"),
                LyricLine(14000L, "Walked through empty avenue memories"),
                LyricLine(28000L, "Listening to nocturnal symphonies"),
                LyricLine(42000L, "All the dreams we traded for tomorrow"),
                LyricLine(56000L, "Turn into songs that drown out sorrow"),
                LyricLine(70000L, "Hear the echoes of the night"),
                LyricLine(84000L, "Shining with incandescent light"),
                LyricLine(100000L, "[Electric Guitar Hook & Chorus]"),
                LyricLine(120000L, "We never let the music fade"),
                LyricLine(135000L, "In the secret sanctuary we made"),
                LyricLine(155000L, "Sing along until the dawn arrives"),
                LyricLine(170000L, "[Driving Rhythm & Outro]")
            )
        ),

        // Track 8 - Pop
        Track(
            id = "track_8",
            title = "Solar Flare",
            artist = "Maya & The Sun",
            album = "Ultraviolet Euphoria",
            durationMs = 198000L,
            genre = "Pop",
            coverGradientStart = 0xFFFF7043,
            coverGradientEnd = 0xFFFFCA28,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Mastered for Audiophile 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Sparkling Pop Synths & Four-on-the-Floor Kick]"),
                LyricLine(10000L, "Woke up with the summer heat inside my chest"),
                LyricLine(22000L, "Leaving behind every doubt and every test"),
                LyricLine(34000L, "Colors getting brighter than they've ever been"),
                LyricLine(46000L, "Like a solar flare shining on my skin!"),
                LyricLine(58000L, "Ooh, watch the golden sparks ignite"),
                LyricLine(70000L, "Dancing straight into the velvet night"),
                LyricLine(82000L, "[Euphoric Vocal Chops & Brass Hook]"),
                LyricLine(100000L, "Nothing can stop the frequency we found"),
                LyricLine(114000L, "Spinning around, lifting off the ground"),
                LyricLine(130000L, "Solar flare! Burning through the sky!"),
                LyricLine(145000L, "We were born to fly!"),
                LyricLine(165000L, "[Joyful Pop Climax & Outro]")
            )
        ),

        // Track 9 - Hip-Hop
        Track(
            id = "track_9",
            title = "Tokyo Drift 3AM",
            artist = "Kenji Flow",
            album = "Shibuya Neon Chronicles",
            durationMs = 175000L,
            genre = "Hip-Hop",
            coverGradientStart = 0xFFEF4444,
            coverGradientEnd = 0xFF18181B,
            iconCategory = "hiphop",
            bitrateKbps = 320,
            audioFormat = "Bass Boosted Studio Master",
            lyrics = listOf(
                LyricLine(0L, "[Distorted 808 Bass Slide & Hi-Hat Rolls]"),
                LyricLine(9000L, "Midnight in Shibuya, headlights on the asphalt"),
                LyricLine(18000L, "Every turn sharp, never looking for a fault"),
                LyricLine(28000L, "Twin turbo spooling under neon street lamps"),
                LyricLine(38000L, "Writing my legacy like legendary champs"),
                LyricLine(48000L, "Three AM, clock spinning around the dial"),
                LyricLine(58000L, "Stacking up the bars in an undisputed style"),
                LyricLine(70000L, "[Sub-Bass 35Hz Drop & Snare Crack]"),
                LyricLine(88000L, "Drifting through the expressway corridor"),
                LyricLine(98000L, "Hungrier than ever, always wanting more"),
                LyricLine(110000L, "Engine revs echoing in the quiet city air"),
                LyricLine(124000L, "Speeding through the matrix without a single care"),
                LyricLine(14000L, "[808 Fadeout & Rain Sample]")
            )
        ),

        // Track 10 - R&B
        Track(
            id = "track_10",
            title = "Velvet Rain",
            artist = "Saffron Soul",
            album = "Silk & Mahogany",
            durationMs = 215000L,
            genre = "R&B",
            coverGradientStart = 0xFF9333EA,
            coverGradientEnd = 0xFFBE185D,
            iconCategory = "rnb",
            bitrateKbps = 320,
            audioFormat = "Hi-Res 24-bit/48kHz",
            lyrics = listOf(
                LyricLine(0L, "[Smooth Fender Rhodes & Finger Snaps]"),
                LyricLine(14000L, "Candle flickers against the mahogany door"),
                LyricLine(28000L, "You don't have to say a single word anymore"),
                LyricLine(42000L, "I hear your heartbeat playing like a symphony"),
                LyricLine(56000L, "Dripping like velvet rain all over me"),
                LyricLine(72000L, "Velvet rain, washing off the pain"),
                LyricLine(88000L, "Whisper my name, let the groove remain"),
                LyricLine(105000L, "[Silky Vocal Harmonies & Bass Run]"),
                LyricLine(125000L, "Hold me close before the midnight turns to blue"),
                LyricLine(142000L, "Nobody understands the depth like you"),
                LyricLine(160000L, "In the velvet rain... velvet rain..."),
                LyricLine(180000L, "[Lush Saxophone & Soulful Outro]")
            )
        ),

        // Track 11 - Rock
        Track(
            id = "track_11",
            title = "Thunderborn",
            artist = "Iron Eclipse",
            album = "Wrath of the Titans",
            durationMs = 205000L,
            genre = "Rock",
            coverGradientStart = 0xFFB91C1C,
            coverGradientEnd = 0xFF78350F,
            iconCategory = "rock",
            bitrateKbps = 320,
            audioFormat = "Audiophile Dynamic Range DR14",
            lyrics = listOf(
                LyricLine(0L, "[Heavy Overdriven Guitar Riff & Double Bass Drum]"),
                LyricLine(15000L, "Clouds gather dark upon the mountain crest"),
                LyricLine(30000L, "A raging fire burning inside the chest"),
                LyricLine(45000L, "Lightning tears the blackened sky in two"),
                LyricLine(60000L, "Born in the tempest, fierce and true!"),
                LyricLine(75000L, "We are Thunderborn! Standing tall!"),
                LyricLine(90000L, "Never back down, we will conquer all!"),
                LyricLine(105000L, "[Screaming Guitar Solo & Wah-Wah Pedal]"),
                LyricLine(135000L, "Roar with the thunder, strike with the flame"),
                LyricLine(150000L, "They will forever remember our name!"),
                LyricLine(168000L, "Thunderborn! Until the end of time!"),
                LyricLine(185000L, "[Crushing Final Chord & Cymbal Crash]")
            )
        ),

        // Track 12 - EDM
        Track(
            id = "track_12",
            title = "Starlight Festival",
            artist = "DJ Kairo x Nova",
            album = "Mainstage Anthem 2026",
            durationMs = 188000L,
            genre = "EDM",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFFF43F5E,
            iconCategory = "edm",
            bitrateKbps = 320,
            audioFormat = "Master 320kbps High Energy",
            lyrics = listOf(
                LyricLine(0L, "[128 BPM Pluck Arpeggio & Rising Snare]"),
                LyricLine(15000L, "Thousands of hands reaching for the stars"),
                LyricLine(30000L, "Laser beams cutting through electric bars"),
                LyricLine(45000L, "Build it up higher, don't let it drop yet"),
                LyricLine(58000L, "This is a festival we'll never forget!"),
                LyricLine(65000L, "Are you ready? (3, 2, 1, JUMP!)"),
                LyricLine(70000L, "[Massive Big Room Saw Leads & Festival Drop]"),
                LyricLine(95000L, "Feel the bass pounding inside your core"),
                LyricLine(110000L, "Starlight festival forevermore!"),
                LyricLine(130000L, "[Second Buildup - Vocal Melody Rising]"),
                LyricLine(145000L, "Light up the sky with pure laser sound!"),
                LyricLine(160000L, "[High Energy Finale & Confetti Cannon]")
            )
        ),

        // Track 13 - Ambient
        Track(
            id = "track_13",
            title = "Aurora Borealis",
            artist = "Celestial Dreams",
            album = "Cosmic Meditation",
            durationMs = 270000L,
            genre = "Ambient",
            coverGradientStart = 0xFF059669,
            coverGradientEnd = 0xFF0284C7,
            iconCategory = "ambient",
            bitrateKbps = 1411,
            audioFormat = "Lossless FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Ethereal Shimmering Pad & Tibetan Singing Bowl]"),
                LyricLine(25000L, "[Emerald Waves Ripple Across Polar Skies]"),
                LyricLine(55000L, "[Gentle Drone Harmonizes in 432 Hz Tuning]"),
                LyricLine(90000L, "[Weightless Peace Descends Upon the Mind]"),
                LyricLine(135000L, "[Crystal Chimes Ringing in Distant Starlight]"),
                LyricLine(180000L, "[Deep Breath - Inhaling Calm, Exhaling Tension]"),
                LyricLine(225000L, "[Slow Dissolve into Endless Cosmic Stillness]")
            )
        ),

        // Track 14 - Latin
        Track(
            id = "track_14",
            title = "Fuego en la Habana",
            artist = "Sol Latino",
            album = "Noches Caribeñas",
            durationMs = 182000L,
            genre = "Latin",
            coverGradientStart = 0xFFE11D48,
            coverGradientEnd = 0xFFF59E0B,
            iconCategory = "latin",
            bitrateKbps = 320,
            audioFormat = "Pristine Latin Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Bongos, Timbales & Spanish Nylon Guitar]"),
                LyricLine(12000L, "Baila conmigo bajo las palmeras del malecón"),
                LyricLine(24000L, "Siente el fuego latiendo en el corazón"),
                LyricLine(36000L, "La noche es joven, el ritmo nos llama ya"),
                LyricLine(48000L, "Nadie se detiene cuando suena la rumba en La Habana"),
                LyricLine(60000L, "¡Fuego! ¡Fuego en la piel!"),
                LyricLine(72000L, "Sabor caribeño dulce como la miel"),
                LyricLine(86000L, "[Brass Horn Section Riff & Conga Solo]"),
                LyricLine(105000L, "Gira la falda, que brille la luna llena"),
                LyricLine(120000L, "La música borra cualquier tristeza y pena"),
                LyricLine(135000L, "¡Viva la vida! ¡Viva el son!"),
                LyricLine(150000L, "[Festive Percussion & Trumpet Crescendo]")
            )
        ),

        // Track 15 - Pop
        Track(
            id = "track_15",
            title = "Electric Heartbeat",
            artist = "Nova Mirage",
            album = "Prism Reflections",
            durationMs = 190000L,
            genre = "Pop",
            coverGradientStart = 0xFFEC4899,
            coverGradientEnd = 0xFF8B5CF6,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Crystal Clear 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Upbeat Synth Bassline & Handclaps]"),
                LyricLine(12000L, "I heard your voice across a crowded room"),
                LyricLine(24000L, "Like a neon flower suddenly in bloom"),
                LyricLine(36000L, "Step closer now, don't let the moment pass"),
                LyricLine(48000L, "We're reflections in an electric looking glass"),
                LyricLine(60000L, "Listen to my electric heartbeat (thump thump)"),
                LyricLine(72000L, "Dancing to the groove out in the street"),
                LyricLine(86000L, "[Catchy Synth Whistle Hook]"),
                LyricLine(104000L, "Every second sparks when you're by my side"),
                LyricLine(118000L, "We've got nowhere to hide"),
                LyricLine(134000L, "Electric heartbeat pulsing loud and clear!"),
                LyricLine(150000L, "[Harmonic Bridge & Sparkle Outro]")
            )
        ),

        // Track 16 - Hip-Hop
        Track(
            id = "track_16",
            title = "Concrete Crown",
            artist = "Malik D",
            album = "Empire of the East",
            durationMs = 195000L,
            genre = "Hip-Hop",
            coverGradientStart = 0xFF3F3F46,
            coverGradientEnd = 0xFFCA8A04,
            iconCategory = "hiphop",
            bitrateKbps = 320,
            audioFormat = "Lossless Master 24-bit",
            lyrics = listOf(
                LyricLine(0L, "[Grimy Vinyl Piano Loop & Hard Boombap Drums]"),
                LyricLine(14000L, "Started with a dream on a cracked avenue corner"),
                LyricLine(28000L, "Now we making moves, growing smarter and warmer"),
                LyricLine(42000L, "Heavy is the head that holds the concrete crown"),
                LyricLine(56000L, "Never let the doubt bring the empire down"),
                LyricLine(70000L, "Concrete crown! Carved in stone!"),
                LyricLine(84000L, "Built this kingdom all on my own!"),
                LyricLine(100000L, "[Scratched Vocal Sample & Sub Bass]"),
                LyricLine(116000L, "From the underground roots to the sky terrace view"),
                LyricLine(130000L, "Staying dedicated, keeping every promise true"),
                LyricLine(148000L, "Concrete crown! Respect the sound!"),
                LyricLine(165000L, "[Classic Hip-Hop Fadeout]")
            )
        ),

        // Track 17 - R&B
        Track(
            id = "track_17",
            title = "Silk & Midnight",
            artist = "Leon Vance",
            album = "After Hours Sanctuary",
            durationMs = 210000L,
            genre = "R&B",
            coverGradientStart = 0xFF4C1D95,
            coverGradientEnd = 0xFF831843,
            iconCategory = "rnb",
            bitrateKbps = 320,
            audioFormat = "Studio High Fidelity 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Deep Warm Bassline & Lush Electric Piano]"),
                LyricLine(16000L, "Past midnight, shadows dancing on the blinds"),
                LyricLine(32000L, "Two wandering hearts with peaceful open minds"),
                LyricLine(48000L, "Silk sheets and gentle whispers in the dark"),
                LyricLine(64000L, "Igniting an immortal acoustic spark"),
                LyricLine(80000L, "Silk and midnight, baby take your time"),
                LyricLine(96000L, "Your love is a rhythm, sweet and sublime"),
                LyricLine(115000L, "[Silky Falsetto Runs & Guitar Harmonics]"),
                LyricLine(138000L, "Let the world outside rush on by"),
                LyricLine(155000L, "Just you and me beneath the starry sky"),
                LyricLine(175000L, "[Slow R&B Groove Outro]")
            )
        ),

        // Track 18 - Metal
        Track(
            id = "track_18",
            title = "Ashes of Valhalla",
            artist = "Frostbane",
            album = "Norse Winterstorm",
            durationMs = 225000L,
            genre = "Rock",
            coverGradientStart = 0xFF1E293B,
            coverGradientEnd = 0xFF991B1B,
            iconCategory = "metal",
            bitrateKbps = 320,
            audioFormat = "Extreme Dynamic Range FLAC",
            lyrics = listOf(
                LyricLine(0L, "[Twin Lead Metal Guitars & Thundering Blastbeats]"),
                LyricLine(18000L, "Across the frozen fjords the ancient raven cries"),
                LyricLine(36000L, "Blood and steel reflecting in the northern skies"),
                LyricLine(54000L, "Raise the shields of iron, sound the battle horn!"),
                LyricLine(72000L, "From the ashes of Valhalla we are born!"),
                LyricLine(90000L, "[Blistering Dual Guitar Sweep Solo]"),
                LyricLine(120000L, "Odin watches from the golden hall on high"),
                LyricLine(138000L, "Warriors of thunder who will never die!"),
                LyricLine(158000L, "Valhalla awaits! Valhalla calls!"),
                LyricLine(178000L, "[Mighty Viking Chorus & Epic Outro]")
            )
        ),

        // Track 19 - Ambient
        Track(
            id = "track_19",
            title = "Weightless Float",
            artist = "Zen Garden",
            album = "Binaural Theta Frequencies",
            durationMs = 250000L,
            genre = "Ambient",
            coverGradientStart = 0xFF0D9488,
            coverGradientEnd = 0xFF0369A1,
            iconCategory = "ambient",
            bitrateKbps = 1411,
            audioFormat = "Audiophile 96kHz Lossless",
            lyrics = listOf(
                LyricLine(0L, "[Gentle Theta Waves & Flowing Bamboo Water]"),
                LyricLine(30000L, "[Let Go of All Tension in Your Shoulders and Jaw]"),
                LyricLine(65000L, "[Warm Harmonics Surround the Stereo Field]"),
                LyricLine(105000L, "[漂う (Tadayou) - Drifting Floating Mind]"),
                LyricLine(150000L, "[Deep Resonant Gongs Vibrating at 528 Hz]"),
                LyricLine(195000L, "[Pure Tranquility Fills Every Cell]"),
                LyricLine(230000L, "[Soft Gentle Fade into Silence]")
            )
        ),

        // Track 20 - Synthwave
        Track(
            id = "track_20",
            title = "Outrun 1984",
            artist = "Turbo Mirage",
            album = "Testarossa Sunset",
            durationMs = 195000L,
            genre = "Synthwave",
            coverGradientStart = 0xFFC026D3,
            coverGradientEnd = 0xFF0284C7,
            iconCategory = "synth",
            bitrateKbps = 320,
            audioFormat = "Analogue Synthesizer Master",
            lyrics = listOf(
                LyricLine(0L, "[LinnDrum Beat & Moog Bass Sequence]"),
                LyricLine(12000L, "Red Ferrari gliding toward the sinking sun"),
                LyricLine(24000L, "Radio playing cassette tapes one by one"),
                LyricLine(36000L, "Palm tree silhouettes lining Miami shores"),
                LyricLine(48000L, "Step on the pedal, hear the twin engine roars!"),
                LyricLine(60000L, "Outrun 1984! Forever in the race!"),
                LyricLine(72000L, "Neon horizons in time and space!"),
                LyricLine(88000L, "[Keytar Solo with Digital Delay]"),
                LyricLine(108000L, "Sunglasses at twilight, living the dream"),
                LyricLine(124000L, "Gliding through the retro-futuristic beam"),
                LyricLine(144000L, "Outrun! Never look behind!"),
                LyricLine(160000L, "[Vintage Synth Arp Fade]")
            )
        ),

        // Track 21 - Lo-Fi
        Track(
            id = "track_21",
            title = "Coffee Shop Window",
            artist = "Autumn Leaf",
            album = "Sunday Morning Slumber",
            durationMs = 172000L,
            genre = "Lo-Fi",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFF059669,
            iconCategory = "lofi",
            bitrateKbps = 320,
            audioFormat = "Master Quality 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Coffee Beans Grinding & Warm Ambient Hum]"),
                LyricLine(12000L, "Steam on the glass, watching bicycles pass"),
                LyricLine(24000L, "Sipping cold brew as the morning skies clear"),
                LyricLine(36000L, "Nothing is rushed when you're resting right here"),
                LyricLine(50000L, "Gentle acoustic guitar over dusty tape"),
                LyricLine(64000L, "A cozy sanctuary and quiet escape"),
                LyricLine(80000L, "[Smooth Jazzy Rhodes Solo]"),
                LyricLine(98000L, "Notes written in an old paperback book"),
                LyricLine(114000L, "Finding peace in every little corner and nook"),
                LyricLine(132000L, "Sunday morning coffee shop window..."),
                LyricLine(150000L, "[Mellow Lo-Fi Outro]")
            )
        ),

        // Track 22 - Classical
        Track(
            id = "track_22",
            title = "Symphony of the Cosmos",
            artist = "Vienna Cinematic Orchestra",
            album = "Interstellar Horizons",
            durationMs = 245000L,
            genre = "Classical",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF0891B2,
            iconCategory = "classical",
            bitrateKbps = 1411,
            audioFormat = "Full Orchestral DSD / FLAC 1411kbps",
            lyrics = listOf(
                LyricLine(0L, "[Conductor's Baton Tap - Quiet String Tremolo]"),
                LyricLine(18000L, "[French Horns Announce the Starlight Theme]"),
                LyricLine(40000L, "[Violin Section Ascends in Celestial Splendor]"),
                LyricLine(70000L, "[Timpani Rolls as Galaxies Collide in Harmony]"),
                LyricLine(105000L, "[Grand Orchestral Crescendo with Full Brass]"),
                LyricLine(145000L, "[Oboe and Harp Duet - The Loneliness of Space]"),
                LyricLine(185000L, "[Triumphant Return of the Cosmic Theme]"),
                LyricLine(215000L, "[Majestic Tutti Chord Resonating in the Great Hall]")
            )
        ),

        // Track 23 - Pakistani Pop (Coke Studio 14)
        Track(
            id = "track_23",
            title = "Pasoori",
            artist = "Ali Sethi & Shae Gill",
            album = "Coke Studio Season 14",
            durationMs = 224000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFDC2626,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Coke Studio Master 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Harmonium Intro & Rubab Strumming]"),
                LyricLine(14000L, "Agg laavan aagayi ae majboori nu"),
                LyricLine(28000L, "Aan jaan di yaari nu"),
                LyricLine(40000L, "Dholna ve dholna ve kithay gaya mera dil"),
                LyricLine(54000L, "Zamaana ve zamaana ve saanu ainvaye na mil"),
                LyricLine(68000L, "Dil vich vasda ae tuhiyo ae meri jaan"),
                LyricLine(82000L, "[Groovy Dholak Beat & Clarinet Solo]"),
                LyricLine(102000L, "Raawan takk takk thak gaiyan akhiyan"),
                LyricLine(116000L, "Gallan dil diyan saariyan ne dasiyan"),
                LyricLine(130000L, "Pasoori ban gayi ae tere pyar di kahani"),
                LyricLine(146000L, "Gunjdi hawaavan vich teri nishani"),
                LyricLine(164000L, "[Shae Gill & Ali Sethi Duet Crescendo]"),
                LyricLine(188000L, "Agg laavan aagayi ae majboori nu!"),
                LyricLine(205000L, "[Acoustic Rubab Outro]")
            )
        ),

        // Track 24 - Qawwali (Coke Studio 8)
        Track(
            id = "track_24",
            title = "Tajdar-e-Haram",
            artist = "Atif Aslam",
            album = "Coke Studio Season 8",
            durationMs = 285000L,
            genre = "Qawwali",
            coverGradientStart = 0xFF047857,
            coverGradientEnd = 0xFF1E3A8A,
            iconCategory = "qawwali",
            bitrateKbps = 1411,
            audioFormat = "Hi-Res FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Soulful Harmonium & Handclaps]"),
                LyricLine(20000L, "Qismat mein meri chain se jeena likh de"),
                LyricLine(42000L, "Doobe na kabhi mera safeena likh de"),
                LyricLine(65000L, "Jannat bhi gawaara hai magar mere liye"),
                LyricLine(85000L, "Aye kaatib-e-taqdeer Madina likh de!"),
                LyricLine(110000L, "Tajdar-e-haram... ho nigaah-e-karam!"),
                LyricLine(130000L, "Hum ghareebon ke din bhi sanwar jaayenge"),
                LyricLine(155000L, "[Traditional Qawwali Clapping & Tabla Climax]"),
                LyricLine(185000L, "Chashm-e-rehmat kusha sooye daagh-e-jigar"),
                LyricLine(210000L, "Tajdar-e-haram... aye shehenshah-e-deed!"),
                LyricLine(240000L, "Ho nigaah-e-karam, ho nigaah-e-karam..."),
                LyricLine(265000L, "[Spiritual Harmonium Fadeout]")
            )
        ),

        // Track 25 - Sufi / Ghazal (Coke Studio 9)
        Track(
            id = "track_25",
            title = "Afreen Afreen",
            artist = "Rahat Fateh Ali Khan & Momina Mustehsan",
            album = "Coke Studio Season 9",
            durationMs = 260000L,
            genre = "Sufi",
            coverGradientStart = 0xFF7C2D12,
            coverGradientEnd = 0xFFF59E0B,
            iconCategory = "sufi",
            bitrateKbps = 320,
            audioFormat = "Master 320kbps Studio Acoustic",
            lyrics = listOf(
                LyricLine(0L, "[Acoustic Guitar Strum & Harmonium Alaap]"),
                LyricLine(18000L, "Husn-e-jaanaan ki tareef mumkin nahi"),
                LyricLine(36000L, "Afreen afreen, afreen afreen"),
                LyricLine(54000L, "Tu bhi dekhe agar to kahe hum-nasheen"),
                LyricLine(72000L, "Afreen afreen, afreen afreen"),
                LyricLine(92000L, "Aankhein jaise maikhana, chehra jaise gulshan"),
                LyricLine(112000L, "Zulfen jaise badli chhaaye saare aalam par"),
                LyricLine(135000L, "[Rahat Fateh Ali Khan Virtuoso Taanke Alaap]"),
                LyricLine(165000L, "Jaane kaisa jaadu kiya tere deedar ne"),
                LyricLine(190000L, "Dil ko loot liya teri ik pukaar ne"),
                LyricLine(215000L, "Afreen afreen! Husn-e-jaanaan ki tareef mumkin nahi!"),
                LyricLine(240000L, "[Harmonious Outro]")
            )
        ),

        // Track 26 - Balochi Folk & Pop (Coke Studio 14)
        Track(
            id = "track_26",
            title = "Kana Yaari",
            artist = "Kaifi Khalil, Eva B & Abdul Wahab Bugti",
            album = "Coke Studio Season 14",
            durationMs = 210000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFFD97706,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Lossless 24-bit Balochi Master",
            lyrics = listOf(
                LyricLine(0L, "[Dambura & Balochi Folk Rhythm]"),
                LyricLine(15000L, "Kana yaari man pawaani"),
                LyricLine(30000L, "Dil e darde dila man zana"),
                LyricLine(45000L, "Ishq e aasa man sochaani"),
                LyricLine(60000L, "Beraan tara man gindana"),
                LyricLine(78000L, "[Eva B Rapid Urdu Rap Flow & 808s]"),
                LyricLine(98000L, "Meri manzil meri raahon ka nishaan hai"),
                LyricLine(115000L, "Har lafz mein sachai ka bayaan hai"),
                LyricLine(135000L, "Kana yaari! Dil de dard nu kaun pehchane!"),
                LyricLine(160000L, "[Wahab Bugti Folk Hook & Modern Synth Drop]"),
                LyricLine(185000L, "Ishq di baazi jit ke vi haar gaye aan"),
                LyricLine(200000L, "[Dambura Folk Outro]")
            )
        ),

        // Track 27 - Emotional Ballad
        Track(
            id = "track_27",
            title = "Kahani Suno 2.0",
            artist = "Kaifi Khalil",
            album = "Kahani Suno",
            durationMs = 175000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF1E1B4B,
            coverGradientEnd = 0xFF9F1239,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Direct DSD Acoustic Master",
            lyrics = listOf(
                LyricLine(0L, "[Delicate Piano & Fingerpicked Guitar]"),
                LyricLine(12000L, "Kahani suno, haan zubani suno"),
                LyricLine(24000L, "Mujhe pyar hua tha, iqrar hua tha"),
                LyricLine(38000L, "Deewana hua mastaana hua"),
                LyricLine(50000L, "Teri chahat mein kitna fasaana hua"),
                LyricLine(65000L, "Tere aane se pehle bechain tha dil"),
                LyricLine(78000L, "Tere jaane ke baad veeraan hai manzil"),
                LyricLine(95000L, "Kyun chhod gaye mujhko tanha yahan?"),
                LyricLine(110000L, "Ab dhoondta phiroon main tera nishaan"),
                LyricLine(128000L, "Mujhe pyar hua tha... iqrar hua tha..."),
                LyricLine(148000L, "Kahani suno... haan zubani suno..."),
                LyricLine(162000L, "[Heartfelt Acoustic Guitar Fade]")
            )
        ),

        // Track 28 - Sufi (Coke Studio 14)
        Track(
            id = "track_28",
            title = "Tu Jhoom",
            artist = "Abida Parveen & Naseebo Lal",
            album = "Coke Studio Season 14",
            durationMs = 270000L,
            genre = "Sufi",
            coverGradientStart = 0xFF4338CA,
            coverGradientEnd = 0xFFBE185D,
            iconCategory = "sufi",
            bitrateKbps = 1411,
            audioFormat = "Master FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Deep Tanpura Drone & Harmonium Alaap]"),
                LyricLine(22000L, "Peedaan nu pawan chhanvein"),
                LyricLine(44000L, "Dukh vi apne, sukh vi apne"),
                LyricLine(66000L, "Saare gham jholiyan paa ke"),
                LyricLine(88000L, "Rab de aggey matha teka"),
                LyricLine(112000L, "Tu jhoom... jhoom, jhoom, jhoom!"),
                LyricLine(135000L, "Main raazi aan usde faisle te"),
                LyricLine(160000L, "[Naseebo Lal Soaring Vocal High Pitch Note]"),
                LyricLine(188000L, "Nasha ishq da chadhda jave"),
                LyricLine(212000L, "Rooh nu azaad kar, dil nu saaf kar"),
                LyricLine(235000L, "Tu jhoom... aye mast qalandar jhoom!"),
                LyricLine(255000L, "[Ecstatic Sufi Climax & Bells]")
            )
        ),

        // Track 29 - Sufi Rock
        Track(
            id = "track_29",
            title = "Sayonee",
            artist = "Junoon",
            album = "Azadi",
            durationMs = 230000L,
            genre = "Rock",
            coverGradientStart = 0xFF7F1D1D,
            coverGradientEnd = 0xFFB45309,
            iconCategory = "rock",
            bitrateKbps = 320,
            audioFormat = "Sufi Rock Remastered 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Distorted Electric Sitar & Rock Drums]"),
                LyricLine(16000L, "Sayonee... chain ik pal nahi"),
                LyricLine(34000L, "Chain ik pal nahi, aur koi hal nahi"),
                LyricLine(52000L, "Kyun hawa mein dhuwan hai?"),
                LyricLine(70000L, "Yeh kaisa karwaan hai?"),
                LyricLine(90000L, "[Salman Ahmad Heavy Electric Guitar Riff]"),
                LyricLine(115000L, "Koyi to sun le meri faryad ve"),
                LyricLine(135000L, "Dil nu azaad kar, rab nu yaad kar"),
                LyricLine(160000L, "Sayonee! Chain ik pal nahi!"),
                LyricLine(185000L, "[Ali Azmat Iconic Powerful Vocal Hook]"),
                LyricLine(210000L, "[Crashing Cymbal Finale]")
            )
        ),

        // Track 30 - National Pop Classic
        Track(
            id = "track_30",
            title = "Dil Dil Pakistan",
            artist = "Vital Signs",
            album = "Vital Signs 1",
            durationMs = 210000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFFFFFFFF,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Remastered National Heritage Master",
            lyrics = listOf(
                LyricLine(0L, "[Junaid Jamshed Vocal Intro & Upbeat Synths]"),
                LyricLine(14000L, "Aisi zameen aur aasmaan"),
                LyricLine(28000L, "Inke siwa jaana kahan"),
                LyricLine(42000L, "Bhadti rahe yeh roshni"),
                LyricLine(56000L, "Chalta rahe yeh karwaan"),
                LyricLine(70000L, "Dil dil Pakistan! Jaan jaan Pakistan!"),
                LyricLine(90000L, "Dil dil Pakistan! Jaan jaan Pakistan!"),
                LyricLine(110000L, "[Bright Synth Guitar Lead & Bassline]"),
                LyricLine(130000L, "Har chehre par noor hai"),
                LyricLine(145000L, "Har dil mein umeed hai"),
                LyricLine(165000L, "Watan hamara jaan se pyaara"),
                LyricLine(185000L, "Dil dil Pakistan!"),
                LyricLine(200000L, "[Euphoric Chorus Outro]")
            )
        ),

        // Track 31 - Pop Rock
        Track(
            id = "track_31",
            title = "Woh Lamhe",
            artist = "Jal The Band",
            album = "Aadat",
            durationMs = 205000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF1E3A8A,
            coverGradientEnd = 0xFF06B6D4,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Original Studio Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Nostalgic Acoustic Guitar Plucking]"),
                LyricLine(15000L, "Woh lamhe, woh baatein"),
                LyricLine(30000L, "Koyi na jaane, thi kaisi raatein"),
                LyricLine(45000L, "O barsaatein... woh bheegi bheegi yaadein"),
                LyricLine(62000L, "Na main jaanoon, na tu jaane"),
                LyricLine(80000L, "Kaisa hai yeh aalam, kaisa afsaana"),
                LyricLine(100000L, "[Powerful Rock Drum Beat & Electric Guitar]"),
                LyricLine(125000L, "Sagar ki gehraiyon se gehra hai gham"),
                LyricLine(145000L, "Kaise bhulaayein woh shaamein sanam"),
                LyricLine(168000L, "Woh lamhe... woh baatein..."),
                LyricLine(190000L, "[Emotive Guitar Outro]")
            )
        ),

        // Track 32 - Acoustic Melodic
        Track(
            id = "track_32",
            title = "Faasle",
            artist = "Kaavish",
            album = "Gunkali",
            durationMs = 215000L,
            genre = "Acoustic",
            coverGradientStart = 0xFF0F766E,
            coverGradientEnd = 0xFF831843,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Pure Acoustic Direct Analog Master",
            lyrics = listOf(
                LyricLine(0L, "[Intimate Acoustic Guitar & Soft Cello]"),
                LyricLine(18000L, "Faasle aise honge socha na tha"),
                LyricLine(36000L, "Saamne baith kar bhi koyi anjaan tha"),
                LyricLine(56000L, "Khamoshiyon mein dhal gayi baatein tamaam"),
                LyricLine(76000L, "Kyun likha kismat ne yeh ajab anjaam"),
                LyricLine(98000L, "[Gentle Piano Chord Progression]"),
                LyricLine(122000L, "Waqt ki raah par bikhre nishaan"),
                LyricLine(145000L, "Dhoondein wafayein kahan se yahan"),
                LyricLine(170000L, "Faasle... aise bhi hote hain..."),
                LyricLine(195000L, "[Cello Fadeout]")
            )
        ),

        // Track 33 - Urban Pop / Electro (Coke Studio 14)
        Track(
            id = "track_33",
            title = "Peechay Hutt",
            artist = "Hasan Raheem, Justin Bibis & Talal Qureshi",
            album = "Coke Studio Season 14",
            durationMs = 185000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFFEA580C,
            coverGradientEnd = 0xFF9333EA,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "808 Urban Electro Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Talal Qureshi 808 Trap Groove & Synth Stabs]"),
                LyricLine(12000L, "Peechay hutt, raasta chhad, aagaye hum!"),
                LyricLine(24000L, "Apne he dhang mein nachde kadam"),
                LyricLine(36000L, "Hasan Raheem flow smoothly on the beat"),
                LyricLine(48000L, "Rocking the rhythm right down the street"),
                LyricLine(62000L, "[Justin Bibis High Energy Vocal Hook]"),
                LyricLine(80000L, "Koyi roke na humko, koyi toke na humko"),
                LyricLine(96000L, "Peechay hutt! Desi swag on top!"),
                LyricLine(115000L, "[Massive Electronic Bass Drop]"),
                LyricLine(135000L, "Yeh hai naya daur, nayi aawaz"),
                LyricLine(152000L, "Pakistan di shaan, be-misaal andaaz!"),
                LyricLine(170000L, "[Talal Qureshi Glitch Outro]")
            )
        ),

        // Track 34 - Romantic Ballad
        Track(
            id = "track_34",
            title = "Ghalat Fehmi",
            artist = "Asim Azhar & Zenab Fatimah Sultan",
            album = "Superstar OST",
            durationMs = 210000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF475569,
            coverGradientEnd = 0xFFE11D48,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Orchestral Master 24-bit",
            lyrics = listOf(
                LyricLine(0L, "[Sweet Acoustic Guitar & Sitar Harmonics]"),
                LyricLine(16000L, "Dhaage tod laao chaandni se noor ke"),
                LyricLine(32000L, "Ghoonghat hi bana lo roshni se noor ke"),
                LyricLine(48000L, "Sharmaye aaftaab bhi dekhe jo roop ko"),
                LyricLine(64000L, "Ghalat fehmi mein na rehna mere hum-dum"),
                LyricLine(82000L, "Tere he naam se roshan hai har ek janam"),
                LyricLine(105000L, "[Asim Azhar Soaring High Chorus]"),
                LyricLine(130000L, "Tujhse he subha, tujhse he shaam"),
                LyricLine(150000L, "Har pal likha hai tere he naam"),
                LyricLine(175000L, "Ghalat fehmi mita do sanam..."),
                LyricLine(195000L, "[Strings Orchestra Finale]")
            )
        ),

        // Track 35 - Folk / Bhangra
        Track(
            id = "track_35",
            title = "Billo De Ghar",
            artist = "Abrar-ul-Haq",
            album = "Billo De Ghar",
            durationMs = 190000L,
            genre = "Punjabi",
            coverGradientStart = 0xFFEAB308,
            coverGradientEnd = 0xFF16A34A,
            iconCategory = "punjabi",
            bitrateKbps = 320,
            audioFormat = "Punjabi Folk Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[High-Octane Dhol Beat & Tumbi Riff]"),
                LyricLine(12000L, "Assan te jaana billo de ghar!"),
                LyricLine(24000L, "Kine kine jaana billo de ghar!"),
                LyricLine(36000L, "Ticket kata lo jaldi saare"),
                LyricLine(48000L, "Nachange saare dhol te yaare!"),
                LyricLine(62000L, "[Energetic Punjabi Chorus]"),
                LyricLine(78000L, "Billo da haasa, qaatil adawan"),
                LyricLine(94000L, "Dil lut leya meri jaan le gayian sadawan"),
                LyricLine(115000L, "[Bhangra Dhol Solo & Chimta]"),
                LyricLine(135000L, "Assan te jaana billo de ghar!"),
                LyricLine(155000L, "Balle balle! Shawa shawa!"),
                LyricLine(175000L, "[Festive Punjabi Outro]")
            )
        ),

        // Track 36 - Classical Fusion (Coke Studio)
        Track(
            id = "track_36",
            title = "Aaye Na Balam",
            artist = "Ali Zafar",
            album = "Coke Studio Season 10",
            durationMs = 235000L,
            genre = "Classical",
            coverGradientStart = 0xFF581C87,
            coverGradientEnd = 0xFF0E7490,
            iconCategory = "classical",
            bitrateKbps = 1411,
            audioFormat = "Raag Bhairavi DSD Hi-Res",
            lyrics = listOf(
                LyricLine(0L, "[Ustad Bade Ghulam Ali Khan Classic - Raag Bhairavi Alaap]"),
                LyricLine(20000L, "Aaye na balam ka karun sajni"),
                LyricLine(42000L, "Aaye na balam ka karun sajni"),
                LyricLine(64000L, "Neend na aaye akhiyan maahi"),
                LyricLine(86000L, "Birha sataye mohe pal pal re"),
                LyricLine(112000L, "[Ali Zafar Classical Vocal Taans & Tabla Solo]"),
                LyricLine(140000L, "Piya bin soona laage gagan"),
                LyricLine(165000L, "Kaisi aag lagayi ae sajan"),
                LyricLine(190000L, "Aaye na balam... ka karun sajni..."),
                LyricLine(215000L, "[Classical Swar Taans & Harmonium Outro]")
            )
        )
    )

    val sampleTracks: List<Track> = baseTracks +
        com.example.data.catalog.NfakTracks.tracks +
        com.example.data.catalog.ArijitSinghTracks.tracks +
        com.example.data.catalog.AsimAzharTracks.tracks +
        com.example.data.catalog.TalhaAnjumTracks.tracks +
        com.example.data.catalog.BilalSaeedTracks.tracks

    val defaultPlaylists = listOf(
        Pair("Ustad Nusrat Fateh Ali Khan – Sufi & Qawwali", "Spiritual devotional masterpieces and eternal qawwalis"),
        Pair("Arijit Singh – Heartbreak & Soul", "The voice of modern Bollywood and timeless romantic ballads"),
        Pair("Talha Anjum & Young Stunners – Urdu Hip-Hop", "Lyrical masterpieces, gritty Karachi anthems, and melodic flows"),
        Pair("Asim Azhar – Pop & R&B Hits", "Breakthrough Pakistani pop, romantic staples, and collaborative hits"),
        Pair("Bilal Saeed – Punjabi Pop Classics", "The pioneer of modern Punjabi pop and urban dance rhythms"),
        Pair("Pakistani & Coke Studio Hits", "Legendary Coke Studio, Sufi, Pop, and Qawwali master tracks"),
        Pair("Liked Songs", "Your personal collection of favorite high-fidelity tracks"),
        Pair("Audiophile Essentials", "Master-quality tracks tuned for pristine dynamic range"),
        Pair("Late Night Focus", "Lo-Fi, Ambient, and Synthwave for deep concentration")
    )
}
