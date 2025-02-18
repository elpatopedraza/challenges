package day2;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day2Part2 {

    public static final String DEFAULT_INPUT_FILE_SOURCE;

    static {
        try {
            DEFAULT_INPUT_FILE_SOURCE = Paths.get(Objects.requireNonNull(Day2Part2.class.getResource("/day2/input.txt")).toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_INPUT_FILE_SOURCE))) {
            int safeReports = 0;
            String line;

            while ((line = br.readLine()) != null) {
                final var levels = Arrays.stream(line.trim().split(StringUtils.SPACE)).map(Integer::valueOf).toArray(Integer[]::new);

                if (isSafeReport(levels)) {
                    safeReports ++;
                } else {
                    for (int i = 0; i < levels.length; i++) {
                        if (isSafeReport(ArrayUtils.remove(levels, i))) {
                            safeReports ++;
                            break;
                        }
                    }
                }
            }

            System.out.printf("Safe reports: %d%n", safeReports);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isSafeReport(Integer[] levels) {
        boolean safeReport = true;
        boolean increasing = false;

        if (levels.length > 1) {
            for (int i = 1; i < levels.length; i++) {
                if (i == 1 && levels[i] > levels[i - 1]) {
                    increasing = true;
                }

                if ((increasing && levels[i] < levels[i - 1]) || (!increasing && levels[i] > levels[i - 1])) {
                    safeReport = false;
                    break;
                }

                final var diff = Math.abs(levels[i] - levels[i - 1]);

                if (diff < 1 || diff > 3) {
                    safeReport = false;
                    break;
                }
            }
        } else {
            safeReport = false;
        }

        return safeReport;
    }
}
