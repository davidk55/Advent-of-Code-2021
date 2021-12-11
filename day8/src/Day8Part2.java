import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day8Part2 {
    public static void main(String[] args) {
        String fileName = "day8/src/input";
        List<String> linesAfterPipe = new ArrayList<>();
        List<String> linesBeforePipe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String curLine;
            while ((curLine = br.readLine()) != null) {
                linesBeforePipe.add(curLine.substring(0, curLine.indexOf('|') - 1));
                linesAfterPipe.add(curLine.substring(curLine.indexOf('|') + 2)); // + 1 for index and + 1 for the beginning whitespace
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Answer: " + getOutputValuesSum(linesBeforePipe, linesAfterPipe));
    }

    public static int getOutputValuesSum(List<String> linesBeforePipe, List<String> linesAfterPipe) {
        List<Map<Character, Character>> rightMappingsPerLine = getMappingsForEachLine(linesBeforePipe);
        int sum = 0;
        for (int i = 0; i < linesAfterPipe.size(); i++) {
            StringBuilder curNumber = new StringBuilder();
            String[] words = linesAfterPipe.get(i).split(" ");
            for (int j = 0; j < words.length; j++) {
                words[j] = sortCharsOfWordAlphabetically(words[j]);
            }
            for (String w : words) {
                String wordFixed = changeCharsToGivenMapping(w, rightMappingsPerLine.get(i));
                String wordFixedSorted = sortCharsOfWordAlphabetically(wordFixed);
                switch (wordFixedSorted) {
                    case "abcefg" -> curNumber.append("0");
                    case "cf" -> curNumber.append("1");
                    case "acdeg" -> curNumber.append("2");
                    case "acdfg" -> curNumber.append("3");
                    case "bcdf" -> curNumber.append("4");
                    case "abdfg" -> curNumber.append("5");
                    case "abdefg" -> curNumber.append("6");
                    case "acf" -> curNumber.append("7");
                    case "abcdefg" -> curNumber.append("8");
                    case "abcdfg" -> curNumber.append("9");
                }
            }
            sum += Integer.parseInt(curNumber.toString());
        }
        return sum;
    }

    public static String changeCharsToGivenMapping(String word, Map<Character, Character> mapping) {
        StringBuilder wordWithAppliedMapping = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            for (char c : mapping.keySet()) {
                if (word.charAt(i) == mapping.get(c)) wordWithAppliedMapping.append(c);
            }
        }
        return wordWithAppliedMapping.toString();
    }

    public static List<Map<Character, Character>> getMappingsForEachLine(List<String> lines) {
        List<Map<Character, Character>> mappingsForEachLine = new ArrayList<>();
        for (String l : lines) {
            String[] words = l.split(" ");
            List<Character> len2 = new ArrayList<>();
            List<Character> len4 = new ArrayList<>();
            List<Character> len3 = new ArrayList<>();
            List<List<Character>> len6 = new ArrayList<>();
            List<List<Character>> len5 = new ArrayList<>();
            for (String w : words) {
                if (w.length() == 2) {
                    for (int i = 0; i < w.length(); i++) {
                        len2.add(w.charAt(i));
                    }
                } else if (w.length() == 4) {
                    for (int i = 0; i < w.length(); i++) {
                        len4.add(w.charAt(i));
                    }
                } else if (w.length() == 3) {
                    for (int i = 0; i < w.length(); i++) {
                        len3.add(w.charAt(i));
                    }
                } else if (w.length() == 6) {
                    List<Character> curCharacters = new ArrayList<>();
                    for (int i = 0; i < w.length(); i++) {
                        curCharacters.add(w.charAt(i));
                    }
                    len6.add(curCharacters);
                } else if (w.length() == 5) {
                    List<Character> curCharacters = new ArrayList<>();
                    for (int i = 0; i < w.length(); i++) {
                        curCharacters.add(w.charAt(i));
                    }
                    len5.add(curCharacters);
                }
            }
            // get 'a'
            Map<Character, Character> charactersForCurLine = new HashMap<>();
            for (char c : len3) {
                if (!len2.contains(c)) charactersForCurLine.put('a', c);
            }
            // get 'c'
            for (char c : len2) {
                for (List<Character> lc : len6) {
                    if (!lc.contains(c)) charactersForCurLine.put('c', c);
                }
            }
            // get 'f'
            for (char c : len2) {
                if (!(charactersForCurLine.get('c') == c)) charactersForCurLine.put('f', c);
            }
            // get possible 'b' and 'd'
            List<Character> possibleBorD = new ArrayList<>();
            for (char c : len4) {
                if (!(charactersForCurLine.get('c') == c || charactersForCurLine.get('f') == c)) {
                    possibleBorD.add(c);
                }
            }
            // confirm 'b' and 'd' and possible 'e' and 'g'
            List<Character> possibleEorG = new ArrayList<>();
            for (List<Character> lc : len5) {
                if (!lc.contains(charactersForCurLine.get('f'))) {
                    if (lc.contains(possibleBorD.get(0))) {
                        charactersForCurLine.put('d', possibleBorD.get(0));
                        charactersForCurLine.put('b', possibleBorD.get(1));
                    } else {
                        charactersForCurLine.put('b', possibleBorD.get(0));
                        charactersForCurLine.put('d', possibleBorD.get(1));
                    }
                    for (char c : lc) {
                        if (!charactersForCurLine.containsValue(c)) {
                            possibleEorG.add(c);
                        }
                    }
                }
            }
            // confirm 'e' and 'g'
            for (List<Character> lc : len5) {
                if (lc.contains(charactersForCurLine.get('f'))) {
                    if (lc.contains(possibleEorG.get(0))) {
                        charactersForCurLine.put('g', possibleEorG.get(0));
                        charactersForCurLine.put('e', possibleEorG.get(1));
                    } else {
                        charactersForCurLine.put('e', possibleEorG.get(0));
                        charactersForCurLine.put('g', possibleEorG.get(1));
                    }
                    break;
                }
            }
            mappingsForEachLine.add(charactersForCurLine);
        }
        return mappingsForEachLine;
    }

    public static String sortCharsOfWordAlphabetically(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        return String.valueOf(wordChars);
    }
}
