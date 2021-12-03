import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3Part1 {
    public static void main(String[] args) {
        String fileName = "day3/src/input";
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            int length = 12;
            int[] digitCounter = new int[length];
            int lines = 0;
            while ((curLine = br.readLine()) != null) {
                for (int i = 0; i < curLine.length(); i++) {
                    if (curLine.charAt(i) == '1') digitCounter[i]++;
                }
                lines++;
            }
            for (int i : digitCounter) {
                if (i > lines / 2) {
                    gammaRate.append('1');
                    epsilonRate.append('0');
                } else {
                    gammaRate.append('0');
                    epsilonRate.append('1');
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int gammaRateInt = Integer.parseInt(gammaRate.toString(), 2);
        int epsilonRateInt = Integer.parseInt(epsilonRate.toString(), 2);
        System.out.println(gammaRateInt * epsilonRateInt);
    }
}