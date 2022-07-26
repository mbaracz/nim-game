package pl.mbaracz.nim.util;

import pl.mbaracz.nim.model.Player;

import java.util.Map;
import java.util.Scanner;

public class InputUtil {

    public static Player askForPlayer(int number, Scanner scanner) {
        System.out.println("Player " + number + ", please enter your name: ");
        return new Player(scanner.next(), true);
    }

    public static String getPile(Player player, Scanner scanner, Map<String, Integer> piles) {
        System.out.println(player.getName() + ", choose a pile: ");

        String pile = scanner.next().toUpperCase();
        if (!piles.containsKey(pile)) {
            System.out.println("That pile does not exists. Choose again.");
            return getPile(player, scanner, piles);
        }
        if (piles.get(pile) == 0) {
            System.out.println("Nice try, " + player.getName() + ". That pile is empty. Choose again.");
            return getPile(player, scanner, piles);
        }
        return pile;
    }

    public static int getCount(String pile, Scanner scanner, Map<String, Integer> piles) {
        System.out.println("How many to remove from pile " + pile + ": ");

        int count = scanner.nextInt();
        if (count < 1) {
            System.out.println("You must choose at least one. Choose again.");
            return getCount(pile, scanner, piles);
        }
        if (count > piles.get(pile)) {
            System.out.println("Pile " + pile + " doesn't have that many. Choose again.");
            return getCount(pile, scanner, piles);
        }
        return count;
    }
}
