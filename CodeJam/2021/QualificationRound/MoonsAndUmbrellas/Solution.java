import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            var s = in.next();
            System.out.println("Case #" + i + ": " + getCopyright(n, m, s));
        }
    }
    private static int getCopyright(int x, int y, String mural) {
        int MAX = 100000;
        var dp = new int[mural.length()][2];
        dp[0][0] = mural.charAt(0) == 'J' ? MAX : 0;
        dp[0][1] = mural.charAt(0) == 'C' ? MAX : 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = mural.charAt(i) == 'J' ? MAX : Math.min(dp[i - 1][0], dp[i - 1][1] + y);
            dp[i][1] = mural.charAt(i) == 'C' ? MAX : Math.min(dp[i - 1][1], dp[i - 1][0] + x);
        }
        return Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]);
    }
}