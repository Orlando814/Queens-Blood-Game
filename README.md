# Queen's Blood

A Java implementation of Queen's Blood, the turn-based strategy card game from
Final Fantasy VII Rebirth. Two players compete to control a 5×7 board by placing
cards that claim territory and accumulate points; the player with the higher
score across contested rows wins.

The project is built around a clean Model–View–Controller architecture, a
publisher–subscriber event system connecting the layers, and pluggable AI
strategies that can play against a human or against each other.

## Features

- **Full MVC architecture** — the model owns all game state and rules, the view
  renders it, and the controller mediates between them with no business logic
  leaking across layers.
- **Swing GUI** — a graphical board, hand, and card display with mouse-driven
  card selection and placement.
- **Textual view** — a console rendering of the same model, useful for testing
  and headless play.
- **Publisher–subscriber event system** — the model notifies listeners on turn
  changes and game-over; the view and controller subscribe rather than polling,
  keeping the layers decoupled.
- **Pluggable AI strategies** — strategies implement a common interface so new
  ones can be dropped in:
  - *First-fit*: places the first playable card in the first available cell.
  - *Greedy / maximize-row*: chooses the move that most increases the player's
    row score relative to the opponent's.
  - *Pass*: yields the turn.
- **Configurable decks** — decks are defined in plain-text config files and
  loaded at startup.
- **Test suite** — unit and integration tests across the model, view, and
  controller, including mock objects (e.g. an `Appendable` that throws on write)
  to exercise error handling and a machine-vs-machine integration test.

## Architecture

| Layer | Responsibility |
|-------|----------------|
| **Model** (`BasicSanguine`) | Owns board state, decks, scoring, and rules. Self-contained — depends on no other layer. |
| **View** (`SanguineGuiView`, `SanguineTextualView`) | Renders the current model state. Driven by the model; cannot exist without it. |
| **Controller** (`SanguineController`) | Starts the game via `playGame()` and routes player actions to the model. |

Key supporting types include `ReadCardFile` (parses deck config files into
`BasicCard`s), `DeckCreator` (builds per-player decks), and `BoardInput`
(represents what occupies a cell: a card, influence, or nothing).

The view and controller communicate with the model through listener interfaces
(`ModelFeaturesListener` for turn/game-over events, `PlayerActionsListener` for
moves), so machine and human players flow through the same code path — a machine
player publishes its chosen move to the view, which then drives the controller
exactly as a human click would.

## Build and Run

This is a Gradle project. Build the jar with:

```
./gradlew build
```

Then run from the project root (so the deck config files resolve correctly):

```
java -jar build/libs/queens-blood.jar <rows> <cols> <redDeck> <blueDeck> <player1> <player2>
```

Example — a 5×7 game with two human players:

```
java -jar build/libs/queens-blood.jar 5 7 config/red.deck config/blue.deck human human
```

Example — a human against the first-fit AI:

```
java -jar build/libs/queens-blood.jar 5 7 config/red.deck config/blue.deck human strategy1
```

### Arguments

| Position | Argument | Notes |
|----------|----------|-------|
| 1 | Rows | Board height (e.g. `5`) |
| 2 | Columns | Board width (e.g. `7`) |
| 3 | Red deck path | e.g. `config/red.deck` |
| 4 | Blue deck path | e.g. `config/blue.deck` |
| 5 | Player 1 | `human`, `strategy1` (first-fit), or `strategy2` (maximize-row) |
| 6 | Player 2 | same options as Player 1 |

## Project Structure

```
src/main/java/sanguine/
  controller/   game flow, player actions, listeners
  model/        game state, rules, scoring
    card/       card representation and parsing
    cell/       board cells and players
    deck/       deck construction
  strategy/     AI move strategies
  view/         GUI and textual rendering
src/test/java/sanguine/
  ...           mirrors main, with mocks for controller/strategy testing
config/         deck definitions
images/         screenshots
```

## Contributors

- [Orlando LuPone](https://github.com/Orlando814)
- [Sean Snaider](https://github.com/SeanSnaider)

## Acknowledgment

Queen's Blood is a minigame from *Final Fantasy VII Rebirth* by Square Enix.
This is an independent, non-commercial reimplementation built as a learning
project; it is not affiliated with or endorsed by Square Enix.
