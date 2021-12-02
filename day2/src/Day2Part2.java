import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2Part2 {
    public static void main(String[] args) {
        String fileName = "day2/src/input";
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            String[] commandWithUnit;
            String command;
            int unit;
            while ((curLine = br.readLine()) != null) {
                commandWithUnit = curLine.split(" ");
                command = commandWithUnit[0];
                unit = Integer.parseInt(commandWithUnit[1]);
                if (command.equals("forward")) {
                    horizontalPos += unit;
                    depth += aim * unit;
                } else if (command.equals("up")) {
                    aim -= unit;
                } else if (command.equals("down")) {
                    aim += unit;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(horizontalPos * depth);
    }
}
