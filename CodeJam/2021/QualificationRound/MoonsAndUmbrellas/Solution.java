import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author lucasr
 */
public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyScanner in = new MyScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MoonsAndUmbrellas solver = new MoonsAndUmbrellas();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class MoonsAndUmbrellas {
        public void solve(int testNumber, MyScanner sc, PrintWriter out) {
            int CJ = sc.nextInt();
            int JC = sc.nextInt();
            char[] vals = sc.next().toCharArray();
            int N = vals.length;
            int ans = Integer.MAX_VALUE;
            for (int bbb = 0; bbb < 2; bbb++) {
                char beg = getChar(bbb);
                for (int eee = 0; eee < 2; eee++) {
                    char end = getChar(eee);
                    if ((vals[0] == beg || vals[0] == '?') && (vals[N - 1] == end || vals[N - 1] == '?')) {
                        for (int ddd = 0; ddd < 2; ddd++) {
                            int tot = 0;
                            boolean diff = ddd == 0;
                            char last = beg;
                            for (int i = 1; i < N; i++) {
                                char cur;
                                if (i < N - 1) {
                                    if (vals[i] == '?') {
                                        cur = diff ? other(last) : last;
                                    } else {
                                        cur = vals[i];
                                    }
                                } else {
                                    cur = end;
                                }
                                if (last != cur) {
                                    tot += last == 'C' ? CJ : JC;
                                }
                                last = cur;
                            }
                            ans = Math.min(ans, tot);
                        }
                    }
                }
            }
            out.println("Case #" + testNumber + ": " + ans);
        }

        static char getChar(int i) {
            return i == 0 ? 'C' : 'J';
        }

        static char other(char c) {
            return c == 'C' ? 'J' : 'C';
        }

    }

    static class MyScanner {
        private BufferedReader br;
        private StringTokenizer tokenizer;

        public MyScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

