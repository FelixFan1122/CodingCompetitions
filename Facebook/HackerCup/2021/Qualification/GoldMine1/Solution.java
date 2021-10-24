import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/felixklein/Downloads/gold_mine_chapter_1_input.txt"));
        System.setOut(new PrintStream("/Users/felixklein/Downloads/gold_mine_chapter_1_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var n = in.nextInt();
            var c = new int[n + 1];
            for (int j = 1; j <= n; j++) c[j] = in.nextInt();
            var graph = (ArrayList<Integer>[])new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) graph[j] = new ArrayList<>();
            for (int j = 0; j < n - 1; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                graph[a].add(b);
                graph[b].add(a);
            }
            System.out.println("Case #" + i + ": " + getMax(c, graph));
        }
    }
    private static int getMax(int[] c, ArrayList<Integer>[] graph) {
        var children = new int[graph[1].size()];
        for (int i = 0; i < children.length; i++) children[i] = postOrder(c, graph, graph[1].get(i), 1);
        int biggest = 0, second = 0;
        for (int child : children) {
            if (child > biggest) {
                second = biggest;
                biggest = child;
            } else if (child > second) {
                second = child;
            }
        }
        return c[1] + biggest + second;
    }
    private static int postOrder(int[] c, ArrayList<Integer>[] graph, int v, int p) {
        int max = 0;
        for (int child : graph[v]) {
            if (child == p) continue;
            max = Math.max(max, postOrder(c, graph, child, v));
        }
        return max + c[v];
    }
}