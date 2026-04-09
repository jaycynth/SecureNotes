# SecureNotes - Common Security Issues

This project is a note-taking app built with modern Android development practices (MVI, Clean Architecture, Dagger Hilt, Jetpack Compose) but intentionally includes security vulnerabilities for demonstration and testing with SAST tools like MobSF/mobsfscan.

## Project Structure
- **MVI Architecture**: State-driven UI updates.
- **Clean Architecture**: Separation of concerns between Data, Domain, and Presentation layers.
- **Dagger Hilt**: Dependency injection.
- **Room Database**: Local storage for notes.
- **Jetpack Compose**: Modern UI toolkit.

## Intentional Vulnerabilities (Version 1: Insecure Baseline)
The following issues are baked into this version of the app:
1. **Hardcoded Secrets**: A fake API key is stored in `app/build.gradle.kts` under the debug build type.
2. **Insecure Logging**: Sensitive note content is printed to Logcat in `NotesViewModel.kt` during deletion.
3. **Naive WebView Configuration**: The Settings screen contains a WebView with JavaScript enabled and file access allowed, which are common entry points for attacks.
4. **Plaintext Storage**: Notes are saved in a standard SQLite database (via Room) without encryption.

## Development Roadmap
- **Version 1**: Insecure baseline (Current).
- **Version 2**: Pipeline integrated, failing on critical findings.
- **Version 3**: Issues fixed (Encrypted Room, Proguard/R8 rules, secure WebView, removed logs), merge allowed.

## How to Run
1. Sync project with Gradle.
2. Run the `app` module on an emulator or physical device.
3. Use MobSF or mobsfscan to analyze the source code or the generated APK.
