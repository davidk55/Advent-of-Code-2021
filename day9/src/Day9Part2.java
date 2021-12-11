import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9Part2 {
    public static void main(String[] args) {
        String fileName = "day9/src/input";
        List<List<Integer>> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                List<Integer> curList = new ArrayList<>();
                for (int i = 0; i < curLine.length(); i++) {
                    curList.add(Character.getNumericValue(curLine.charAt(i)));
                }
                lines.add(curList);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<String> lowPoints = getLowPoints(lines);
        List<Integer> basinsSizes = new ArrayList<>();
        for (String s : lowPoints) {
            String[] lowPointPos = s.split(" ");
            int lowPointX = Integer.parseInt(lowPointPos[0]);
            int lowPointY = Integer.parseInt(lowPointPos[1]);
            boolean[][] markedGrid = new boolean[lines.size()][lines.get(0).size()];
            basinsSizes.add(getBasinSize(lowPointX, lowPointY, lines, lines.get(0).size(), lines.size(), markedGrid));
        }
        basinsSizes.sort((i, j) -> {
            if (i > j) return -1;
            else if (i.equals(j)) return 0;
            else return 1;
        });

        int sum = 1;
        for (int i = 0; i < 3; i++) {
            sum *= basinsSizes.get(i);
        }
        System.out.println("Answer: " + sum);
    }

    public static int getBasinSize(int lowPointX, int lowPointY, List<List<Integer>> grid, int highestX, int highestY, boolean[][] markedGrid) {
        int count = 0;
        markedGrid[lowPointY][lowPointX] = true;
        if (lowPointY + 1 < highestY && !markedGrid[lowPointY + 1][lowPointX]) {
            if (grid.get(lowPointY + 1).get(lowPointX) != 9)
                count += getBasinSize(lowPointX, lowPointY + 1, grid, highestX, highestY, markedGrid);
        }
        if (lowPointX + 1 < highestX && !markedGrid[lowPointY][lowPointX + 1]) {
            if (grid.get(lowPointY).get(lowPointX + 1) != 9)
                count += getBasinSize(lowPointX + 1, lowPointY, grid, highestX, highestY, markedGrid);
        }
        if (lowPointY - 1 >= 0 && !markedGrid[lowPointY - 1][lowPointX]) {
            if (grid.get(lowPointY - 1).get(lowPointX) != 9)
                count += getBasinSize(lowPointX, lowPointY - 1, grid, highestX, highestY, markedGrid);
        }
        if (lowPointX - 1 >= 0 && !markedGrid[lowPointY][lowPointX - 1]) {
            if (grid.get(lowPointY).get(lowPointX - 1) != 9)
                count += getBasinSize(lowPointX - 1, lowPointY, grid, highestX, highestY, markedGrid);
        }
        return count + 1;
    }

    public static List<String> getLowPoints(List<List<Integer>> list) {
        int sum = 0;
        List<String> lowPoints = new ArrayList<>();
        for (int y = 0; y < list.size(); y++) {
            for (int x = 0; x < list.get(y).size(); x++) {
                boolean isLowPoint = true;
                if (x - 1 >= 0) {
                    if (list.get(y).get(x) >= list.get(y).get(x - 1)) isLowPoint = false;
                }
                if (x + 1 < list.get(y).size()) {
                    if (list.get(y).get(x) >= list.get(y).get(x + 1)) isLowPoint = false;
                }
                if (y - 1 >= 0) {
                    if (list.get(y).get(x) >= list.get(y - 1).get(x)) isLowPoint = false;
                }
                if (y + 1 < list.size()) {
                    if (list.get(y).get(x) >= list.get(y + 1).get(x)) isLowPoint = false;
                }
                if (isLowPoint) {
                    lowPoints.add(x + " " + y);
                }
            }
        }
        return lowPoints;
    }
}
