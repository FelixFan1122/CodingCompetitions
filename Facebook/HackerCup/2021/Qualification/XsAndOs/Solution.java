import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/felixklein/Downloads/xs_and_os_input.txt"));
        System.setOut(new PrintStream("/Users/felixklein/Downloads/xs_and_os_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var n = in.nextInt();
            var board = new char[n][];
            for (int j = 0; j < n; j++) board[j] = in.next().toCharArray();
            var result = win(board);
            if (result[0] == Integer.MAX_VALUE) System.out.println("Case #" + i + ": Impossible");
            else System.out.println("Case #" + i + ": " + result[0] + " " + result[1]);
        }
    }
    private static int[] win(char[][] board) {
        int min = Integer.MAX_VALUE, count = 0;
        for (int i = 0; i < board.length; i++) {
            int current = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') current++;
                else if (board[i][j] == 'O') {
                    current = Integer.MAX_VALUE;
                    break;
                }
            }
            if (current == min) count++;
            else if (current < min) {
                min = current;
                count = 1;
            }
        }
        for (int i = 0; i < board.length; i++) {
            int current = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '.') current++;
                else if (board[j][i] == 'O') {
                    current = Integer.MAX_VALUE;
                    break;
                }
            }
            if (current == min) count++;
            else if (current < min) {
                min = current;
                count = 1;
            }
        }
        if (min == 1) count = countSets(board);
        return new int[] { min, count };
    }
    private static int countSets(char[][] board) {
        var set = new HashSet<Integer>();
        for (int i = 0; i < board.length; i++) {
            int current = 0, coord = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    current++;
                    coord = i * board.length + j;
                } else if (board[i][j] == 'O') {
                    current = Integer.MAX_VALUE;
                    break;
                }
            }
            if (current == 1) set.add(coord);
        }
        for (int i = 0; i < board.length; i++) {
            int current = 0, coord = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == '.') {
                    current++;
                    coord = j * board.length + i;
                } else if (board[j][i] == 'O') {
                    current = Integer.MAX_VALUE;
                    break;
                }
            }
            if (current == 1) set.add(coord);
        }
        return set.size();
    }
}