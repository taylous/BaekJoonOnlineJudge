package 백준11657타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int INF = 987654321;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb;

        ArrayList<Edge> edges = new ArrayList<>();
        long[] dist;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean flag = false;
        int from, to, cost;

        dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, cost));
        }

        for (int i = 1; i <= n - 1; i++) {
            for (Edge edge : edges) {

                if (dist[edge.from] == INF)
                    continue;

                if (dist[edge.to] > dist[edge.from] + edge.cost)
                    dist[edge.to] = dist[edge.from] + edge.cost;
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] == INF)
                continue;

            if (dist[edge.to] > dist[edge.from] + edge.cost) {
                flag = true;
                break;
            }
        }

        if (flag) {
            System.out.println(-1);
        } else {

            sb = new StringBuffer();

            for (int i = 2; i <= n; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]);
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }
        br.close();
    }
}

class Edge {

    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}