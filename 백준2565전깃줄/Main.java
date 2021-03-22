package 백준2565전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire> {
    int start, dest;

    public Wire(int start, int dest) {
        this.start = start;
        this.dest = dest;
    }

    @Override
    public int compareTo(Wire other) {
        return this.start - other.start;
    }
}

public class Main {

    private static ArrayList<Wire> wires;
    private static int[] cache;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;

        N = Integer.parseInt(br.readLine());
        wires = new ArrayList<>();
        cache = new int[501];
        Arrays.fill(cache, -1);

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            wires.add(new Wire(start, dest));
        }
        Collections.sort(wires);

        for (int i = 0; i < N; i++)
            answer = Math.max(answer, longestIncreasingSubsequence(i));
        System.out.println(N - answer);
        br.close();
    }

    private static int longestIncreasingSubsequence(int index) {

        if (cache[index] != -1)
            return cache[index];

        int ret = 1;
        for (int i = index + 1; i < N; i++) {
            if (wires.get(index).dest < wires.get(i).dest)
                ret = Math.max(ret, longestIncreasingSubsequence(i) + 1);
        }
        return cache[index] = ret;
    }
}
