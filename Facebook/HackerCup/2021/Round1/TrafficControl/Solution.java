import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/felixklein/Downloads/traffic_control_input.txt"));
        System.setOut(new PrintStream("/Users/felixklein/Downloads/traffic_control_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            if (n + m - 1 > Math.min(a, b)) System.out.println("Case #" + i + ": Impossible");
            else {
                System.out.println("Case #" + i + ": Possible");
                var grid = generate(n, m, a, b);
                for (var r : grid) {
                    var sb = new StringBuilder();
                    for (var e : r) sb.append(e).append(' ');
                    sb.deleteCharAt(sb.length() - 1);
                    System.out.println(sb);
                }
            }
        }
    }
    private static int[][] generate(int n, int m, int a, int b) {
        var grid = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) grid[i][j] = 1;
        grid[0][0] = a - n - m + 2;
        grid[0][m - 1] = b - n - m + 2;
        return grid;
    }
}