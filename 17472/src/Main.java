import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Island {

	int x;
	int y;

	public Island(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {

	int startVertex;
	int endVertex;
	int weight;

	public Edge(int startVertex, int endVertex, int weight) {
		super();
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight < other.weight ? -1 : 1;
	}
}

class Main {

	static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
	static ArrayList<Island> islands = new ArrayList<>();
	
	static int[][] map;
	static int[] parents;

	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int N;
	static int M;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Edge edge;
		int x, y, sV, eV, sP, eP;
		int number, weight, check = -1, root;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = -1;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1)
					islands.add(new Island(i, j));
			}
		}

		for (Island island : islands) {

			x = island.x;
			y = island.y;

			if (map[x][y] == 1)
				numbering(x, y, number--);
		}
		number *= -1;
		parents = new int[number];
		
		for(int i = 0; i < number; i++)
			parents[i] = i;
		constructBridges();

		while (!priorityQueue.isEmpty()) {

			edge = priorityQueue.poll();
			sV = edge.startVertex;
			eV = edge.endVertex;
			weight = edge.weight;

			sP = getRoot(sV);
			eP = getRoot(eV);

			if (sP == eP)
				continue;
			
			parents[eP] = sV;
			Answer += weight;
		}
		
		for(int i = 1; i < number; i++) {
			
			root = getRoot(i);
			
			if(check == -1) {
				check = root;
			}
			else {
				
				if(check != root) {
					Answer = -1;
					break;
				}
			}
		}
		System.out.println(Answer);
		priorityQueue.clear();
		islands.clear();

		br.close();
	}

	static int getRoot(int vertex) {

		if (parents[vertex] == vertex)
			return vertex;
		return parents[vertex] = getRoot(parents[vertex]);
	}

	static void numbering(int startX, int startY, int number) {

		ArrayDeque<Island> arrayDeque = new ArrayDeque<Island>();
		arrayDeque.add(new Island(startX, startY));
		map[startX][startY] = number;

		Island island;
		int x, y, nx, ny;

		while (!arrayDeque.isEmpty()) {

			island = arrayDeque.remove();
			x = island.x;
			y = island.y;

			for (int i = 0; i < 4; i++) {

				nx = x + loX[i];
				ny = y + loY[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (map[nx][ny] == 0 || map[nx][ny] != 1)
					continue;

				map[nx][ny] = number;
				arrayDeque.add(new Island(nx, ny));
			}
		}
	}

	static boolean checkDirection(int x, int y, int dir) {

		int nx = x + loX[dir];
		int ny = y + loY[dir];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M)
			return false;
		if (map[nx][ny] != 0)
			return false;
		return true;
	}

	static void constructBridges() {

		int x, y, nx, ny;
		int length, endVertex;

		for (Island island : islands) {

			x = island.x;
			y = island.y;

			for (int i = 0; i < 4; i++) {

				if (!checkDirection(x, y, i))
					continue;

				nx = x + loX[i];
				ny = y + loY[i];
				length = 0;
				endVertex = -1;

				while (true) {

					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						break;
					if (map[nx][ny] != 0) {

						if (map[nx][ny] != map[x][y])
							endVertex = map[nx][ny];
						break;
					}

					nx += loX[i];
					ny += loY[i];
					length++;
				}
				if (length != 1 && endVertex != -1)
					priorityQueue.offer(new Edge(map[x][y] * -1, endVertex * -1, length));
			}
		}
	}
}