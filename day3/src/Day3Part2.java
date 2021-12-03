import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) {
        String fileName = "day3/src/input";
        int length = 12;
        List<String> mostCommon = new ArrayList<>();
        List<String> leastCommon = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                mostCommon.add(curLine);
                leastCommon.add(curLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int curLength = mostCommon.size();
        int curSum = 0;
        char curMostCommon;
        for (int i = 0; i < length; i++) {
            if (curLength == 1) break;
            for (String s : mostCommon) {
                if (s.charAt(i) == '1') curSum++;
            }
            curMostCommon = curSum >= curLength - curSum ? '1' : '0';
            curSum = 0;
            Iterator<String> it = mostCommon.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (s.charAt(i) != curMostCommon) {
                    it.remove();
                    curLength--;
                }
            }
        }
        curLength = leastCommon.size();
        char curLeastCommon;
        for (int i = 0; i < length; i++) {
            if (curLength == 1) break;
            for (String s : leastCommon) {
                if (s.charAt(i) == '1') curSum++;
            }
            curLeastCommon = curLength - curSum <= curSum ? '0' : '1';
            curSum = 0;
            Iterator<String> it = leastCommon.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (s.charAt(i) != curLeastCommon) {
                    it.remove();
                    curLength--;
                }
            }
        }
        int oxygenGeneratorRating = Integer.parseInt(mostCommon.get(0), 2);
        int co2ScrubberRating = Integer.parseInt(leastCommon.get(0), 2);
        System.out.println(oxygenGeneratorRating * co2ScrubberRating);
    }
}