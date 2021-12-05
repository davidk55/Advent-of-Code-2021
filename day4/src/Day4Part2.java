import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 {
    public static void main(String[] args) {
        String fileName = "day4/src/input";
        String[] drawnNumbers = new String[0];
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            if ((curLine = br.readLine()) != null) drawnNumbers = curLine.split(",");
            int lineNumbers = 0;
            while ((curLine = br.readLine()) != null) {
                if (lineNumbers % 6 != 0) {
                    rows.add(curLine.trim().split("\\s+"));
                }
                lineNumbers++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (String num : drawnNumbers) {
            List<Integer> markedBoards = markDrawnNumber(rows, num);
            if (!(getBoardCount(rows) == 1)) {
                for (int i : markedBoards) {
                    if (checkIfWinnerBeforeDelete(rows, i) ) {
                        markBoardAsCompleted(rows, i);
                    }
                }

            }

            if (getBoardCount(rows) == 1 && checkIfLastIsWinner(rows)) {
                System.out.println(calculateFinalScore(rows, num));
                break;
            }
        }
    }
    private static boolean checkIfLastIsWinner(List<String[]> rows) {
        int firstRowOfLastBoard = getFirstRowOfBoard(rows, 0);
        // check if row is completed
        boolean finishedRow = false;
        for (int i = firstRowOfLastBoard; i < firstRowOfLastBoard + 5; i++) {
            finishedRow = true;
            for (int j = 0; j < rows.get(i).length; j++) {
                if (!rows.get(i)[j].equals("x")) {
                    finishedRow = false;
                    break;
                }
            }
            if (finishedRow) break;
        }
        // check if column is completed
        boolean finishedColumn = false;
        for (int i = 0; i < rows.get(firstRowOfLastBoard).length; i++) {
            finishedColumn = true;
            for (int j = firstRowOfLastBoard; j < firstRowOfLastBoard + 5; j++) {
                if (!rows.get(j)[i].equals("x")) {
                    finishedColumn = false;
                    break;
                }
            }
            if (finishedColumn) break;
        }
        return finishedColumn || finishedRow;
    }
    public static List<Integer> markDrawnNumber(List<String[]> rows, String drawnNumber) {
        List<Integer> markedBoards = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).length; j++) {
                if (rows.get(i)[j].equals(drawnNumber)) {
                    markedBoards.add(i / 5);
                    rows.get(i)[j] = "x";
                }
            }
        }
        return markedBoards;
    }
    public static int getFirstRowOfBoard(List<String[]> rows, int boardNumber) {
        int curBoardNumber = 0;
        for (int i = 0; i < rows.size() - 5; i += 5) {
            if (rows.get(i)[0].equals("-1")) continue;
            else if (curBoardNumber == boardNumber) return i;
            else curBoardNumber++;
            curBoardNumber++;
        }
        return -1;
    }
    public static boolean checkIfWinnerBeforeDelete(List<String[]> rows, int boardNumber) {
        // check if row is completed
        boolean finishedRow = false;
        for (int i = boardNumber * 5; i < boardNumber * 5 + 5; i++) {
            finishedRow = true;
            for (int j = 0; j < rows.get(i).length; j++) {
//                System.out.println("bn: " + boardNumber + " row: " + i + " col: " + j);
                if (!rows.get(i)[j].equals("x")) {
                    finishedRow = false;
                    break;
                }
            }
            if (finishedRow) break;
        }
        // check if column is completed
        boolean finishedColumn = false;
        for (int i = 0; i < rows.get(boardNumber * 5).length; i++) {
            finishedColumn = true;
            for (int j = boardNumber * 5; j < boardNumber * 5 + 5; j++) {
                if (!rows.get(j)[i].equals("x")) {
                    finishedColumn = false;
                    break;
                }
            }
            if (finishedColumn) break;
        }
        return finishedColumn || finishedRow;
    }
    public static void markBoardAsCompleted(List<String[]> rows, int boardNumber) {
        rows.get(boardNumber * 5)[0] = "-1";
    }
    public static int getBoardCount(List<String[]> rows) {
        int boardCount = 0;
        for (int i = 0; i < rows.size() - 5; i += 5) {
            if (!rows.get(i)[0].equals("-1")) boardCount++;
        }
        return boardCount;
    }
    private static int sumUpUnmarkedNumbers(List<String[]> rows) {
        int sum = 0;
        int firstRowOfLastBoard = getFirstRowOfBoard(rows, 0);
        for (int i = firstRowOfLastBoard; i < firstRowOfLastBoard + 5; i++) {
            for (int j = 0; j < rows.get(i).length; j++) {
                if (!rows.get(i)[j].equals("x")) sum += Integer.parseInt(rows.get(i)[j]);
            }
        }
        return sum;
    }
    public static int calculateFinalScore(List<String[]> rows, String lastDrawn) {
        int lastDrawnInt = Integer.parseInt(lastDrawn);
        return sumUpUnmarkedNumbers(rows) * lastDrawnInt;
    }
}