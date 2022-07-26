package pl.mbaracz.nim.model;

import pl.mbaracz.nim.config.Config;
import pl.mbaracz.nim.util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private static final Map<String, Integer> PILES = new HashMap<>();
    private final List<Player> players;
    private final Scanner scanner;

    static {
        PILES.put("A", 3);
        PILES.put("B", 4);
        PILES.put("C", 5);
    }

    public Game(Scanner scanner, List<Player> players) {
        this.scanner = scanner;
        this.players = players;
    }

    public Game(Scanner scanner, Player... players) {
        this(scanner, Arrays.asList(players));
    }

    public void start() {
        int index = 0;

        boolean early = false;

        while (!checkGameEnd()) {
            Player player = players.get(index);

            Config.DISPLAY_MODE.printFormattedData(PILES);

            if (checkLoss()) {
                System.out.println(player.name + ", you must take the last remaining counter, so you lose. " + players.get(index ^ 1).name + " wins!");
                early = true;
                break;
            }
            if (player.human) {
                String pile = InputUtil.getPile(player, scanner, PILES);
                int count = InputUtil.getCount(pile, scanner, PILES);
                PILES.merge(pile, -count, Integer::sum);
            } else {
                makeComputerMove();
            }
            index ^= 1;
        }
        if (!early) {
            System.out.println(players.get(index).name + ", there are no counters left, so you WIN!");
        }
    }

    public boolean checkGameEnd() {
        return PILES.values()
                .stream()
                .noneMatch(it -> it != 0);
    }

    public boolean checkLoss() {
        String pattern = PILES.values()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return Arrays.asList("001", "010", "100").contains(pattern);
    }

    public int calcNimSum() {
        return (PILES.get("A") ^ PILES.get("B")) ^ PILES.get("C");
    }

    public void makeComputerMove() {
        // Amount of piles to remove
        int amount = 0;

        // Pile to remove from
        String pile = null;

        // Check how many piles are greater than one to see if you can reduce to odd number of 1-piles
        int largePile = (int) PILES.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .count();

        // Find pile with max value
        Map.Entry<String, Integer> maxEntry = PILES.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(NullPointerException::new);

        if (largePile <= 1) {
            // Count non-zero piles
            int nonZeroPiles = (int) PILES.values()
                    .stream()
                    .filter(it -> it > 0)
                    .count();

            // Leave one counter to make odd number of 1-item piles or remove all counters if an even num of piles remains
            amount = nonZeroPiles % 2 == 1 ? maxEntry.getValue() - 1 : maxEntry.getValue();
            pile = maxEntry.getKey();
        } else {
            int sum = calcNimSum();

            for (Map.Entry<String, Integer> entry : PILES.entrySet()) {
                int xor = entry.getValue() ^ sum;

                if (xor < entry.getValue()) {
                    pile = entry.getKey();
                    amount = entry.getValue() - xor;
                }
            }
            // Remove one from the largest pile if no useful move found
            if (pile == null) {
                pile = maxEntry.getKey();
                amount = 1;
            }
        }
        System.out.printf("Computer makes move: took %s counters from %s pile%n", amount, pile);
        PILES.merge(pile, -amount, Integer::sum);
    }
}