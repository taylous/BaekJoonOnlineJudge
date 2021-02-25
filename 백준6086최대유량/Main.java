package 백준6086최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 987654321;
    private static final int SIZE = 52;

    private static ArrayList<ArrayList<Integer>> adjacent;
    private static int[][] capacity;
    private static int[][] flows;

    private static int alphabetToIdx(char vertex) {

        if ('A' <= vertex && vertex <= 'Z')
            return vertex - 'A';
        return vertex - 'a' + 26;
    }

    private static int networkFlow() {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] prev = new int[SIZE];

        int source = 0, sink = 25, totalFlow = 0, flow;

        while (true) {

            Arrays.fill(prev, -1);
            deque.add(source);

            while (!deque.isEmpty()) {

                int from = deque.remove();

                for (int to : adjacent.get(from)) {
                    if (prev[to] != -1)
                        continue;

                    if (capacity[from][to] - flows[from][to] > 0) {
                        deque.add(to);
                        prev[to] = from;

                        if (to == sink)
                            break;
                    }
                }
            }
            if (prev[sink] == -1)
                break;

            flow = INF;
            for (int vertex = sink; vertex != source; vertex = prev[vertex])
                flow = Math.min(flow, capacity[prev[vertex]][vertex] - flows[prev[vertex]][vertex]);
            for (int vertex = sink; vertex != source; vertex = prev[vertex]) {
                flows[prev[vertex]][vertex] += flow;
                flows[vertex][prev[vertex]] -= flow;
            }
            totalFlow += flow;
        }
        return totalFlow;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        adjacent = new ArrayList<>();
        capacity = new int[SIZE][SIZE];
        flows = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
            adjacent.add(new ArrayList<>());
        while (n-- > 0) {

            int[] location = new int[2];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 2; i++)
                location[i] = alphabetToIdx(st.nextToken().charAt(0));
            int cost = Integer.parseInt(st.nextToken());

            capacity[location[0]][location[1]] += cost;
            capacity[location[1]][location[0]] += cost;
            adjacent.get(location[0]).add(location[1]);
            adjacent.get(location[1]).add(location[0]);
        }
        System.out.println(networkFlow());
        br.close();
    }
}
