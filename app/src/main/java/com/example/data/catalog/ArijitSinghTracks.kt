package com.example.data.catalog

import com.example.model.LyricLine
import com.example.model.Track

object ArijitSinghTracks {
    val tracks: List<Track> = listOf(
        // 1. Channa Mereya
        Track(
            id = "arijit_1",
            title = "Channa Mereya",
            artist = "Arijit Singh",
            album = "Ae Dil Hai Mushkil",
            durationMs = 289000L,
            genre = "Acoustic",
            coverGradientStart = 0xFFDC2626,
            coverGradientEnd = 0xFF7C2D12,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Master Studio 24-bit/48kHz",
            lyrics = listOf(
                LyricLine(0L, "[Acoustic Guitar Picking & Sitar Strums]"),
                LyricLine(16000L, "Achha chalta hoon duaon mein yaad rakhna"),
                LyricLine(32000L, "Mere zikr ka zubaan pe swaad rakhna"),
                LyricLine(50000L, "Dil ke sandookon mein mere achhe kaam rakhna"),
                LyricLine(68000L, "Chitthi taaron mein bhi mera tu salaam rakhna"),
                LyricLine(88000L, "Andhera tera maine le liya"),
                LyricLine(104000L, "Mera ujla sitaara tere naam kiya"),
                LyricLine(124000L, "Channa mereya mereya, channa mereya mereya"),
                LyricLine(144000L, "Channa mereya mereya beliya o piya!"),
                LyricLine(170000L, "[Arijit Singh Emotional Vocal Crescendo & Dholak]"),
                LyricLine(205000L, "O piya... channa mereya mereya beliya!"),
                LyricLine(240000L, "Tere rukh se apna daaman chhuda ke chala"),
                LyricLine(265000L, "[Sitar & Guitar Harmonics Outro]")
            )
        ),
        // 2. Tum Hi Ho
        Track(
            id = "arijit_2",
            title = "Tum Hi Ho",
            artist = "Arijit Singh",
            album = "Aashiqui 2",
            durationMs = 262000L,
            genre = "Pop",
            coverGradientStart = 0xFF1E3A8A,
            coverGradientEnd = 0xFF172554,
            iconCategory = "piano",
            bitrateKbps = 320,
            audioFormat = "Pristine Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Iconic Melodic Piano Opening]"),
                LyricLine(15000L, "Hum tere bin ab reh nahi sakte"),
                LyricLine(30000L, "Tere bina kya wajood mera"),
                LyricLine(46000L, "Tujhse juda agar ho jaayenge"),
                LyricLine(62000L, "To khud se hi ho jaayenge juda"),
                LyricLine(80000L, "Kyunki tum hi ho, ab tum hi ho"),
                LyricLine(98000L, "Zindagi ab tum hi ho!"),
                LyricLine(118000L, "Chain bhi, mera dard bhi"),
                LyricLine(136000L, "Meri aashiqui ab tum hi ho!"),
                LyricLine(160000L, "[Orchestral Strings & Driving Rock Drums]"),
                LyricLine(190000L, "Tere liye hi jiya main, khud ko jo yun de diya hai"),
                LyricLine(218000L, "Teri wafa ne mujhko sambhaala"),
                LyricLine(240000L, "Kyunki tum hi ho... ab tum hi ho...")
            )
        ),
        // 3. Agar Tum Saath Ho
        Track(
            id = "arijit_3",
            title = "Agar Tum Saath Ho",
            artist = "Arijit Singh & Alka Yagnik",
            album = "Tamasha",
            durationMs = 341000L,
            genre = "Acoustic",
            coverGradientStart = 0xFF9333EA,
            coverGradientEnd = 0xFF3B82F6,
            iconCategory = "acoustic",
            bitrateKbps = 320,
            audioFormat = "A.R. Rahman Hi-Res Master",
            lyrics = listOf(
                LyricLine(0L, "[A.R. Rahman Soft Piano & Acoustic Rhythm]"),
                LyricLine(20000L, "Pal bhar thehar jaao, dil yeh sambhal jaaye"),
                LyricLine(42000L, "Kaise tumhe rokein, aansoo nikal jaaye"),
                LyricLine(65000L, "Teri taraf mudne lage hain mere qadam"),
                LyricLine(88000L, "Behti rehti hai aakash pe ek dhoop"),
                LyricLine(115000L, "Agar tum saath ho..."),
                LyricLine(135000L, "Dil yeh sambhal jaaye agar tum saath ho"),
                LyricLine(165000L, "[Heart-wrenching Duet Harmony & Violins]"),
                LyricLine(205000L, "Har gham aasan lage, har dukh seh jaayenge"),
                LyricLine(245000L, "Agar tum saath ho..."),
                LyricLine(290000L, "Bin tere zindagi veeraan lagti hai"),
                LyricLine(320000L, "[Piano Solo Fadeout]")
            )
        ),
        // 4. Kabira
        Track(
            id = "arijit_4",
            title = "Kabira",
            artist = "Arijit Singh & Harshdeep Kaur",
            album = "Yeh Jawaani Hai Deewani",
            durationMs = 223000L,
            genre = "Sufi",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFFB45309,
            iconCategory = "sufi",
            bitrateKbps = 320,
            audioFormat = "Folk Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Acoustic Strumming & Traditional Dholak]"),
                LyricLine(14000L, "Kaisi teri khudgarzi, na dhoop chune na chhaanv"),
                LyricLine(30000L, "Kaisi teri khudgarzi, kisi thor tike na paanv"),
                LyricLine(48000L, "Ban liya apna paigambar, tar liya tu saat samandar"),
                LyricLine(66000L, "Phir bhi sookha mann ke andar, kyun reh gaya?"),
                LyricLine(88000L, "Re Kabira maan jaa, re Fakeera maan jaa"),
                LyricLine(112000L, "Aaja tujhko pukaare teri parchaaiyan"),
                LyricLine(138000L, "[Harshdeep Kaur Soulful Folk Alaap]"),
                LyricLine(165000L, "Tooti charpaai wahi, thandi purvaai wahi"),
                LyricLine(190000L, "Re Kabira maan jaa..."),
                LyricLine(210000L, "[Harmonious Acoustic Fade]")
            )
        ),
        // 5. Hamari Adhuri Kahani
        Track(
            id = "arijit_5",
            title = "Hamari Adhuri Kahani",
            artist = "Arijit Singh",
            album = "Hamari Adhuri Kahani",
            durationMs = 385000L,
            genre = "Pop",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFF475569,
            iconCategory = "piano",
            bitrateKbps = 320,
            audioFormat = "Lossless Dramatic Ballad FLAC",
            lyrics = listOf(
                LyricLine(0L, "[Poignant Piano Arpeggio & Strings]"),
                LyricLine(22000L, "Khushbu se teri yun hi takra gaye"),
                LyricLine(44000L, "Chalte chalte dekho kahan hum aa gaye"),
                LyricLine(68000L, "Kaisi yeh kashmakash, kaisa yeh daur hai"),
                LyricLine(94000L, "Dil mein chupa hua koi aur shor hai"),
                LyricLine(124000L, "Hamari adhuri kahani..."),
                LyricLine(150000L, "Hamari adhuri kahani..."),
                LyricLine(185000L, "[Arijit High-Pitch Emotional Crescendo]"),
                LyricLine(230000L, "Zulfon ke saaye mein dhal jaati thi shaam"),
                LyricLine(275000L, "Ab bas reh gaya tera mera naam"),
                LyricLine(320000L, "Hamari adhuri kahani..."),
                LyricLine(360000L, "[Cello & Piano Outro]")
            )
        ),
        // 6. Phir Le Aya Dil
        Track(
            id = "arijit_6",
            title = "Phir Le Aya Dil",
            artist = "Arijit Singh",
            album = "Barfi!",
            durationMs = 305000L,
            genre = "Ghazal",
            coverGradientStart = 0xFFB45309,
            coverGradientEnd = 0xFF78350F,
            iconCategory = "ghazal",
            bitrateKbps = 1411,
            audioFormat = "Direct DSD Ghazal Master",
            lyrics = listOf(
                LyricLine(0L, "[Pritam's Acoustic Guitar & Harmonium Alaap]"),
                LyricLine(20000L, "Phir le aaya dil majboor kya keeje"),
                LyricLine(44000L, "Raas na aaya rehna door kya keeje"),
                LyricLine(70000L, "Dil keh raha use mukammal kar bhi aao"),
                LyricLine(98000L, "Wo jo adhoori si baat baaqi hai"),
                LyricLine(128000L, "Wo jo adhoori si yaad baaqi hai"),
                LyricLine(160000L, "[Arijit Classical Harkatein & Soft Tabla]"),
                LyricLine(200000L, "Kismat ko hai manzoor kya keeje"),
                LyricLine(240000L, "Phir le aaya dil majboor kya keeje"),
                LyricLine(280000L, "[Delicate Acoustic Outro]")
            )
        ),
        // 7. Khairiyat
        Track(
            id = "arijit_7",
            title = "Khairiyat",
            artist = "Arijit Singh",
            album = "Chhichhore",
            durationMs = 280000L,
            genre = "Pop",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0369A1,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Master 320kbps Studio Acoustic",
            lyrics = listOf(
                LyricLine(0L, "[Gentle Acoustic Guitar & Synth Pad]"),
                LyricLine(16000L, "Khairiyat poocho, kabhi to kaifiyat poocho"),
                LyricLine(34000L, "Tumhare bin deewane ka kya haal hai"),
                LyricLine(52000L, "Dil mera dekho, na meri haisiyat poocho"),
                LyricLine(70000L, "Tere bin ek din jaise sau saal hai"),
                LyricLine(94000L, "Anjaam hai tay mera, hona tumhe hai mera"),
                LyricLine(118000L, "Jitni bhi hon dooriyan filhaal hain"),
                LyricLine(144000L, "[Lush Orchestral Strings Swell]"),
                LyricLine(175000L, "Ye ishq ki inteha hai, jo tu meri dua hai"),
                LyricLine(210000L, "Khairiyat poocho... kabhi to kaifiyat poocho..."),
                LyricLine(250000L, "[Soft Melodic Fade]")
            )
        ),
        // 8. Apna Bana Le
        Track(
            id = "arijit_8",
            title = "Apna Bana Le",
            artist = "Arijit Singh & Sachin-Jigar",
            album = "Bhediya",
            durationMs = 264000L,
            genre = "Romantic",
            coverGradientStart = 0xFFF43F5E,
            coverGradientEnd = 0xFF9F1239,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Hi-Res Master 24-bit/48kHz",
            lyrics = listOf(
                LyricLine(0L, "[Acoustic Strums & Whistling Melody]"),
                LyricLine(15000L, "Tu mera koi na hoke bhi kuch laage"),
                LyricLine(32000L, "Kiya re jo bhi toone, dil pe mere chaahe"),
                LyricLine(50000L, "Apna bana le piya, apna bana le piya"),
                LyricLine(68000L, "Dil ke nagar mein sheher basa le piya"),
                LyricLine(90000L, "[Soulful Guitar Solo & Soft Kick]"),
                LyricLine(115000L, "Chhoone se tere haan tere, mausam gulabi hua"),
                LyricLine(140000L, "Tere he rang mein rang gaya dil mera"),
                LyricLine(170000L, "Apna bana le piya... apna bana le piya..."),
                LyricLine(210000L, "Sab kuch ganwa ke tujhko paaya"),
                LyricLine(240000L, "[Romantic Acoustic Finish]")
            )
        ),
        // 9. Kesariya
        Track(
            id = "arijit_9",
            title = "Kesariya",
            artist = "Arijit Singh",
            album = "Brahmāstra",
            durationMs = 268000L,
            genre = "Romantic",
            coverGradientStart = 0xFFF97316,
            coverGradientEnd = 0xFFC2410C,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Pritam Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Bright Banjo, Strumming & Flute]"),
                LyricLine(14000L, "Mujhko itna bataaye koi"),
                LyricLine(28000L, "Kaise tujhse dil na lagaaye koi"),
                LyricLine(44000L, "Rabba ne tujhko banaane mein"),
                LyricLine(60000L, "Kardi hai husn ki khaali tijoriyan"),
                LyricLine(78000L, "Kesariya tera ishq hai piya"),
                LyricLine(94000L, "Rang jaaun jo main haath lagaun"),
                LyricLine(114000L, "Din beete saara teri fikr mein"),
                LyricLine(130000L, "Rain saari teri khair manaun"),
                LyricLine(155000L, "[Upbeat Danceable Pop Chorus & Shehnai]"),
                LyricLine(190000L, "Kesariya tera ishq hai piya!"),
                LyricLine(230000L, "[Sparkling Acoustic Outro]")
            )
        ),
        // 10. O Bedardeya
        Track(
            id = "arijit_10",
            title = "O Bedardeya",
            artist = "Arijit Singh",
            album = "Tu Jhoothi Main Makkaar",
            durationMs = 313000L,
            genre = "Acoustic",
            coverGradientStart = 0xFF7C2D12,
            coverGradientEnd = 0xFF1C1917,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Studio Heartbreak Master",
            lyrics = listOf(
                LyricLine(0L, "[Crying Violin Intro & Heavy Piano]"),
                LyricLine(18000L, "Pyaar jhootha tha jataaya hi kyun"),
                LyricLine(38000L, "Aise jaana tha to aaya hi kyun"),
                LyricLine(60000L, "Ae dil bata yeh kyun hua"),
                LyricLine(82000L, "O bedardeya... o bedardeya..."),
                LyricLine(110000L, "Jhoothi thi saari kasmein teri"),
                LyricLine(135000L, "Jhoothi thi saari rasmein teri"),
                LyricLine(165000L, "[Arijit Singh Screaming Painful High Notes]"),
                LyricLine(210000L, "Kyun tod diya aashiyaana mera"),
                LyricLine(250000L, "O bedardeya... o bedardeya..."),
                LyricLine(290000L, "[Fading Strings & Weeping Piano]")
            )
        ),
        // 11. Tera Fitoor
        Track(
            id = "arijit_11",
            title = "Tera Fitoor",
            artist = "Arijit Singh",
            album = "Genius",
            durationMs = 311000L,
            genre = "Romantic",
            coverGradientStart = 0xFF059669,
            coverGradientEnd = 0xFF065F46,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Himesh Reshammiya Hi-Fi Master",
            lyrics = listOf(
                LyricLine(0L, "[Melodious Acoustic Strum & Whistle]"),
                LyricLine(16000L, "Tera fitoor jab se chadh gaya re"),
                LyricLine(34000L, "Ishq jo tha wo badh gaya re"),
                LyricLine(52000L, "Mujhe lagti hai saari zameen aasmaan"),
                LyricLine(70000L, "Tera fitoor jab se chadh gaya re!"),
                LyricLine(95000L, "[Upbeat Pop Rhythm & Synth Chords]"),
                LyricLine(125000L, "Hawaayein bhi gun gunaaye tere geet"),
                LyricLine(155000L, "Dil ne seekhi hai bas teri preet"),
                LyricLine(190000L, "Tera fitoor... jab se chadh gaya re..."),
                LyricLine(240000L, "[Joyful Melody Outro]")
            )
        ),
        // 12. Rait Zara Si
        Track(
            id = "arijit_12",
            title = "Rait Zara Si",
            artist = "Arijit Singh & Shashaa Tirupati",
            album = "Atrangi Re",
            durationMs = 291000L,
            genre = "Romantic",
            coverGradientStart = 0xFFD97706,
            coverGradientEnd = 0xFF78350F,
            iconCategory = "sufi",
            bitrateKbps = 1411,
            audioFormat = "A.R. Rahman Lossless Master FLAC",
            lyrics = listOf(
                LyricLine(0L, "[Enchanting Shehnai & Mandolin Prelude]"),
                LyricLine(18000L, "Hona tera hona paana tumko paana"),
                LyricLine(36000L, "Lagne laga hai khud se begaana"),
                LyricLine(56000L, "Haath se fisli yeh rait zara si"),
                LyricLine(76000L, "Chhooti mere khwaabon ki dor zara si"),
                LyricLine(100000L, "[Dreamy A.R. Rahman Bassline & Flute Duet]"),
                LyricLine(135000L, "Palkon pe khwaab sajaaye the humne"),
                LyricLine(170000L, "Rait zara si... beh gayi paani mein jaise"),
                LyricLine(210000L, "Hona tera hona..."),
                LyricLine(260000L, "[Melancholy Classical Fade]")
            )
        ),
        // 13. Enna Sona
        Track(
            id = "arijit_13",
            title = "Enna Sona",
            artist = "Arijit Singh",
            album = "OK Jaanu",
            durationMs = 213000L,
            genre = "Romantic",
            coverGradientStart = 0xFFE11D48,
            coverGradientEnd = 0xFF9F1239,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "A.R. Rahman Studio Acoustic",
            lyrics = listOf(
                LyricLine(0L, "[A.R. Rahman Fingerpicked Guitar & Tambourine]"),
                LyricLine(14000L, "Enna sona kyun rab ne banaya"),
                LyricLine(28000L, "Aavan javan te main yaara nu manavan"),
                LyricLine(44000L, "Enna sona, enna sona o..."),
                LyricLine(60000L, "Kol hove te seik lagda ae"),
                LyricLine(76000L, "Door jaave te dil jalda ae"),
                LyricLine(95000L, "Kehdi agg naal rab ne banaya"),
                LyricLine(115000L, "[Sweet Vocal High Harmony]"),
                LyricLine(140000L, "Enna sona kyun rab ne banaya!"),
                LyricLine(175000L, "Enna sona..."),
                LyricLine(198000L, "[Acoustic Harmonics End]")
            )
        ),
        // 14. Hawayein
        Track(
            id = "arijit_14",
            title = "Hawayein",
            artist = "Arijit Singh",
            album = "Jab Harry Met Sejal",
            durationMs = 290000L,
            genre = "Romantic",
            coverGradientStart = 0xFF0284C7,
            coverGradientEnd = 0xFF0E7490,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Pritam Studio Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Breezy Acoustic Guitars & Whistling Hook]"),
                LyricLine(16000L, "Tujhko main kitna chahta hoon"),
                LyricLine(32000L, "Yeh tu kabhi soch na sake"),
                LyricLine(50000L, "Hawayein hawayein le jaayein jahan"),
                LyricLine(68000L, "Chalein hum wahan"),
                LyricLine(90000L, "Le jaayein jahan hawayein hawayein"),
                LyricLine(115000L, "[Infectious Pop Strumming & Drums]"),
                LyricLine(145000L, "Begaani si yeh raahein sabhi"),
                LyricLine(175000L, "Apni lagti hain tu jo paas ho"),
                LyricLine(210000L, "Hawayein... hawayein..."),
                LyricLine(255000L, "[Upbeat Whistle & Sunset Guitar]")
            )
        ),
        // 15. Chaleya
        Track(
            id = "arijit_15",
            title = "Chaleya",
            artist = "Arijit Singh & Shilpa Rao",
            album = "Jawan",
            durationMs = 200000L,
            genre = "Pop",
            coverGradientStart = 0xFF8B5CF6,
            coverGradientEnd = 0xFFEC4899,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Anirudh Ravichander Master 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Anirudh's Infectious Bouncy Bass & Finger Snaps]"),
                LyricLine(12000L, "Ishq mein dil bana hai, dil mein fidaa hai"),
                LyricLine(24000L, "Teri aankhon ka jaadu sar pe chadh gaya"),
                LyricLine(38000L, "Chaleya teri ore, chaleya teri ore"),
                LyricLine(52000L, "Mera dil na maane, bas daude teri ore!"),
                LyricLine(68000L, "[Catchy Dance Hook & Groovy Synth Chords]"),
                LyricLine(90000L, "Haye tauba kaisa yeh nasha chha gaya"),
                LyricLine(115000L, "Tu hi to meri subha, tu hi to shab"),
                LyricLine(140000L, "Chaleya teri ore! Chaleya teri ore!"),
                LyricLine(175000L, "[Modern Dance Pop Climax]")
            )
        ),
        // 16. Sooraj Dooba Hain
        Track(
            id = "arijit_16",
            title = "Sooraj Dooba Hain",
            artist = "Arijit Singh & Aditi Singh Sharma",
            album = "Roy",
            durationMs = 264000L,
            genre = "EDM",
            coverGradientStart = 0xFFF59E0B,
            coverGradientEnd = 0xFFD97706,
            iconCategory = "edm",
            bitrateKbps = 320,
            audioFormat = "EDM Progressive Club Master",
            lyrics = listOf(
                LyricLine(0L, "[Electronic Piano Riff & 128 BPM Kick]"),
                LyricLine(16000L, "Matalbi ho ja zara matlabi"),
                LyricLine(32000L, "Duniya ki sunta hai kyun"),
                LyricLine(48000L, "Sooraj dooba hai yaaron, do ghoont nashe ke maaro"),
                LyricLine(66000L, "Raste bhula do saare gharbaar ke!"),
                LyricLine(84000L, "[Massive Progressive House Drop & Synth Plucks]"),
                LyricLine(115000L, "O sooraj dooba hai yaaron!"),
                LyricLine(145000L, "Kal ki kisko khabar hai, aaj ki raat to amar hai"),
                LyricLine(180000L, "Sooraj dooba hai! Do ghoont nashe ke maaro!"),
                LyricLine(220000L, "[High Energy Festival Synth Finale]")
            )
        ),
        // 17. Jhoome Jo Pathaan
        Track(
            id = "arijit_17",
            title = "Jhoome Jo Pathaan",
            artist = "Arijit Singh & Sukriti Kakar",
            album = "Pathaan",
            durationMs = 208000L,
            genre = "Pop",
            coverGradientStart = 0xFF0F172A,
            coverGradientEnd = 0xFFDC2626,
            iconCategory = "pop",
            bitrateKbps = 320,
            audioFormat = "Action Dance Pop 320kbps",
            lyrics = listOf(
                LyricLine(0L, "[Qawwali Fusion Beats & Modern Synth Brass]"),
                LyricLine(14000L, "Tumne mohabbat karni hai, humne mohabbat ki hai"),
                LyricLine(28000L, "Iss dil ke alawa kisi se na darrte"),
                LyricLine(44000L, "Jhoome jo Pathaan meri jaan"),
                LyricLine(58000L, "Mehfil hi lut jaaye!"),
                LyricLine(72000L, "De de jo zabaan meri jaan, uss pe mar mit jaaye!"),
                LyricLine(92000L, "[Massive Dance Groove & Arabic Oud Riff]"),
                LyricLine(120000L, "Shaan se jeete hain, shaan se marte hain"),
                LyricLine(150000L, "Jhoome jo Pathaan meri jaan!"),
                LyricLine(185000L, "[High-Octane Dance Outro]")
            )
        ),
        // 18. Titliyaan Warga (Cover)
        Track(
            id = "arijit_18",
            title = "Titliyaan Warga",
            artist = "Arijit Singh",
            album = "Acoustic Unplugged Covers",
            durationMs = 230000L,
            genre = "Acoustic",
            coverGradientStart = 0xFFEAB308,
            coverGradientEnd = 0xFFCA8A04,
            iconCategory = "guitar",
            bitrateKbps = 320,
            audioFormat = "Unplugged Acoustic Master",
            lyrics = listOf(
                LyricLine(0L, "[Mellow Fingerpicking & Melancholic Strings]"),
                LyricLine(15000L, "Kade taan tu mainu pehchaan"),
                LyricLine(32000L, "Main hi si teri saari jahaan"),
                LyricLine(50000L, "Jadon de tu badleya ae, dil mera ronda ae"),
                LyricLine(68000L, "Titliyaan warga tu ghumda phire"),
                LyricLine(90000L, "Kade iss phool te, kade uss phool te"),
                LyricLine(115000L, "[Arijit's Soulful Raw Vocal Delivery]"),
                LyricLine(145000L, "Dhokha kitta ae tu mere pyaar naal"),
                LyricLine(175000L, "Titliyaan warga..."),
                LyricLine(210000L, "[Soft Guitar Harmonics End]")
            )
        )
    )
}
