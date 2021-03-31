package 백준1197최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Edge implements Comparable<Edge> {

        int startVertex, endVertex, weight;

        public Edge(int startVertex, int endVertex, int weight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    private static final PriorityQueue<Edge> edges = new PriorityQueue<>();
    private static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parents = new int[v + 1];
        for (int i = 0; i <= v; i++)
            parents[i] = i;

        while (e-- > 0) {

            st = new StringTokenizer(br.readLine());
            edges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        while (!edges.isEmpty()) {

            Edge edge = edges.poll();

            if (getRoot(edge.startVertex) == getRoot(edge.endVertex))
                continue;

            union(edge.startVertex, edge.endVertex);
            answer += edge.weight;
        }
        System.out.println(answer);
        br.close();
    }

    private static int getRoot(int node) {

        if (parents[node] == node)
            return node;
        return parents[node] = getRoot(parents[node]);
    }

    private static void union(int sV, int eV) {

        int sP = getRoot(sV);
        int eP = getRoot(eV);

        if (sP != eP)
            parents[sP] = eP;
    }
}
