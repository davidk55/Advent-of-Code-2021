import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8Part1 {
    public static void main(String[] args) {
        String fileName = "day8/src/input";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                int outputBeginning = curLine.indexOf("|") + 2; // + 1 for index and + 1 for the beginning whitespace
                System.out.println(outputBeginning);
                sb.append(curLine.substring(outputBeginning)).append(" ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Answer: " + getUniqueNumberCount(sb.toString()));
    }

    public static int getUniqueNumberCount(String numbers) {
        int count = 0;
        for (String s : numbers.split(" ")) {
            int len = s.length();
            // 1: 2 segments, 4: 4 segments, 7: 3 segments, 8: 7 segments
            if (len == 2 || len == 4 || len == 3 || len == 7) count++;
        }
        return count;
    }
}
