import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Part2 {
    public static void main(String[] args) {
        String fileName = "day1/src/input";
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            int curNum;
            while ((curLine = br.readLine()) != null) {
                numbers.add(Integer.parseInt(curLine));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int curPos = 0;
        int firstWindow;
        int secondWindow;
        int counter = 0;
        while (curPos + 3 < numbers.size()) {
            firstWindow = numbers.get(curPos) + numbers.get(curPos + 1) + numbers.get(curPos + 2);
            secondWindow = numbers.get(curPos + 1) + numbers.get(curPos + 2) + numbers.get(curPos + 3);
            if (firstWindow < secondWindow) counter++;
            curPos++;
        }
        System.out.println("Answer: " + counter);
    }
}
