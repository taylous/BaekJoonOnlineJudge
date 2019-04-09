import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {

    int vertex;
    int weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight < other.weight ? -1 : 1;
    }
}

public class Main {

    static ArrayList<ArrayList<Edge>> adjacent = new ArrayList<>();

	static int V;
	static int E;
	static int K;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int from, to, weight;

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        for(int i = 0; i <= V; i++)
            adjacent.add(new ArrayList<>());

        while(E-- > 0) {

            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjacent.get(from).add(new Edge(to, weight));
        }
        shortestPath();
		br.close();
	}

	static void shortestPath() {

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        StringBuffer sb = new StringBuffer();
        Edge edge;

	    int[] distance = new int[V + 1];
	    int from, weight;

        Arrays.fill(distance, 987654321);
        distance[K] = 0;
        priorityQueue.offer(new Edge(K, distance[K]));

        while(!priorityQueue.isEmpty()) {

            edge = priorityQueue.poll();
            from = edge.vertex;
            weight = edge.weight;

            if(weight > distance[from])
                continue;

            for(Edge to : adjacent.get(from)) {

                if(distance[to.vertex] > distance[from] + to.weight) {

                    distance[to.vertex] = distance[from] + to.weight;
                    priorityQueue.offer(new Edge(to.vertex, distance[to.vertex]));
                }
            }
        }

        for(int i = 1; i <= V; i++) {

            sb.append(distance[i] >= 987654321 ? "INF" : distance[i]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}