import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2Part1 {
    public static void main(String[] args) {
        String fileName = "day2/src/input";
        int horizontalPos = 0;
        int depth = 0;
        String[] commandWithUnits;
        String command;
        int units;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                commandWithUnits = curLine.split(" ");
                command = commandWithUnits[0];
                units = Integer.parseInt(commandWithUnits[1]);
                if (command.equals("forward")) horizontalPos += units;
                else if (command.equals("up")) depth -= units;
                else if (command.equals("down")) depth += units;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Answer: " + horizontalPos * depth);
    }
}
