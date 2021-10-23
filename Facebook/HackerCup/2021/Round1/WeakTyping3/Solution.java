import java.util.*;
import java.io.*;
public class Solution {
    private static final int MOD = 1000_000_007;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/felixklein/Downloads/weak_typing_chapter_3_input.txt"));
        System.setOut(new PrintStream("/Users/felixklein/Downloads/weak_typing_chapter_3_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var k = in.nextInt();
            var u = in.next();
            System.out.println("Case #" + i + ": " + type(u));
        }
    }
    private static int type(String u) {
        char firstChar = ' ', lastChar = ' ';
        long firstIndex = -1, g = 0, lastIndex = -1, leftSum = 0, length = 0, rightSum = 0, switches = 0;
        for (int i = 0; i < u.length(); i++) {
            char c = u.charAt(i);
            char firstCharRight = firstChar, lastCharRight = lastChar;
            long firstIndexRight = firstIndex, gRight = g, lastIndexRight = lastIndex, leftSumRight = leftSum, lengthRight = length, rightSumRight = rightSum, switchesRight = switches;
            switch (c) {
                case 'F':
                    firstCharRight = ' ';
                    firstIndexRight = -1;
                    gRight = 0;
                    lastCharRight = ' ';
                    lastIndexRight = -1;
                    leftSumRight = 0;
                    lengthRight = 1;
                    rightSumRight = 0;
                    switchesRight = 0;
                    break;
                case 'O':
                    firstCharRight = 'O';
                    firstIndexRight = 0;
                    gRight = 0;
                    lastCharRight = 'O';
                    lastIndexRight = 0;
                    leftSumRight = 0;
                    lengthRight = 1;
                    rightSumRight = 0;
                    switchesRight = 0;
                    break;
                case 'X':
                    firstCharRight = 'X';
                    firstIndexRight = 0;
                    gRight = 0;
                    lastCharRight = 'X';
                    lastIndexRight = 0;
                    leftSumRight = 0;
                    lengthRight = 1;
                    rightSumRight = 0;
                    switchesRight = 0;
                    break;
                case '.':
                    break;
                default:
                    break;
            }
            g = (g + gRight + leftSum * lengthRight + rightSumRight * length) % MOD;
            leftSum = (leftSum + leftSumRight + switchesRight * length) % MOD;
            rightSum = (rightSum + rightSumRight + switches * lengthRight) % MOD;
            switches = (switches + switchesRight) % MOD;
            if (lastIndex != -1 && firstIndexRight != -1 && lastChar != firstCharRight) {
                g = (g + (lastIndex + 1) * ((lengthRight - firstIndexRight + MOD) % MOD)) % MOD;
                leftSum = (leftSum + lastIndex + 1) % MOD;
                rightSum = (rightSum + lengthRight - firstIndexRight + MOD) % MOD;
                switches = (switches + 1) % MOD;
            }
            firstChar = firstIndex == -1 ? firstCharRight : firstChar;
            lastChar = lastIndexRight == -1 ? lastChar : lastCharRight;
            firstIndex = firstIndex == -1 ? (firstIndexRight == -1 ? -1 : firstIndexRight + length) % MOD : firstIndex;
            lastIndex = lastIndexRight == -1 ? lastIndex : (lastIndexRight + length) % MOD;
            length = (length + lengthRight) % MOD;
        }
        return (int)g;
    }
}