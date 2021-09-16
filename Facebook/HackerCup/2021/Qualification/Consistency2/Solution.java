import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/felixklein/Downloads/consistency_chapter_2_input.txt"));
        System.setOut(new PrintStream("/Users/felixklein/Downloads/consistency_chapter_2_output.txt"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            var s = in.next();
            var k = in.nextInt();
            var graph = new HashMap<Character, ArrayList<Character>>();
            for (int j = 0; j < k; j++) {
                var ab = in.next();
                char a = ab.charAt(0), b = ab.charAt(1);
                graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            }
            var shortestPaths = getShortestPaths(graph);
            System.out.println("Case #" + i + ": " + getMin(s, shortestPaths));
        }
    }
    private static int getMin(String s, int[][] graph) {
        int min = Integer.MAX_VALUE;
        for (char t = 'A'; t <= 'Z'; t++) min = Math.min(min, change(s, t, graph));
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private static int change(String s, char t, int[][] graph) {
        int seconds = 0;
        for (var c : s.toCharArray()) {
            if (graph[c - 'A'][t - 'A'] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
            seconds += graph[c - 'A'][t - 'A'];
        }
        return seconds;
    }
    private static int[][] getShortestPaths(HashMap<Character, ArrayList<Character>> graph) {
        var shortestPaths = new int[26][26];
        for (int i = 0; i < 26; i++) for (int j = 0; j < 26; j++) shortestPaths[i][j] = Integer.MAX_VALUE;
        for (char c = 'A'; c <= 'Z'; c++) bfs(shortestPaths, c, graph);
        return shortestPaths;
    }
    private static void bfs(int[][] shortestPaths, char s, HashMap<Character, ArrayList<Character>> graph) {
        var q = new LinkedList<Character>();
        q.offer(s);
        int d = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                var t = q.poll();
                if (shortestPaths[s - 'A'][t - 'A'] <= d) continue;
                shortestPaths[s - 'A'][t - 'A'] = d;
                for (var u : graph.getOrDefault(t, new ArrayList<>())) q.offer(u);
            }
            d++;
        }
    }
}