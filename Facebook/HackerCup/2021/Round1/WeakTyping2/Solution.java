import java.util.*;
import java.io.*;
public class Solution {
    private static final int MOD = 1000_000_007;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\zeus_\\Downloads\\weak_typing_chapter_2_input.txt"));
        System.setOut(new PrintStream("C:\\Users\\zeus_\\Downloads\\weak_typing_chapter_2_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var n = in.nextInt();
            var w = in.next();
            System.out.println("Case #" + i + ": " + type(w));
        }
    }
    private static int type(String w) {
        char prev = w.charAt(0);
        int prevIndex = 0;
        var switches = new long[w.length()];
        for (int i = 1; i < w.length(); i++) {
            char c = w.charAt(i);
            switches[i] = switches[i - 1];
            if (c == 'O' && (w.charAt(i - 1) == 'X' || (w.charAt(i - 1) == 'F' && prev == 'X'))) switches[i] = (switches[i - 1] + prevIndex + 1) % MOD;
            if (c == 'X' && (w.charAt(i - 1) == 'O' || (w.charAt(i - 1) == 'F' && prev == 'O'))) switches[i] = (switches[i - 1] + prevIndex + 1) % MOD;
            if (c != 'F') {
                prev = c;
                prevIndex = i;
            }
        }
        long sum = 0;
        for (long s : switches) sum = (sum + s) % MOD;
        return (int)sum;
    }
}