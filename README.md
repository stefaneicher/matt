# Matt – Kinderbonus-App 🌟

Ein spielerisches Familien-Bonus-System für Android. Eltern vergeben Aufgaben mit Punkten (1–10), Kinder sammeln Punkte und können diese für Aktionen einlösen. Ereignisse werden als animierter Feed (Event Scroll) mit Lottie-Animationen visualisiert.

## Architektur

```
app/
├── data/          # Room DB, Firebase, Repository-Implementierungen
├── domain/        # Modelle, Repository-Interfaces, Use Cases
└── presentation/  # Jetpack Compose UI, ViewModels, Navigation
```

**Tech Stack:**
- Kotlin + Jetpack Compose
- Clean Architecture (Data / Domain / Presentation)
- Hilt (Dependency Injection)
- Room (lokale Datenbank)
- Firebase Auth + Firestore (Backend)
- Firebase Cloud Messaging (Push-Benachrichtigungen)
- Lottie (AI-Animationen)
- Coroutines + Flow

## Domain-Modell

| Entität | Beschreibung |
|---------|-------------|
| `User` | Eltern oder Kind, verknüpft mit einer Familie |
| `Family` | Gruppiert 1–2 Eltern und mehrere Kinder |
| `Task` | Aufgabe mit 1–10 Punkten, von Eltern erstellt |
| `Action` | Einlösung mit Punkteabzug, von Eltern erstellt |
| `FamilyEvent` | Ereignis im Feed (Aufgabe erledigt, Aktion eingelöst, Bonus) |
| `PointAccount` | Punktekonto je Kind (Guthaben, verdient, ausgegeben) |
| `Character` | Spielcharakter, freischaltbar ab bestimmtem Punktestand |

## Rollen und Rechte

- **Eltern**: Aufgaben und Aktionen erstellen, Familie verwalten, Ereignisse bestätigen
- **Kinder**: Aufgaben abhaken, Aktionen einlösen, Event-Feed abspielen

## CI/CD

### Continuous Integration
Bei jedem Push auf `main`/`develop` und bei Pull Requests:
- Lint-Analyse
- Unit-Tests
- Debug-APK-Build

### Preview Deploy
- Workflow: **Preview Deploy**
- Trigger: Pull Request auf `main`/`develop` oder manuell via `workflow_dispatch`
- Ergebnis: Downloadbares Artifact `preview-apk` (Debug-APK, 7 Tage verfügbar)
- Zusätzlich: Artifact `preview-screenshots` mit Paparazzi-Screenshots und HTML-Report für UI-Visualisierung im Review
- Compose-Previews und Snapshot-Tests sind aktuell für `HomeScreen` und `CharactersScreen` eingerichtet

### Continuous Delivery
Bei Tag-Push (`v*.*.*`) oder manuell via `workflow_dispatch`:
1. Release-AAB signieren
2. Unit-Tests
3. Upload zu Google Play (Standard: `internal` Track)
4. Promotion-Lanes: `internal → alpha → beta → production`

### Benötigte GitHub Secrets
| Secret | Beschreibung |
|--------|-------------|
| `KEYSTORE_BASE64` | Base64-kodierter Android Keystore |
| `KEY_ALIAS` | Keystore-Alias |
| `KEY_PASSWORD` | Schlüssel-Passwort |
| `STORE_PASSWORD` | Keystore-Passwort |
| `PLAY_SERVICE_ACCOUNT_JSON` | Google Play Service-Account JSON |

## Setup

```bash
# Abhängigkeiten installieren
./gradlew dependencies

# Debug-Build
./gradlew assembleDebug

# Tests ausführen
./gradlew test

# Release-Bundle bauen (Keystore-Umgebungsvariablen setzen)
./gradlew bundleRelease
```

## Firebase Setup

1. Firebase-Projekt erstellen unter https://console.firebase.google.com
2. Android-App registrieren (Package: `com.stefaneicher.matt`)
3. `google-services.json` herunterladen und nach `app/` legen
4. Authentication und Firestore aktivieren

## Roadmap

- **Phase 1 (MVP):** Accounts/Familie, Tasks, Punktekonto, einfache Ereignisse ✅
- **Phase 2:** Aktionen mit Punkteabzug, Charaktere, erweiterter Event-Scroll
- **Phase 3:** AI-Animationen, Playback-Backlog, Push-Benachrichtigungen
- **Phase 4:** Skalierung, Analytics, Live-Optimierung
