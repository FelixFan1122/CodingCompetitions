import java.util.*;
import java.io.*;
public class Solution {
    private static final HashSet<Character> vowels = new HashSet<>();
    public static void main(String[] args) throws FileNotFoundException {
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        System.setIn(new FileInputStream("C:\\Users\\zeus_\\Downloads\\consistency_chapter_1_input.txt"));
        System.setOut(new PrintStream("C:\\Users\\zeus_\\Downloads\\consistency_chapter_1_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var s = in.next();
            System.out.println("Case #" + i + ": " + getMin(s));
        }
    }
    private static int getMin(String s) {
        int min = Integer.MAX_VALUE;
        for (char t = 'A'; t <= 'Z'; t++) min = Math.min(min, change(s, t));
        return min;
    }
    private static int change(String s, char t) {
        boolean isVowel = vowels.contains(t);
        int seconds = 0;
        for (char c : s.toCharArray()) {
            if (c == t) continue;
            seconds += (isVowel && vowels.contains(c)) || (!isVowel && !vowels.contains(c)) ? 2 : 1;
        }
        return seconds;
    }
}