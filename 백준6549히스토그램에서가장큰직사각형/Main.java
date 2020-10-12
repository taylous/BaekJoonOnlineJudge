package 백준6549히스토그램에서가장큰직사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./src/백준6549히스토그램에서가장큰직사각형/sample_input.txt"));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        int[] histograms;
        int n;

        while (true) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if (n == 0)
                break;

            histograms = new int[n];
            for (int i = 0; i < n; i++)
                histograms[i] = Integer.parseInt(st.nextToken());
            answer.append(findLargestRectangleInHistogram(histograms, n));
            answer.append("\n");
        }
        System.out.print(answer.toString());
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
