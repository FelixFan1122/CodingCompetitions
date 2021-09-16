import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            var house = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    house[j][k] = in.nextInt();
                }
            }
            int count = getSafe(house);
            System.out.println("Case #" + i + ": " + count);
        }
    }
    private static int getSafe(int[][] house) {
        int max = getMax(house);
        int r = house.length, c = house[0].length, count = 0, min = max - r - c + 2;
        ArrayList<int[]>[] steps = (ArrayList<int[]>[])new ArrayList[r + c - 1];
        for (int i = 0; i < steps.length; i++) steps[i] = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                count = house[i][j] < min ? count + min - house[i][j] : count;
                house[i][j] = house[i][j] < min ? min : house[i][j];
                steps[max - house[i][j]].add(new int[] { i, j });
            }
        }
        boolean[] visited = new boolean[r * c];
        int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        for (int i = 0; i < steps.length; i++) {
            for (var cell : steps[i]) {
                for (var d : directions) {
                    int rr = cell[0] + d[0], cc = cell[1] + d[1];
                    if (rr >= 0 && rr < r && cc >= 0 && cc < c && !visited[rr * r + c]) {
                        count += house[cell[0]][cell[1]] - house[rr][cc] - 1;
                        house[rr][cc] = house[cell[0]][cell[1]] - 1;
                        visited[cell[0] * r + cell[1]] = true;
                        steps[max - house[rr][cc]].add(new int[] { rr, cc });
                    }
                }
            }
        }
        return count;
    }
    private static int getMax(int[][] house) {
        int max = 0;
        for (int i = 0; i < house.length; i++) {
            for (int j = 0; j < house[0].length; j++) {
                max = Math.max(max, house[i][j]);
            }
        }
        return max;
    }
}