# The Nim Game (Misère version)
Nim is a mathematical game of strategy in which two players take turns removing objects from distinct heaps or piles.
On each turn, a player must remove at least one object, and may remove any number of objects provided they all come from the same heap or pile.
Depending on the version being played, the goal of the game is either to avoid taking the last object or to take the last object.

Source: https://en.wikipedia.org/wiki/Nim

## Rules of the game
1. Start by placing counters into 3 piles.
2. Player #1 picks a pile, then removes one or more counters from that pile. (It's okay to take the whole pile)
3. Player #2 picks a pile, then removes one or more counters from that pile.
4. Player #1 plays again. (It's okay to choose a different pile this time)
5. Whichever player is forced to take the **last** counter is the **LOSER**.

## Configuration
If you want to change some game settings, visit [**Config**](src/pl/mbaracz/nim/config/Config.java) file and change them.

```java
public class Config {

    public static final boolean HUMAN_FIRST_MOVE_WITH_COMPUTER = false;

    public static final boolean GAME_WITH_COMPUTER = true;

    public static final String COMPUTER_NAME = "Computer";

    public static final String FANCY_DISPLAY_CHAR = "*";

    public static final DisplayMode DISPLAY_MODE = DisplayMode.COLUMNS;
}

```