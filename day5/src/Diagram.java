public class Diagram {
    private final int[][] diagram;

    public Diagram(int MAX_LINES) {
        diagram = new int[MAX_LINES][MAX_LINES];
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        int cache;
        if (x1 > x2) {
            cache = x1;
            x1 = x2;
            x2 = cache;
        }
        if (y1 > y2) {
            cache = y1;
            y1 = y2;
            y2 = cache;
        }
        if (x1 == x2 || y1 == y2) {
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    diagram[i][j]++;
                }
            }
        }
    }

    public void addDiagonalLine(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
            for (int i = 0; i <= Math.abs(x2 - x1); i++) {
                if (x1 < x2 && y1 < y2) diagram[x1 + i][y1 + i]++;
                else if (x1 > x2 && y1 < y2) diagram[x1 - i][y1 + i]++;
                else if (x1 < x2 && y1 > y2) diagram[x1 + i][y1 - i]++;
                else if (x1 > x2 && y1 > y2) diagram[x1 - i][y1 - i]++;
            }
        }
    }

    public int countPositionsTwoOrMore() {
        int counterTwoOrMore = 0;
        for (int[] i : diagram) {
            for (int j : i) {
                if (j >= 2) counterTwoOrMore++;
            }
        }
        return counterTwoOrMore;
    }

    public int[] lineSegmentToLineCoordinates(String line) {
        String[] s = line.trim().split(",|\\s->\\s");
        int[] lineCoordinates = new int[4];
        for (int i = 0; i < 4; i++) {
            lineCoordinates[i] = Integer.parseInt(s[i]);
        }
        return lineCoordinates;
    }
}
