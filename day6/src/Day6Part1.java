import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6Part1 {
    public static void main(String[] args) {
        String fileName = "day6/src/input";
        PopulationLanternfish fishPopulation = null;
        int days = 80;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            if ((curLine = br.readLine()) != null) {
                fishPopulation = new PopulationLanternfish(curLine);
                System.out.println("Initial state: " + curLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (fishPopulation != null) System.out.println("Answer: " + fishPopulation.getPopululationCountAfterDays(days));
    }
}
