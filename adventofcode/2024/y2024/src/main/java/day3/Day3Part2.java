package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day3Part2 {

    public static final String DEFAULT_INPUT_FILE_SOURCE;

    static {
        try {
            DEFAULT_INPUT_FILE_SOURCE = Paths.get(Objects.requireNonNull(Day3Part2.class.getResource("/day3/input.txt")).toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_INPUT_FILE_SOURCE))) {
            boolean doOperationInPlace = true;
            final var doDoNotRegexPattern = Pattern.compile("(do\\(\\)|don't\\(\\))");
            final var mulOpRegexPattern = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
            String line;
            long accumulatedMul = 0L;

            while ((line = br.readLine()) != null) {
                int lastEnd = 0;
                final var doDoNotRegexMatcher = doDoNotRegexPattern.matcher(line);

                while (doDoNotRegexMatcher.find()) {
                    final var mulOpRegexMatcher = mulOpRegexPattern.matcher(line.substring(lastEnd, doDoNotRegexMatcher.start()));

                    while (mulOpRegexMatcher.find()) {
                        if (doOperationInPlace) {
                            accumulatedMul += Long.parseLong(mulOpRegexMatcher.group(1)) * Long.parseLong(mulOpRegexMatcher.group(2));
                        }
                    }

                    if (doDoNotRegexMatcher.group().equals("don't()")) {
                        doOperationInPlace = false;
                    } else if (doDoNotRegexMatcher.group().equals("do()")) {
                        doOperationInPlace = true;
                    }

                    lastEnd = doDoNotRegexMatcher.end();
                }

                if (lastEnd < line.length()) {
                    final var mulOpRegexMatcher = mulOpRegexPattern.matcher(line.substring(lastEnd));

                    while (mulOpRegexMatcher.find()) {
                        if (doOperationInPlace) {
                            accumulatedMul += Long.parseLong(mulOpRegexMatcher.group(1)) * Long.parseLong(mulOpRegexMatcher.group(2));
                        }
                    }
                }
            }

            System.out.printf("Accumulated mul: %d%n", accumulatedMul);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
