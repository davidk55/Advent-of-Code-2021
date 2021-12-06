import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6Part2 {
    public static void main(String[] args) {
        String fileName = "day6/src/input";
        PopulationLanternfishV2 fishPopulation = null;
        int days = 256;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            if ((curLine = br.readLine()) != null) {
                fishPopulation = new PopulationLanternfishV2(curLine);
                System.out.println("Initial state: " + curLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (fishPopulation != null) System.out.println("Answer: " + fishPopulation.getPopululationCountAfterDays(days));
    }
}
