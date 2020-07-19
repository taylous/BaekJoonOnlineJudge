package 백준11657타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        ArrayList<ArrayList<Location>> adjacent = new ArrayList<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        boolean[] inQue;
        long[] dist;
        int[] isCycle;
        int from, to, cost;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++)
            adjacent.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            adjacent.get(from).add(new Location(to, cost));
        }

        inQue = new boolean[n + 1];
        dist = new long[n + 1];
        isCycle = new int[n + 1];

        Arrays.fill(dist, INF);

        arrayDeque.add(1);
        dist[1] = 0;
        isCycle[1] += 1;

        while (!arrayDeque.isEmpty()) {

            from = arrayDeque.remove();
            inQue[from] = false;

            for (Location location : adjacent.get(from)) {

                to = location.vertex;
                cost = location.cost;

                if (dist[to] > dist[from] + cost) {

                    dist[to] = dist[from] + cost;

                    if (!inQue[to]) {

                        isCycle[to] += 1;

                        if (isCycle[to] >= n) {
                            System.out.println(-1);
                            return;
                        }
                        arrayDeque.add(to);
                        inQue[to] = true;
                    }
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            sb.append(dist[i] != INF ? dist[i] : -1);
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

class Location {

    int vertex;
    int cost;

    public Location(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}