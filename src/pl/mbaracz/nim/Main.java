package pl.mbaracz.nim;

import pl.mbaracz.nim.config.Config;
import pl.mbaracz.nim.model.Game;
import pl.mbaracz.nim.model.Player;
import pl.mbaracz.nim.util.InputUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player1, player2;
        if (Config.HUMAN_FIRST_MOVE_WITH_COMPUTER) {
            player1 = InputUtil.askForPlayer(1, scanner);
            player2 = Config.GAME_WITH_COMPUTER ? new Player(Config.COMPUTER_NAME, false) : InputUtil.askForPlayer(2, scanner);
        } else {
            player1 = Config.GAME_WITH_COMPUTER ? new Player(Config.COMPUTER_NAME, false) : InputUtil.askForPlayer(1, scanner);
            player2 = InputUtil.askForPlayer(2, scanner);
        }
        new Game(scanner, player1, player2).start();
    }
}
