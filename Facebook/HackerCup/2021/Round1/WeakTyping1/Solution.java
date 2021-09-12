import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\zeus_\\Downloads\\weak_typing_chapter_1_input.txt"));
        System.setOut(new PrintStream("C:\\Users\\zeus_\\Downloads\\weak_typing_chapter_1_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var n = in.nextInt();
            var w = in.next();
            System.out.println("Case #" + i + ": " + type(w));
        }
    }
    private static int type(String w) {
        char prev = 'f';
        int switches = 0;
        for (char c : w.toCharArray()) {
            if (c == 'F' || c == prev) continue;
            if (prev != 'f') switches++;
            prev = c;
        }
        return switches;
    }
}