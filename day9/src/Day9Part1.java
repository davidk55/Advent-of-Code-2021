import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9Part1 {
    public static void main(String[] args) {
        List<List<Integer>> lines = new ArrayList<>();
        String fileName = "day9/src/input";
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
        System.out.println("Answer: " + getLowPointsSum(lines));
    }

    public static int getLowPointsSum(List<List<Integer>> list) {
        int sum = 0;
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
                    sum += (list.get(y).get(x) + 1);
                }
            }
        }
        return sum;
    }
}
