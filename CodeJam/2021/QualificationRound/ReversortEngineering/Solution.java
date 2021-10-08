import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println("Case #" + i + ": " + generate(n, m));
        }
    }
    private static String generate(int n, int c) {
        return c < n - 1 || c > n * (n + 1) / 2 - 1 ? "IMPOSSIBLE" : join(' ', recurse(n, c));
    }
    private static String join(char delimiter, int[] seq) {
        var sb = new StringBuilder();
        for (int num : seq) sb.append(num).append(delimiter);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    private static int[] recurse(int n, int c) {
        if (n == 1) return new int[] { 1 };
        int cost = Math.min(n, c - n + 2);
        var seq = recurse(n - 1, c - cost);
        var copy = new int[n];
        copy[0] = 1;
        for (int i = 1; i < n; i++) copy[i] = seq[i - 1] + 1;
        for (int i = 0, j = cost - 1; i < j; i++, j--) {
            int temp = copy[i];
            copy[i] = copy[j];
            copy[j] = temp;
        }
        return copy;
    }
}