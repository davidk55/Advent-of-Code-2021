import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13Part2 {
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
        for (String[] instruction : instructions) {
            if (instruction[2].equals("y"))
                Day13Part1.foldAlongY(grid, Integer.parseInt(instruction[3]));
            else if (instruction[2].equals("x"))
                Day13Part1.foldAlongX(grid, Integer.parseInt(instruction[3]));
        }
        System.out.println("Answer: ");
        printGrid(grid);
    }

    public static void printGrid(boolean[][] grid) {
        // get highest x and y
        int highestX = 0;
        int highestY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    if (i > highestY) highestY = i;
                    if (j > highestX) highestX = j;
                }
            }
        }
        // print the grid
        for (int i = 0; i <= highestY; i++) {
            for (int j = 0; j <= highestX; j++) {
                if (grid[i][j]) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
    }
}
