# 🏅 Kreeda-Prerana Scout

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-UI-blue.svg)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-orange.svg)](LICENSE)

An elite, glassmorphic athlete performance analytics and scouting companion built for modern athletic coordinators and trainers. **Kreeda-Prerana Scout** streamlines individual stopwatch logging, batch-entry imports, and dynamic leaderboard sorting to scout state and district level athletic talents.

---

## 📌 Problem Statement
Traditional sports talent identification systems are slow, manual, and rely on paper scoring, resulting in missed athletic potential. Trainers lack real-time digital logging tools that automate achievement classifications (e.g., District/State limits) and dynamic leaderboard rankings on the field. 

**Kreeda-Prerana Scout** solves this by providing a premium, offline-first digital scouting hub with custom stopwatch tools, quick CSV-style batch uploads, and visual target indicators to identify top athletes instantly.

---

## ✨ Features
* **💎 Glassmorphic & Premium UI**: Designed with high-contrast, modern aesthetics, sleek elevations, and responsive micro-interactions built on Jetpack Compose.
* **⏱️ Integrated Precision Stopwatch**: Real-time elapsed timing logs built into individual athlete cards.
* **📊 Live Leaderboard Dynamics**: Real-time sorting algorithms that auto-rank athletes based on their best timing records.
* **📝 Fast Batch Entry**: Input a comma-separated list of athlete names for rapid onboarding on the track field.
* **🏅 Smart Talent Badges**: Automatic evaluation of qualification standards (e.g., *District Level Ready* for times under 12.0 seconds).
* **📈 Talent Curve Analytics**: Sequential performance logs tracking progress from trial to trial.

---

## 🛠️ Tech Stack & Architecture
* **Language**: Kotlin 1.9
* **UI Toolkit**: Jetpack Compose (Material Design 3)
* **Architecture**: Model-View-Intent (MVI) inspired declarative components
* **Reactive Components**: State Hoisting, Composable Flow API, LaunchedEffects Coroutines

---

## 📁 Repository Structure
```
KreedaScout/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/kreedaprerana/
│   │   │   │   ├── MainActivity.kt                # Host Activity Setup
│   │   │   │   ├── data/
│   │   │   │   │   └── Athlete.kt                 # Athlete Data Model
│   │   │   │   └── ui/
│   │   │   │       ├── KreedaPreranaUI.kt         # Main Dashboard Container
│   │   │   │       ├── components/
│   │   │   │       │   ├── AddAthleteCard.kt      # Single Athlete Form
│   │   │   │       │   ├── BatchEntryCard.kt      # Quick Batch Onboarding
│   │   │   │       │   └── AthleteCardUI.kt       # Live Stop Watch Card
│   │   │   │       └── theme/                     # Premium Custom Color Systems
│   │   │   └── res/                               # Android UI Assets & Drawables
│   │   └── build.gradle.kts                       # App-level dependencies
│   └── build.gradle.kts                           # Project-level dependencies
├── gradlew                                        # Unix Gradle Executable
├── gradlew.bat                                    # Windows Gradle Executable
└── README.md                                      # Documentation Hub
```

---

## 🚀 Setup & Installation

### Prerequisites
* **JDK 17** installed.
* **Android Studio Hedgehog** (or later) recommended.

### Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/shivuaishu2004-eng/KreedaPrerana.git
   cd KreedaPrerana
   ```

2. **Open in Android Studio**
   - File -> New -> Import Project -> Select `KreedaScout` directory.
   - Let Gradle sync and download build dependencies.

3. **Install & Run via CLI**
   To compile and run directly on a connected device or active emulator:
   ```bash
   ./gradlew installDebug
   ```

4. **Verify / Run Local Build Checks**
   To test compilation confidence marks locally:
   ```bash
   ./gradlew assembleDebug
   ```

---

## 📸 Interface Screenshots & Demo
*(Interactive visuals representing the premium interface elements)*

| Add Athlete & Batch Input | Active Trial Timer | Live Dynamic Leaderboard |
| :---: | :---: | :---: |
| `[Card: Full Form Input]` | `[Timer: 10.45s Running]` | `[Badge: 🏅 District Level Ready]` |

> **Interactive Demo**: Live App demonstration video or APK link will be deployed here soon.

---

## 🔮 Future Improvements Roadmap
* **🗄️ Room Database Integration**: Implement local SQL persistence to retain athlete records across app launches.
* **📥 PDF Scouting Report Generator**: Generate custom-designed scouting analytics summaries.
* **📈 Charting Curves**: Integrate Canvas API curves to plot individual time drops over time.
* **🔔 Cloud Backup Sync**: Cloud-based sync for coaching teams.
