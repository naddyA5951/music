# 🎵 Music Libreriya – Audiophile Music Player & Offline Studio

**Music Libreriya** is a high-fidelity music streaming and offline player for Android, built with Jetpack Compose, Kotlin DSL, and Material 3. It features a rich catalog of 100 studio-quality tracks, synchronized karaoke lyrics, a built-in 5-band graphic equalizer with DSP effects, custom playlist management, social sharing, and seamless cross-platform syncing.

---

## 🌟 Key Features

1. **Complete 100-Song Catalog**:
   - **Ustad Nusrat Fateh Ali Khan (NFAK)**: *Tum Ek Gorakh Dhanda Ho, Allah Hoo Allah Hoo, Ali Maula Ali Dam Dam, Nit Khair Manga, Dam Mast Qalandar, Kinna Sohna Tenu Rab Ne Banaya, Mera Dholan Mahi, Akhian Udeekdiyan, Mast Nazron Se, Sanu Ek Pal Chain Na Aave, Yeh Jo Halka Halka Suroor Hai*
   - **Arijit Singh**: *Channa Mereya, Tum Hi Ho, Agar Tum Saath Ho, Kabira, Hamari Adhuri Kahani, Phir Le Aya Dil, Khairiyat, Apna Bana Le, Kesariya, O Bedardeya, Tera Fitoor, Rait Zara Si, Enna Sona, Hawayein, Chaleya, Sooraj Dooba Hain, Jhoome Jo Pathaan, Titliyaan Warga*
   - **Asim Azhar**: *Tera Woh Pyar, Jo Tu Na Mila, Sohneya, Tasveer, Habibi, Sajnaa, Sassi, Humraah, Mahi Aaja, Sohniye, Yaad*
   - **Talha Anjum / Young Stunners**: *Afsos, Guman, Quarantine, Down Time, Open Letter, Agency, Touch Base, 100 Letters, Lost & Found, Afsane, Karachi Mentality, Why Not, Benz*
   - **Bilal Saeed**: *12 Saal, Baari, Adhi Adhi Raat, Chootha, Mahi Mahi, Lethal Combination, No Love, Blah Blah, Uff Yeh Noor, Kaif-o-Suroor, Teri Khair Mangdi*
   - **Coke Studio & Pakistani Hits**: *Pasoori, Tajdar-e-Haram, Afreen Afreen, Kana Yaari, Kahani Suno 2.0, Tu Jhoom, Sayonee, Dil Dil Pakistan, Woh Lamhe, Faasle, Peechay Hutt, Ghalat Fehmi, Billo De Ghar, Aaye Na Balam*
   - **Audiophile Studio Master Series**: Synthwave, Lo-Fi, Acoustic, Cyber Ambient, Classical Piano, Jazz Lounge, and Progressive EDM.

2. **100% Offline Playback & Download Engine**:
   - Save any song directly to local storage (`audio_downloads/`) with a single tap.
   - Dedicated **Offline Mode** toggle pill in the header for zero-latency, internet-free listening.
   - **Device Audio Scanner**: Scan and play your own local MP3, WAV, and FLAC files directly from your phone.

3. **Live Synchronized Lyrics (Karaoke Mode)**:
   - Real-time line-by-line scrolling lyrics in Roman Urdu and English.
   - Tap any lyric line to jump playback to that exact second.

4. **Built-in 5-Band Graphic Equalizer**:
   - 5 interactive frequency bands: 60Hz, 230Hz, 910Hz, 3.6kHz, 14kHz (+/- 12 dB).
   - Dedicated **Bass Boost** (0–100%) and **3D Spatial Virtualizer** (0–100%).
   - 8 Sound Presets: *Flat, Audiophile HiFi, Bass Booster, Electronic, Rock, Vocal / Acoustic, Jazz, Deep Lounge*.

5. **Streaming Quality Selector**:
   - Audiophile FLAC (1411 kbps), Master Studio (320 kbps), Standard Hi-Fi (192 kbps), Data Saver (96 kbps).

6. **Custom Playlists & Room Database**:
   - Create, edit, and organize custom mixes with emoji badges and custom gradients.
   - Offline-first persistence via Room Database.

7. **Cross-Platform Sync & Social Sharing**:
   - Generate shareable lyrics quote cards and share songs via the Android system share sheet (`ACTION_SEND`).
   - Portable JSON library export and restore across iOS, Desktop, and Android.

---

## 🚀 How to Launch on GitHub & Download APK

You do **not** need a Google Play Store developer account to run or distribute Music Libreriya.

### Method 1: Push to GitHub from Google AI Studio Build
1. In the **Google AI Studio Build** web interface, locate the top navigation / header bar.
2. Click the **GitHub** button (or open the project settings menu `⋮` and select **Push to GitHub** / **Export to GitHub**).
3. Connect your GitHub account (`naveedalicodes1@gmail.com`).
4. Select your destination repository (e.g. `music-libreriya`) and confirm the push.
5. All code, icons, tests, and the `.github/workflows/build-apk.yml` file will be pushed to your GitHub repository!

### Method 2: Automatic APK Generation on GitHub (GitHub Actions)
Once pushed to GitHub:
1. Navigate to the **Actions** tab in your GitHub repository.
2. The workflow **"Build Music Libreriya APK"** will run automatically.
3. Once finished, click on the workflow run. Under **Artifacts**, click **`Music-Libreriya-Debug-APK`** to download your ready-to-install `.apk` file!
4. An automated GitHub Release (`v1.0.0`) will also be created with the APK attached.

### Method 3: Direct APK Export from AI Studio
1. In AI Studio, open the project menu in the top right.
2. Select **Export as ZIP** or **Download APK**.
3. Transfer the `.apk` to your Android phone.

---

## 📲 How to Sideload & Install the APK on Android

1. Transfer or download the generated `app-debug.apk` onto your Android phone.
2. Tap the `.apk` file in your phone's **Files / Downloads** app.
3. If prompted with *"For your security, your phone is not allowed to install unknown apps from this source"*:
   - Tap **Settings**.
   - Toggle **Allow from this source** to **ON**.
4. Tap **Install**.
5. Open **Music Libreriya** and enjoy your audiophile music collection offline and online!

---

## 🛠️ Build Locally (for Developers)

```bash
# Clone the repository
git clone https://github.com/your-username/music-libreriya.git
cd music-libreriya

# Run unit tests
gradle :app:testDebugUnitTest

# Assemble debug APK
gradle :app:assembleDebug
```
The output APK will be available in:
`app/build/outputs/apk/debug/app-debug.apk`
