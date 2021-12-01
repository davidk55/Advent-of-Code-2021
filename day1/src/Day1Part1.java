import java.io.*;

public class Day1Part1 {
    public static void main(String[] args) {
        String fileName = "day1/src/input";
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            int lastNumber = 0;
            int currentNumber;
            if ((curLine = br.readLine()) != null) lastNumber = Integer.parseInt(curLine);
            while ((curLine = br.readLine()) != null) {
                currentNumber = Integer.parseInt(curLine);
                if (currentNumber > lastNumber) counter++;
                lastNumber = currentNumber;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Answer: " + counter);
    }
}
