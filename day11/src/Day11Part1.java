import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day11Part1 {
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
        initializeGrid(lines, grid);
        int sumFlashCounts = 0;
        for (int i = 0; i < 100; i++) {
            sumFlashCounts += calculateStep(grid, new boolean[MAX_ROWS][MAX_COLUMNS]);
        }
        System.out.println("Answer: " + sumFlashCounts);
    }

    public static int calculateStep(int[][] grid, boolean[][] lightedGrid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int curValue = grid[row][col];
                if (curValue == 9 && !lightedGrid[row][col]) resetCurAndIncreaseAdjacent(grid, lightedGrid, row, col);
                else if (!lightedGrid[row][col]) grid[row][col]++;
            }
        }
        int flashCount = 0;
        for (int row = 0; row < lightedGrid.length; row++) {
            for (int col = 0; col < lightedGrid[row].length; col++) {
                if (lightedGrid[row][col]) flashCount++;
            }
        }
        return flashCount;
    }

    private static void resetCurAndIncreaseAdjacent(int[][] grid, boolean[][] lightedGrid, int row, int col) {
        lightedGrid[row][col] = true;
        grid[row][col] = 0;
        // upper left corner
        if (row - 1 >= 0 && col - 1 >= 0 && !lightedGrid[row - 1][col - 1]) {
            if (grid[row - 1][col - 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row - 1, col - 1);
            } else grid[row - 1][col - 1]++;
        }
        // upper
        if (row - 1 >= 0 && !lightedGrid[row - 1][col]) {
            if (grid[row - 1][col] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row - 1, col);
            } else {
                grid[row - 1][col]++;
            }
        }
        // left
        if (col - 1 >= 0 && !lightedGrid[row][col - 1]) {
            if (grid[row][col - 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row, col - 1);
            } else grid[row][col - 1]++;
        }
        // lower
        if (row + 1 < grid.length && !lightedGrid[row + 1][col]) {
            if (grid[row + 1][col] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row + 1, col);
            } else grid[row + 1][col]++;
        }
        // right
        if (col + 1 < grid[row].length && !lightedGrid[row][col + 1]) {
            if (grid[row][col + 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row, col + 1);
            } else grid[row][col + 1]++;
        }
        // lower right corner
        if (row + 1 < grid.length && col + 1 < grid[row + 1].length && !lightedGrid[row + 1][col + 1]) {
            if (grid[row + 1][col + 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row + 1, col + 1);
            } else grid[row + 1][col + 1]++;
        }
        // lower left corner
        if (row + 1 < grid.length && col - 1 >= 0 && !lightedGrid[row + 1][col - 1]) {
            if (grid[row + 1][col - 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row + 1, col - 1);
            } else grid[row + 1][col - 1]++;
        }
        // upper right corner
        if (row - 1 >= 0 && col + 1 < grid[row - 1].length && !lightedGrid[row - 1][col + 1]) {
            if (grid[row - 1][col + 1] == 9) {
                resetCurAndIncreaseAdjacent(grid, lightedGrid, row - 1, col + 1);
            } else grid[row - 1][col + 1]++;
        }
    }

    public static void initializeGrid(List<String> lines, int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = Character.getNumericValue(lines.get(row).charAt(col));
            }
        }
    }
}
