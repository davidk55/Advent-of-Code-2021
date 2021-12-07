import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DayPart2 {
    public static void main(String[] args) {
        String fileName = "day7/src/input";
        Map<Integer, Integer> horPositions = new HashMap<>();
        int highestNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            if ((curLine = br.readLine()) != null) {
                for (String s : curLine.split(",")) {
                    int pos = Integer.parseInt(s);
                    if (highestNumber < pos) highestNumber = pos;
                    horPositions.put(pos, horPositions.get(pos) == null ? 1 : horPositions.get(pos) + 1);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int lowestFuels = getFuelCost(horPositions, 0);
        for (int i = 1; i < highestNumber; i++) {
            int curFuelCost = getFuelCost(horPositions, i);
            if (lowestFuels > curFuelCost)
                lowestFuels = curFuelCost;
        }
        System.out.println("Answer: " + lowestFuels);

    }

    public static int getFuelCost(Map<Integer, Integer> horPositions, int number) {
        int sum = 0;
        for (int i : horPositions.keySet()) {
            int distance = Math.abs(number - i);
            for (int j = 1; j <= distance; j++) {
                sum += horPositions.get(i) * j;
            }
        }
        return sum;
    }
}
