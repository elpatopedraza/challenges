package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day3Part1 {

    public static final String DEFAULT_INPUT_FILE_SOURCE;

    static {
        try {
            DEFAULT_INPUT_FILE_SOURCE = Paths.get(Objects.requireNonNull(Day3Part1.class.getResource("/day3/input.txt")).toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_INPUT_FILE_SOURCE))) {
            final var regexPattern = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
            String line;
            long accumulatedMul = 0L;

            while ((line = br.readLine()) != null) {
                final var regexMatcher = regexPattern.matcher(line);

                while (regexMatcher.find()) {
                    accumulatedMul += Long.parseLong(regexMatcher.group(1)) * Long.parseLong(regexMatcher.group(2));
                }
            }

            System.out.printf("Accumulated mul: %d%n", accumulatedMul);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
