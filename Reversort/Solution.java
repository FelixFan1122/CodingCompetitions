import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) a[j] = in.nextInt();
            System.out.println("Case #" + i + ": " + getCost(a));
        }
    }
    private static int getCost(int[] a) {
        int cost = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int min = findMin(a, i);
            cost += min - i + 1;
            reverse(a, i, min);
        }
        return cost;
    }
    private static int findMin(int[] a, int start) {
        int min = start;
        for (int i = start + 1; i < a.length; i++) {
            if (a[i] < a[min]) min = i;
        }
        return min;
    }
    private static void reverse(int[] a, int start, int end) {
        while (start < end) {
            int temp = a[start];
            a[start++] = a[end];
            a[end--] = temp;
        }
    }
}