package 백준9370미확인도착지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Node implements Comparable<Node> {

        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    private static int[][] adjacent;
    private static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        ArrayList<Integer> candidates;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {

            candidates = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            adjacent = new int[n + 1][n + 1];

            for (int i = 0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                adjacent[from][to] = weight;
                adjacent[to][from] = weight;
            }
            for (int i = 0; i < t; i++)
                candidates.add(Integer.parseInt(br.readLine()));
            Collections.sort(candidates);

            int[] distance = simulateShortestDistance(s);
            int startPoint = distance[g] < distance[h] ? h : g;
            int[] distanceFromGH = simulateShortestDistance(startPoint);

            for (int candidate : candidates) {

                if (distance[candidate] != Integer.MAX_VALUE && distance[startPoint] + distanceFromGH[candidate] <= distance[candidate])
                    answer.append(candidate).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer.toString());
        br.close();
    }

    private static int[] simulateShortestDistance(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            int from = node.vertex;
            int weight = node.weight;

            if (distance[from] < weight)
                continue;

            for (int to = 1; to <= n; to++) {

                if (adjacent[from][to] > 0 && distance[to] > adjacent[from][to] + weight) {
                    distance[to] = adjacent[from][to] + weight;
                    pq.offer(new Node(to, distance[to]));
                }
            }
        }
        return distance;
    }
}
