import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {
    public static void main(String[] args) {
        String fileName = "day4/src/input";
        String[] drawnNumbers = new String[0];
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            if ((curLine = br.readLine()) != null) drawnNumbers = curLine.split(",");
            int lineNumbers = 0;
            while ((curLine = br.readLine()) != null) {
                if (lineNumbers % 6 != 0) rows.add(curLine.trim().split("\\s+"));
                lineNumbers++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int sum = 0;
        boolean finishedRow;
        boolean finishedColumn;
        int lastDrawn = 0;
        outerLoop:
        for (String num : drawnNumbers) {
            lastDrawn = Integer.parseInt(num);
            // loop through all rows
            for (int rowNum = 0; rowNum < rows.size(); rowNum++) {
                // loop through the columns of the current row
                for (int columnNum = 0; columnNum < rows.get(rowNum).length; columnNum++) {
                    if (rows.get(rowNum)[columnNum].equals(num)) {
                        rows.get(rowNum)[columnNum] = "x";
                        // check if a column is completed
                        finishedRow = true;
                        for (String s : rows.get(rowNum)) {
                            if (!s.equals("x")) {
                                finishedRow = false;
                                break;
                            }
                        }
                        // check if a row is completed
                        finishedColumn = true;
                        for (int k = (rowNum / 5) * 5; k < rowNum + 5; k++) {
                            if (!rows.get(k)[columnNum].equals("x")) {
                                finishedColumn = false;
                                break;
                            }
                        }
                        // sum up the unmarked numbers in the that board
                        if (finishedRow || finishedColumn) {
                            for (int l = (rowNum / 5) * 5; l < rowNum + 5; l++) {
                                for (int m = 0; m < rows.get(l).length; m++) {
                                    if (!rows.get(l)[m].equals("x")) sum += Integer.parseInt(rows.get(l)[m]);
                                }
                            }
                            // break out of all loops
                            break outerLoop;
                        }
                    }
                }
            }
        }
        System.out.println(sum * lastDrawn);
    }
}