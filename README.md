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

- Install Android Studio version Otter 3 Feature Drop 2025.2.3.
- Clone the repository in android studio:New->Project From version.
  Control https://github.com/2026-DEV2-032/TicTacToe.git.
- Configure JDK 11 in Android Studio (File > Settings > Build Tools > Gradle).
- Ensure Android SDK 36 is installed via SDK Manager.
- Recommended emulator: Pixel 6a with Android 16 / API 36 “Baklava” | x86_64
- Sync Project with gradle files.
- To build the debug APK: Gradle icon-> Tic Tac Toe-> Tasks ->build -> build
- To install and run on a connected device or emulator: Gradle icon-> Tic Tac Toe-> Tasks ->
  install ->installDebug

### Using Command Line

- To build the debug APK `./gradlew assembleDebug`
- To install and run on a connected device or emulator `./gradlew app:installDebug`

## Implementation Details

- Built with Kotlin and Jetpack Compose
- Uses MVVM architecture pattern

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
│       ├── java/com/bpn/toctactoe/
│       │   ├── data/           # Data layer (repositories)
│       │   ├── domain/         # Domain layer
│       │   │   ├── model/      # Domain models (e.g., Stage, Performer)
│       │   │   ├── rules/      # Rules related to game logic. Implementation in progress
│       │   │   └── usecase/    # Business logic (use cases)
│       │   └── presenter/      # UI layer (ViewModel, UI models, etc.)
│       └── res/               # Android resources
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

## Crafting logs

I realised after 3 days that I have not respected the crafting commit requirements. To stay
transparent, I am documenting retrospectively the work done during the first three days, and will
continue with incremental commits from now on.

What has been done so far (checkWinner logic still in progress):

- Set up anonymous Proton email and GitHub repository.(ex: no verification code received after
  captcha)
- Create base architecture packages presentation/domain/data.
- Considered using multi-modules to enforce the separation of concern but this is a small project,
  it is
  not recommended.
- Reflecting on the domain models and business logic.
- GameBoard: started with the Board and takeCell on the BoardCells. Made the board size
  configurable.bad
  decision to take 2DArray. The idea was that later in the check winner, in perspective of extension
  board and winCombination, it sounds closer to the physical reality.
- Player : Considered if the design should support extension for more players. Named the boardCell
  players' value “character” (pawn might have been a better word). Decided to first progress and if
  I have time to come back.
- Faced difficulties finding the appropriate english wording to describe the game "materials".
- Implemented PlayTurnUseCase ( initially named PlaceUseCase, to place a player character on the
  board),
  validated cell selection and use takeCell on the board, added unit tests.
- Wanted to use Junit5 but faced compatibility issues with the latest Gradle version and Decided not
  to block progress and postpone this.
- Considered adding CI ( never used Github CI before), it seems easy to setup but given the time
  constraint (“a few hours expected”), I postponed it.
- Started presentation layer to visualize progress. GameUiState contains all
  UI relevant data. GameUiAction is a sealed interface for user actions. GameViewModel exposes the
  GameUiState and the GameScreen observes GameUiState.
  GameUiState is immutable to avoid unnecessary recomposition. in GameViewModel,
  state is always copy or update in order that jetpack compose detects the new UIGameState.
- Ran into recomposition issues caused by the 2D array structure.
- GameScreen, used LazyVerticalGrid to easy map cell clicks and to limit recomposition. Created
  GameUIBoard and a mapping logic from domain board to UI board.Added an Int extension function to
  map BoardItem index to BoardCell position.
- Need to inject PlayTurnUseCase to ViewModel. Used a manual ViewModelProvider.Factory. I watched
  several tutorials about Koin last years, but since I haven’t practiced it yet, I
  decided not to use it. If time allowed, I would migrate to Koin
- Retrospectively, I realized I should have started by creating a branch for each feature,
  similar to using a Jira ticket workflow (with ticket branch). This would have made the crafting
  process clearer and commits more incremental.
- The more I reflect on the past three days, the more I realize I approached it like an Advent of
  Code challenge, enjoying the game competition itself and sometimes losing sight of the main
  objective.

From this commit point, commits will reflect incremental crafting steps.

## Lessons learned

- Requesting the exercise in the morning helps you sleep better and start fresh.
- Having an anonymous email and github upfront, disconnect personal GitHub to avoid
  confusion.
- Follow usual work habits by creating a branch per feature from the start.
- Prioritize simplicity first: is better than over-engineering under tight time constraints.
  However,I decided to focus on completing the functionality first, even if it means some
  over-engineering.
- The more I reflect on the past three days, the more I realize I approached it like an Advent of
  Code challenge, enjoying the game competition itself and sometimes losing sight of the main
  objective.
- Coming from Atlassian suite workflows (Jira stories/bugs mapped to branches and commits), I
  realized I can
  apply the same structured approach on GitHub which I’m now practicing while learning GitHub more
  deeply.