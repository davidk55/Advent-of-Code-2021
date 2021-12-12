import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Day10Part2 {
    public static void main(String[] args) {
        String fileName = "day10/src/input";
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                lines.add(curLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Long> sums = new ArrayList<>();
        for (String line : lines) {
            if (!checkIfLineIsCorrupted(line)) {
                sums.add(getAutocompletionScoreOfLine(line));
            }
        }
        sums.sort((o1, o2) -> {
            if (o1 > o2) return 1;
            else if (o1.equals(o2)) return 0;
            else return -1;
        });
        // since list index start with 0 you do not have to increase the half of the size by one
        System.out.println("Answer: " + sums.get(sums.size() / 2));
    }

    public static long getAutocompletionScoreOfLine(String line) {
        long score = 0;
        Deque<Character> brackets = new LinkedList<>();
        brackets.push(line.charAt(0));
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{' || line.charAt(i) == '<') {
                brackets.push(line.charAt(i));
            }
            if (brackets.peek() != null) {
                if (brackets.peek() == '(' && line.charAt(i) == ')') brackets.pop();
                else if (brackets.peek() == '[' && line.charAt(i) == ']') brackets.pop();
                else if (brackets.peek() == '{' && line.charAt(i) == '}') brackets.pop();
                else if (brackets.peek() == '<' && line.charAt(i) == '>') brackets.pop();
            }
        }
        for (char c : brackets) {
            if (c == '(') score = score * 5 + 1;
            if (c == '[') score = score * 5 + 2;
            if (c == '{') score = score * 5 + 3;
            if (c == '<') score = score * 5 + 4;
        }
        return score;
    }

    public static boolean checkIfLineIsCorrupted(String line) {
        boolean isCorrupted = false;
        Deque<Character> brackets = new LinkedList<>();
        brackets.push(line.charAt(0));
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{' || line.charAt(i) == '<') {
                brackets.push(line.charAt(i));
            }
            boolean isClosingBracket = line.charAt(i) == ')' || line.charAt(i) == ']' || line.charAt(i) == '}' || line.charAt(i) == '>';
            if (brackets.peek() != null) {
                if (brackets.peek() == '(' && line.charAt(i) == ')') brackets.pop();
                else if (brackets.peek() == '[' && line.charAt(i) == ']') brackets.pop();
                else if (brackets.peek() == '{' && line.charAt(i) == '}') brackets.pop();
                else if (brackets.peek() == '<' && line.charAt(i) == '>') brackets.pop();
                else if (isClosingBracket) {
                    isCorrupted = true;
                    break;
                }
            } else if (isClosingBracket) {
                isCorrupted = true;
                break;
            }
        }
        return isCorrupted;
    }
}
