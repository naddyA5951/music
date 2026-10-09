package com.example.data.catalog

import com.example.model.LyricLine
import com.example.model.Track

object NfakTracks {
    val tracks: List<Track> = listOf(
        // 1. Tum Ek Gorakh Dhanda Ho
        Track(
            id = "nfak_1",
            title = "Tum Ek Gorakh Dhanda Ho",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "The Legendary Qawwal Vol. 1",
            durationMs = 360000L,
            genre = "Qawwali",
            coverGradientStart = 0xFF78350F,
            coverGradientEnd = 0xFF1E1B4B,
            iconCategory = "qawwali",
            bitrateKbps = 1411,
            audioFormat = "Master Qawwali FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Harmonium Chords, Tanpura & Handclaps Alaap]"),
                LyricLine(25000L, "Kabhi yahan tumhe dhoonda, kabhi wahan pohnche"),
                LyricLine(50000L, "Tumhari deed ki khatir kahan kahan pohnche"),
                LyricLine(75000L, "Hairan hoon mere daata, yeh kaisa tamasha hai"),
                LyricLine(105000L, "Tum ek gorakh dhanda ho!"),
                LyricLine(130000L, "Har chehre pe noor tera, har dil mein zuroor tera"),
                LyricLine(160000L, "Tum ek gorakh dhanda ho! Aye mere rab tum ek gorakh dhanda ho!"),
                LyricLine(195000L, "[NFAK High Taanke Alaap & Roaring Qawwali Chorus]"),
                LyricLine(240000L, "Samajh mein kuch nahi aata ke tu kahan nahi hai"),
                LyricLine(285000L, "Tum ek gorakh dhanda ho!"),
                LyricLine(330000L, "[Clapping Rhythm & Mystical Climax]")
            )
        ),
        // 2. Allah Hoo Allah Hoo
        Track(
            id = "nfak_2",
            title = "Allah Hoo Allah Hoo",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Devotional Sufi Hymns",
            durationMs = 320000L,
            genre = "Sufi",
            coverGradientStart = 0xFF065F46,
            coverGradientEnd = 0xFF0F172A,
            iconCategory = "sufi",
            bitrateKbps = 1411,
            audioFormat = "Pure Sufi Master DSD",
            lyrics = listOf(
                LyricLine(0L, "[Spiritual Tanpura & Harmonium Dhun]"),
                LyricLine(20000L, "Yeh zameen jab na thi, yeh jahan jab na tha"),
                LyricLine(45000L, "Chaand suraj na the, aasmaan jab na tha"),
                LyricLine(70000L, "Raaz-e-haq bhi kisi par ayaan jab na tha"),
                LyricLine(95000L, "Tab na tha kuch yahan, tha magar tu hi tu"),
                LyricLine(120000L, "Allah Hoo! Allah Hoo! Allah Hoo!"),
                LyricLine(150000L, "Tu hi maalik mera, tu hi maula mera"),
                LyricLine(180000L, "Allah Hoo! Allah Hoo! Allah Hoo!"),
                LyricLine(220000L, "[Ecstatic Rhythm Speed Acceleration & Harmonium Solo]"),
                LyricLine(260000L, "Har zarray mein tera noor chamakta hai daata"),
                LyricLine(295000L, "Allah Hoo... Allah Hoo... Allah Hoo...")
            )
        ),
        // 3. Ali Maula Ali Dam Dam
        Track(
            id = "nfak_3",
            title = "Ali Maula Ali Dam Dam",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Qalandari Mast",
            durationMs = 310000L,
            genre = "Qawwali",
            coverGradientStart = 0xFFB91C1C,
            coverGradientEnd = 0xFF451A03,
            iconCategory = "qawwali",
            bitrateKbps = 320,
            audioFormat = "Studio Qawwali Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Furious Clapping & Dholak Thump]"),
                LyricLine(18000L, "Ali imaam-e-manasto manam ghulaam-e-Ali"),
                LyricLine(40000L, "Hazaaron jaan-e-giraami fidaa ba naam-e-Ali"),
                LyricLine(65000L, "Dam mast qalandar Ali Ali!"),
                LyricLine(88000L, "Ali maula Ali maula Ali dam dam!"),
                LyricLine(115000L, "Ali maula Ali maula Ali dam dam!"),
                LyricLine(145000L, "[Intense NFAK Sargam Virtuosity]"),
                LyricLine(180000L, "Shah-e-mardaan, sher-e-yazdaan, quwwat-e-parwardigaar"),
                LyricLine(215000L, "La fataa illa Ali, la saif illa zulfiqaar!"),
                LyricLine(250000L, "Ali maula Ali dam dam!"),
                LyricLine(285000L, "[Thunderous Qawwali Finale]")
            )
        ),
        // 4. Nit Khair Manga
        Track(
            id = "nfak_4",
            title = "Nit Khair Manga",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Ishq Da Rutba",
            durationMs = 280000L,
            genre = "Sufi",
            coverGradientStart = 0xFFC2410C,
            coverGradientEnd = 0xFF431407,
            iconCategory = "sufi",
            bitrateKbps = 320,
            audioFormat = "Analog Master Hi-Res 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Melancholic Harmonium & Tabla Theka]"),
                LyricLine(16000L, "Nit khair manga sohneya main teri"),
                LyricLine(34000L, "Dua na koi hor mangdi"),
                LyricLine(52000L, "Tere pichhe jogi hoyi, saari duniya main royi"),
                LyricLine(74000L, "Nit khair manga sohneya main teri!"),
                LyricLine(98000L, "[Soulful Taans & Chorus Harmony]"),
                LyricLine(125000L, "Tainu vekhe bina chain na aave"),
                LyricLine(150000L, "Meri jind jaan tere naal jave"),
                LyricLine(185000L, "Nit khair manga... rab kolon mangdi faryad ve..."),
                LyricLine(225000L, "Nit khair manga sohneya main teri!"),
                LyricLine(260000L, "[Harmonium Fadeout]")
            )
        ),
        // 5. Dam Mast Qalandar
        Track(
            id = "nfak_5",
            title = "Dam Mast Qalandar",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Lal Meri Pat",
            durationMs = 300000L,
            genre = "Qawwali",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFF78350F,
            iconCategory = "qawwali",
            bitrateKbps = 1411,
            audioFormat = "Audiophile Qawwali FLAC",
            lyrics = listOf(
                LyricLine(0L, "[Jhoolay Laal Bells & Fast Clapping Rhythm]"),
                LyricLine(18000L, "Lal meri pat rakhiyo bhala jhoole laalan"),
                LyricLine(38000L, "Sindhri da, Sehwan da, sakhi Shahbaz Qalandar"),
                LyricLine(60000L, "Duma dum mast qalandar!"),
                LyricLine(80000L, "Ali da pehla number!"),
                LyricLine(100000L, "Duma dum mast qalandar! Sakhi Shahbaz Qalandar!"),
                LyricLine(130000L, "[High-Octane Dhol & Harmonium Madness]"),
                LyricLine(165000L, "Chaar charaagh tere baran hamesha"),
                LyricLine(195000L, "Panjwaan main baaran aayi aan bhala jhoole laalan"),
                LyricLine(230000L, "Duma dum mast qalandar! Jhoolay laalan!"),
                LyricLine(270000L, "[Electrifying Qawwali Crescendo]")
            )
        ),
        // 5b. Afreen Afreen (Original Masterpiece)
        Track(
            id = "nfak_afreen",
            title = "Afreen Afreen",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Sangam / Eternal Ghazals",
            durationMs = 345000L,
            genre = "Ghazal",
            coverGradientStart = 0xFF991B1B,
            coverGradientEnd = 0xFF581C87,
            iconCategory = "ghazal",
            bitrateKbps = 1411,
            audioFormat = "Master Studio FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Mystical Harmonium & Tabla Alaap by Ustad Nusrat Fateh Ali Khan]"),
                LyricLine(24000L, "Husn-e-jaanaan ki tareef mumkin nahi"),
                LyricLine(48000L, "Afreen afreen, afreen afreen"),
                LyricLine(72000L, "Tu bhi dekhe agar to kahe hum-nasheen"),
                LyricLine(96000L, "Afreen afreen, afreen afreen"),
                LyricLine(124000L, "Aankhein jaise maikhana, chehra jaise gulshan"),
                LyricLine(152000L, "Zulfen jaise badli chhaaye saare aalam par"),
                LyricLine(185000L, "[Legendary NFAK Sargam & Taans]"),
                LyricLine(225000L, "Jaane kaisa jaadu kiya tere deedar ne"),
                LyricLine(260000L, "Dil ko loot liya teri ik pukaar ne"),
                LyricLine(295000L, "Afreen afreen! Husn-e-jaanaan ki tareef mumkin nahi!"),
                LyricLine(330000L, "[Classical Sargam Virtuosity & Harmonium Finale]")
            )
        ),
        // 6. Kinna Sohna Tenu Rab Ne Banaya
        Track(
            id = "nfak_6",
            title = "Kinna Sohna Tenu Rab Ne Banaya",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Romantic Sufi Ghazals",
            durationMs = 290000L,
            genre = "Sufi",
            coverGradientStart = 0xFFBE185D,
            coverGradientEnd = 0xFF831843,
            iconCategory = "sufi",
            bitrateKbps = 320,
            audioFormat = "Master Quality 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Sweet Harmonium Prelude & Soft Dholak]"),
                LyricLine(16000L, "Kinna sohna tenu rab ne banaya"),
                LyricLine(34000L, "Kinna sohna tenu rab ne banaya"),
                LyricLine(52000L, "Dil kare vekhda ravaan"),
                LyricLine(70000L, "Kinna sohna tenu rab ne banaya!"),
                LyricLine(92000L, "Dil da mamla hai, dildar da fasana"),
                LyricLine(115000L, "Tere roop da diwana saara zamana"),
                LyricLine(140000L, "[Nusrat's Soulful Alaap & Playful Swar]"),
                LyricLine(175000L, "Tere mukhde to nazran na hatdiyan"),
                LyricLine(205000L, "Dil kare vekhda ravaan, vekhda ravaan"),
                LyricLine(240000L, "Kinna sohna tenu rab ne banaya!"),
                LyricLine(270000L, "[Harmonious Romantic Outro]")
            )
        ),
        // 7. Mera Dholan Mahi
        Track(
            id = "nfak_7",
            title = "Mera Dholan Mahi",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Folk Expressions",
            durationMs = 275000L,
            genre = "Pakistani Pop",
            coverGradientStart = 0xFF0D9488,
            coverGradientEnd = 0xFF115E59,
            iconCategory = "pakistani",
            bitrateKbps = 320,
            audioFormat = "Pristine Folk Master",
            lyrics = listOf(
                LyricLine(0L, "[Rhythmic Dholak & Flute Alaap]"),
                LyricLine(18000L, "Mera dholan mahi aavega"),
                LyricLine(36000L, "Mere dukhde door karaavega"),
                LyricLine(56000L, "Raah takk takk thak gaiyan akhiyan meri"),
                LyricLine(78000L, "Kaddon dholan gale laavega"),
                LyricLine(105000L, "[Traditional Punjabi Alaap & Chorus]"),
                LyricLine(135000L, "Sajna ve tere baajon chain na aave"),
                LyricLine(165000L, "Tere bina yeh zindagani bitaayi na jaave"),
                LyricLine(200000L, "Mera dholan mahi... aavega..."),
                LyricLine(240000L, "[Folk Dholak & Swar Finale]")
            )
        ),
        // 8. Akhian Udeekdiyan
        Track(
            id = "nfak_8",
            title = "Akhian Udeekdiyan",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Supreme Qawwali Masterpieces",
            durationMs = 310000L,
            genre = "Qawwali",
            coverGradientStart = 0xFF4F46E5,
            coverGradientEnd = 0xFF312E81,
            iconCategory = "qawwali",
            bitrateKbps = 1411,
            audioFormat = "Lossless FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Melancholic Harmonium & Slow Tabla Rhythm]"),
                LyricLine(22000L, "Akhian udeekdiyan, dil vaajan maarda"),
                LyricLine(46000L, "Aaja pardesiya, vaasta e pyaar da"),
                LyricLine(70000L, "Akhian udeekdiyan! Dil vaajan maarda!"),
                LyricLine(98000L, "Teri raahwan vich phul main bichhawan"),
                LyricLine(124000L, "Tainu dil da haal sunawan"),
                LyricLine(155000L, "[Soul-Wrenching High Pitch Taans]"),
                LyricLine(195000L, "Lokan ne te dil todeya, tu na todin dildara"),
                LyricLine(230000L, "Akhian udeekdiyan... dil vaajan maarda..."),
                LyricLine(275000L, "[Deep Emotional Climax]")
            )
        ),
        // 9. Mast Nazron Se
        Track(
            id = "nfak_9",
            title = "Mast Nazron Se",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "The Suroor Sessions",
            durationMs = 295000L,
            genre = "Ghazal",
            coverGradientStart = 0xFF9333EA,
            coverGradientEnd = 0xFF581C87,
            iconCategory = "ghazal",
            bitrateKbps = 320,
            audioFormat = "Studio Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Harmonium Chords & Classic Qawwali Claps]"),
                LyricLine(18000L, "Mast nazron se Allah bachaye"),
                LyricLine(38000L, "Husn-waalon se Allah bachaye"),
                LyricLine(60000L, "Inki aadaayein hain bijli jaisi"),
                LyricLine(82000L, "Mast nazron se Allah bachaye!"),
                LyricLine(110000L, "[Playful Vocal Improvisation & Sargam]"),
                LyricLine(145000L, "Zulfen bikhrein to shaam ho jaye"),
                LyricLine(175000L, "Aankh uthe to qatl-e-aam ho jaye"),
                LyricLine(210000L, "Mast nazron se Allah bachaye! Husn-waalon se Allah bachaye!"),
                LyricLine(255000L, "[Spirited Qawwali Fadeout]")
            )
        ),
        // 10. Sanu Ek Pal Chain Na Aave
        Track(
            id = "nfak_10",
            title = "Sanu Ek Pal Chain Na Aave",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "Pure Devotion & Love",
            durationMs = 290000L,
            genre = "Sufi",
            coverGradientStart = 0xFFEA580C,
            coverGradientEnd = 0xFF9A3412,
            iconCategory = "sufi",
            bitrateKbps = 1411,
            audioFormat = "Direct Master FLAC",
            lyrics = listOf(
                LyricLine(0L, "[Intimate Harmonium Strumming]"),
                LyricLine(16000L, "Sanu ek pal chain na aave"),
                LyricLine(34000L, "Sajna tere bina, sajna tere bina"),
                LyricLine(54000L, "Sada kaleya jee nahi lagna"),
                LyricLine(72000L, "Sajna tere bina, sajna tere bina"),
                LyricLine(95000L, "Dukh sukh de saathi ban ke"),
                LyricLine(120000L, "Saari umar nibhaayi ae"),
                LyricLine(150000L, "[Unforgettable Melodic Taans & Swar]"),
                LyricLine(190000L, "Sanu ek pal chain na aave! Sajna tere bina!"),
                LyricLine(230000L, "Tere baajon keda mera... dholna..."),
                LyricLine(265000L, "[Harmonium & Dholak Outro]")
            )
        ),
        // 11. Yeh Jo Halka Halka Suroor Hai
        Track(
            id = "nfak_11",
            title = "Yeh Jo Halka Halka Suroor Hai",
            artist = "Ustad Nusrat Fateh Ali Khan",
            album = "The Legendary Concerts",
            durationMs = 380000L,
            genre = "Qawwali",
            coverGradientStart = 0xFF6D28D9,
            coverGradientEnd = 0xFF1E1B4B,
            iconCategory = "qawwali",
            bitrateKbps = 1411,
            audioFormat = "Concert Master FLAC 24-bit/96kHz",
            lyrics = listOf(
                LyricLine(0L, "[Extended Harmonium Prelude & Slow Rhythm Clapping]"),
                LyricLine(28000L, "Saqi ki har nigaah pe bal khaa ke pee gaya"),
                LyricLine(58000L, "Maujhon se khelta hua lehra ke pee gaya"),
                LyricLine(92000L, "Yeh jo halka halka suroor hai"),
                LyricLine(118000L, "Mera ishq nahi fitoor hai"),
                LyricLine(144000L, "Tere rukh pe jo noor hai"),
                LyricLine(170000L, "Yeh jo halka halka suroor hai!"),
                LyricLine(205000L, "[Electrifying Sargam Duet with Rahat Fateh Ali Khan]"),
                LyricLine(250000L, "Tere ishq ne sab kuch bhula diya"),
                LyricLine(290000L, "Mujhe ik sharabi bana diya!"),
                LyricLine(330000L, "Yeh jo halka halka suroor hai... sab teri nazar ka kusoor hai!"),
                LyricLine(365000L, "[Legendary Qawwali Climax]")
            )
        )
    )
}
