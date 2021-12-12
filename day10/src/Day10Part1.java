import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day10Part1 {
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
        int finalScore = 0;
        for (String s : lines) {
            finalScore += getSyntaxErrorScore(s);
        }
        System.out.println("Answer: " + finalScore);
    }

    public static int getSyntaxErrorScore(String line) {
        int score = 0;
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
                    // illegal characters
                else if (line.charAt(i) == ')') {
                    score += 3;
                    break;
                } else if (line.charAt(i) == ']') {
                    score += 57;
                    break;
                } else if (line.charAt(i) == '}') {
                    score += 1197;
                    break;
                } else if (line.charAt(i) == '>') {
                    score += 25137;
                    break;
                }
            }
        }
        return score;
    }
}
