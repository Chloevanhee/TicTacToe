# Tic Tac Toe

## About this Kata test context

This Kata is an applying test. It is normally expected be performed using TDD.
I am familiar with the TDD theory but I have limited practical experience.
After discussing with the Chapter Lead, it was advised that:

- Not strictly following TDD in this case is acceptable and should not negatively impact the code
  review, provided it is clearly communicated.
- UI design is not priority but jetpack compose must be used.
- Crafting and committing all along in order to follow the thinking and development process

Sources : [Tic tac toe](https://github.com/stephane-genicot/katas/blob/master/TicTacToe.md)

## Rules

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
    - One player has three in a row, horizontally, vertically or diagonally
    - All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

## Build and Run

### Using Android Studio

- Install Android Studio version Ladybug Feature Drop (or newer).
- Clone the repository in android studio:New->Project From version.
  Control https://github.com/Chloevanhee/TicTacToe.git.
- Configure JDK 17 (recommended for latest Gradle) or JDK 11 in Android Studio.
- Ensure Android SDK 36 is installed via SDK Manager.
- Recommended emulator: Pixel 6a with Android 16 / API 36 “Baklava” | x86_64
- Sync Project with gradle files.
- To build the debug APK: Gradle icon-> Tic Tac Toe-> Tasks ->build -> build
- To install and run on a connected device or emulator: Gradle icon-> Tic Tac Toe-> Tasks ->
  install ->installDebug

### Using Command Line

- To build the debug APK: `./gradlew assembleDebug`
- To install and run on a connected device or emulator: `./gradlew app:installDebug`

## Implementation Details

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture principles
- **Dependency Injection**: Koin
- **Testing**: JUnit 5, AssertK, Coroutines Test

## Demo

The demo shows:

- A draw party
- A winner party

![Tic Tac Toe app video preview](https://github.com/2026-DEV2-032/TicTacToe/blob/main/docs/videos/demo.gif)

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/bnp/tictactoe/
│       │   ├── di/             # Koin modules definitions
│       │   ├── domain/         # Domain layer
│       │   │   ├── models/     # Domain models
│       │   │   ├── rules/      # Rules related to game logic. Implementation in progress
│       │   │   └── usecases/   # Business logic (use cases)
│       │   ├── presentation/   # UI layer (ViewModel, Compose Screens)
│       │   └── ui/theme/       # Compose theme and styling
│       └── res/                # Android resources
docs/                          # Documentation and demo materials
```
## Applying Requirements

- The commits are exposing the crafting process.
- No external review or help of colleagues during the process.
- Anonymous github repo.
- UI jetpack Compose.
- README.md provided ( how to compile and run this code).
- Application runs and fulfill the requirements.
- Produce the best code you can possibly provide to us.
- Bonus Ci.
- Bonus scalable design.

## Applying Assessment

- Code quality.
- Best practice.
- discussion about the choices.
- overall approach to solve the problem.
- Allow to take 5 to 7 days.

## Mindset

- MVVM, single source of truth, unidirection data flow
- Clean architecture, separation of concern
- SOLID
- Test coverage and testability
- Performance
- Bonus ci
- Bonus scalability
- We expect you to spend up to a few hours on this
- Allow to take 5 to 7 days
