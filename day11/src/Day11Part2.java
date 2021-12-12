import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day11Part2 {
    private static final int MAX_ROWS = 10;
    private static final int MAX_COLUMNS = 10;

    public static void main(String[] args) {
        String fileName = "day11/src/input";
        int[][] grid = new int[MAX_ROWS][MAX_COLUMNS];
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                lines.add(curLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Day11Part1.initializeGrid(lines, grid);
        int stepsNeededForAllLighted = 0;
        int maxPossibleSteps = 1000;
        for (int i = 1; i < maxPossibleSteps; i++) {
            boolean[][] lightedGrid = new boolean[MAX_ROWS][MAX_COLUMNS];
            Day11Part1.calculateStep(grid, lightedGrid);
            if (checkIfAllAreLighted(lightedGrid)) {
                stepsNeededForAllLighted = i;
                break;
            }
        }
        System.out.println("Answer: " + stepsNeededForAllLighted);
    }

    public static boolean checkIfAllAreLighted(boolean[][] lightedGrid) {
        boolean areAllLighted = true;
        for (boolean[] rows : lightedGrid) {
            for (boolean columns : rows) {
                if (!columns) {
                    areAllLighted = false;
                    break;
                }
            }
        }
        return areAllLighted;
    }
}
