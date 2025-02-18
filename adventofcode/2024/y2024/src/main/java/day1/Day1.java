package day1;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Day1 {

    public static final String DEFAULT_INPUT_FILE_SOURCE;

    static {
        try {
            DEFAULT_INPUT_FILE_SOURCE = Paths.get(Objects.requireNonNull(Day1.class.getResource("/day1/input.txt")).toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_INPUT_FILE_SOURCE))) {
            String line;
            final List<Integer> leftList = new LinkedList<>();
            final List<Integer> rightList = new LinkedList<>();

            while ((line = br.readLine()) != null) {
                final String[] splitLine = line.trim().replaceAll(" +", StringUtils.SPACE).split(StringUtils.SPACE);
                leftList.add(Integer.valueOf(splitLine[0]));
                rightList.add(Integer.valueOf(splitLine[1]));
            }

            Collections.sort(leftList);
            Collections.sort(rightList);

            long totalDistance = 0L;

            for (int i = 0; i < leftList.size(); i++) {
                System.out.printf("%d - %d%n", leftList.get(i), rightList.get(i));
                totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
                System.out.printf("Distance: %d%n", Math.abs(leftList.get(i) - rightList.get(i)));
            }

            System.out.printf("Total distance: %d%n", totalDistance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
