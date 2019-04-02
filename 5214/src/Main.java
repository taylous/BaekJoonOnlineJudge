import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Location {

	int from;
	int count;

	public Location(int from, int count) {
		super();
		this.from = from;
		this.count = count;
	}
}

public class Main {

	static ArrayList<ArrayList<Integer>> hyperTube = new ArrayList<>();
	static int hyperTubeIndex = 100000;

	static int Answer;
	static int N;
	static int K;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int vertex;

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= 101002; i++)
			hyperTube.add(new ArrayList<>());

		for (int i = 1; i <= M; i++) {

			st = new StringTokenizer(br.readLine());

			for (int tube = 0; tube < K; tube++) {

				vertex = Integer.parseInt(st.nextToken());

				hyperTube.get(hyperTubeIndex + i).add(vertex);
				hyperTube.get(vertex).add(hyperTubeIndex + i);
			}
		}
		System.out.println(transfer());
		br.close();
	}

	static int transfer() {

		ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
		boolean[] visited = new boolean[101002];

		Location location;
		int from, count;

		arrayDeque.add(new Location(1, 1));
		visited[1] = true;

		while (!arrayDeque.isEmpty()) {

			location = arrayDeque.remove();
			from = location.from;
			count = location.count;

			if (from == N)
				return count;

			for (int to : hyperTube.get(from)) {

				if (!visited[to]) {

					visited[to] = true;
					if (to > 100000)
						arrayDeque.add(new Location(to, count));
					else
						arrayDeque.add(new Location(to, count + 1));
				}
			}
		}
		return -1;
	}
}
