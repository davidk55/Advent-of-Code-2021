import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5Part1 {
    public static void main(String[] args) {
        String fileName = "day5/src/input";
        Diagram diagram = new Diagram(1000);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                int[] cords = diagram.lineSegmentToLineCoordinates(curLine);
                diagram.addLine(cords[0], cords[1], cords[2], cords[3]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Answer: " + diagram.countPositionsTwoOrMore());
    }
}
