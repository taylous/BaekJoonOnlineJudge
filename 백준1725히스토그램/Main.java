package 백준1725히스토그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] histograms;
        int n = Integer.parseInt(br.readLine());

        histograms = new int[n];

        for (int i = 0; i < n; i++)
            histograms[i] = Integer.parseInt(br.readLine());
        System.out.println(findLargestRectangleInHistogram(histograms, n));
        br.close();
    }

    public static long findLargestRectangleInHistogram(int[] histograms, int n) {

        Stack<Integer> stack = new Stack<>();
        long area = 0;

        for (int i = 0; i < n; i++) {
            while (!stack.empty() && histograms[stack.peek()] > histograms[i]) {
                long height = histograms[stack.pop()];

                long width = i;
                if (!stack.empty())
                    width = (i - stack.peek() - 1);
                area = Math.max(area, width * height);
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            long height = histograms[stack.pop()];
            long width = n;
            if (!stack.empty())
                width = n - stack.peek() - 1;
            area = Math.max(area, width * height);
        }
        return area;
    }
}
