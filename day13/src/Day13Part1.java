import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13Part1 {
    public static void main(String[] args) {
        String fileName = "day13/src/input";
        int MAX_ROWS = 1500;
        int MAX_COLUMNS = 1500;
        boolean[][] grid = new boolean[MAX_ROWS][MAX_COLUMNS];
        List<String[]> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            boolean endOfDots = false;
            while ((curLine = br.readLine()) != null) {
                if (curLine.equals("")) {
                    endOfDots = true;
                    continue;
                }
                if (!endOfDots) {
                    String[] dotPosition = curLine.split(",");
                    int dotPosX = Integer.parseInt(dotPosition[0]);
                    int dotPosY = Integer.parseInt(dotPosition[1]);
                    grid[dotPosY][dotPosX] = true;
                } else {
                    String[] singleInstruction = curLine.split("[ =]");
                    instructions.add(singleInstruction);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int constructionCountToFollow = 1;
        for (int i = 0; i < constructionCountToFollow; i++) {
            if (instructions.get(i)[2].equals("y")) foldAlongY(grid, Integer.parseInt(instructions.get(i)[3]));
            else if (instructions.get(i)[2].equals("x")) foldAlongX(grid, Integer.parseInt(instructions.get(i)[3]));
        }
        int dotCount = getDotCount(grid);
        System.out.println("Answer: " + dotCount);
    }


    public static void foldAlongY(boolean[][] grid, int rowPos) {
        for (int i = rowPos; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) {
                    grid[i][j] = false;
                    int gapFoldRowToCurRow = i - rowPos;
                    if (!grid[rowPos - gapFoldRowToCurRow][j]) grid[rowPos - gapFoldRowToCurRow][j] = true;
                }
            }
        }
    }

    public static void foldAlongX(boolean[][] grid, int columnPos) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = columnPos; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    grid[i][j] = false;
                    int gapFoldColumnToCurColumn = j - columnPos;
                    if (!grid[i][columnPos - gapFoldColumnToCurColumn])
                        grid[i][columnPos - gapFoldColumnToCurColumn] = true;
                }
            }
        }
    }

    public static int getDotCount(boolean[][] grid) {
        int dotCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) dotCount++;
            }
        }
        return dotCount;
    }



}
