import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Location implements Comparable<Location> {

    int vertex;
    int weight;

    public Location(int vertex, int weight) {

        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Location other) {
        return this.weight < other.weight ? -1 : 1;
    }
}

public class Main {

    static int[][] adjacent;
    static int[] toHome;

	static int Answer;
	static int N;
	static int M;
	static int X;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjacent = new int[N + 1][N + 1];
        toHome = new int[N + 1];

        while(M-- > 0) {

            st = new StringTokenizer(br.readLine());
            adjacent[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        Party(X, true);

        for(int start = 1; start <= N; start++) {

            if(start == X)
                continue;
            Answer = Math.max(Answer, Party(start, false));
        }
        System.out.println(Answer);
		br.close();
	}

	static int Party(int start, boolean flag) {

        PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        priorityQueue.offer(new Location(start, 0));

        while(!priorityQueue.isEmpty()) {

            Location location = priorityQueue.poll();
            int here = location.vertex;
            int weight = location.weight;

            if(visited[here])
                continue;
            if(weight > dist[here])
                continue;
            visited[here] = true;

            for(int there = 1; there <= N; there++) {

                if(adjacent[here][there] > 0 && dist[there] > adjacent[here][there] + dist[here]) {

                    dist[there] = adjacent[here][there] + dist[here];
                    priorityQueue.offer(new Location(there, dist[there]));
                }
            }
        }

        if(flag)
            System.arraycopy(dist, 0, toHome, 0, N + 1);
        return dist[X] + toHome[start];
    }
}