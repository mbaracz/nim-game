package pl.mbaracz.nim.display;

import pl.mbaracz.nim.config.Config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum DisplayMode implements DisplayTrait {

    COLUMNS {
        @Override
        public void printFormattedData(Map<String, Integer> data) {
            int max = Collections.max(data.values());

            Map<String, Integer> clone = new HashMap<>(data);

            Function<String, String> mapper = (key) -> {
                if (clone.get(key) <= 0) {
                    return " ";
                }
                clone.merge(key, -1, Integer::sum);
                return Config.FANCY_DISPLAY_CHAR;
            };

            System.out.println(System.lineSeparator() + "A B C");
            for (int i = 0; i <= max; i++) {
                System.out.printf("%s %s %s %n", mapper.apply("A"), mapper.apply("B"), mapper.apply("C"));
            }
        }
    },

    ROWS {
        @Override
        public void printFormattedData(Map<String, Integer> data) {
            System.out.println();
            data.forEach((k, v) -> System.out.println(k + ": " + String.join("", Collections.nCopies(v, Config.FANCY_DISPLAY_CHAR))));
            System.out.println();
        }
    },

    COUNT {
        @Override
        public void printFormattedData(Map<String, Integer> data) {
            System.out.printf("%nA: %s  B: %s  C: %s%n", data.get("A"), data.get("B"), data.get("C"));
        }
    }
}