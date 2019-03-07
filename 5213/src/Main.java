import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Location implements Comparable<Location> {

	int index;
	int x;
	int y;
	int c;

	public Location(int index, int x, int y, int c) {

		this.index = index;
		this.x = x;
		this.y = y;
		this.c = c;
	}

	@Override
	public int compareTo(Location other) {

		return this.c < other.c ? -1 : 1;
	}
}

class Domino {

	int index;
	int left;
	int right;

	public Domino(int index, int left, int right) {

		this.index = index;
		this.left = left;
		this.right = right;
	}
}

public class Main {

	static Domino[][] domino;
	static ArrayDeque<Integer> pathDeque = new ArrayDeque<>();

	static int[][] loX = {

			{ -1, -1, 0, 1, 1, 0 }, { -1, -1, 0, 1, 1, 0 } };

	static int[][] loY = {

			{ -1, 0, 1, 0, -1, -1 }, { 0, 1, 1, 1, 0, -1 } };

	static int Answer;
	static int Number;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		Number = 1;

		domino = new Domino[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {

			for (int j = 1; j <= N + (i % 2 == 1 ? 0 : -1); j++) {

				st = new StringTokenizer(br.readLine());

				domino[i][j] = new Domino(Number++, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
		}
		Answer = Integer.MAX_VALUE;
		totem();

		sb.append(pathDeque.size());
		sb.append("\n");
		while (!pathDeque.isEmpty()) {
			
			sb.append(pathDeque.removeLast());
			sb.append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void totem() {

		PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		int[] path = new int[Number + 1];

		int maxVertex = 0, vertex, oddOrEven, start;

		priorityQueue.offer(new Location(1, 1, 1, 1));
		visited[1][1] = true;

		while (!priorityQueue.isEmpty()) {

			Location location = priorityQueue.poll();
			int index = location.index;
			int x = location.x;
			int y = location.y;
			int c = location.c;

			maxVertex = Math.max(maxVertex, index);

			if (x % 2 == 1)
				oddOrEven = 0;
			else
				oddOrEven = 1;

			for (int i = 0; i < 6; i++) {

				int nx = x + loX[oddOrEven][i];
				int ny = y + loY[oddOrEven][i];

				if (nx < 0 || nx > N || ny < 0 || ny > N)
					continue;
				if (visited[nx][ny] || domino[nx][ny] == null)
					continue;

				if (oddOrEven == 0) {

					if (i == 0 || i == 4 || i == 5) {

						if (domino[nx][ny].right == domino[x][y].left) {

							visited[nx][ny] = true;
							path[domino[nx][ny].index] = index;
							priorityQueue.offer(new Location(domino[nx][ny].index, nx, ny, c + 1));
						}
					} else {

						if (domino[nx][ny].left == domino[x][y].right) {

							visited[nx][ny] = true;
							path[domino[nx][ny].index] = index;
							priorityQueue.offer(new Location(domino[nx][ny].index, nx, ny, c + 1));
						}
					}
				} else {

					if (i == 1 || i == 2 || i == 3) {

						if (domino[nx][ny].left == domino[x][y].right) {

							visited[nx][ny] = true;
							path[domino[nx][ny].index] = index;
							priorityQueue.offer(new Location(domino[nx][ny].index, nx, ny, c + 1));
						}
					} else {

						if (domino[nx][ny].right == domino[x][y].left) {

							visited[nx][ny] = true;
							path[domino[nx][ny].index] = index;
							priorityQueue.offer(new Location(domino[nx][ny].index, nx, ny, c + 1));
						}
					}
				}
			}
		}
		pathDeque.add(maxVertex);
		start = maxVertex;

		while (true) {

			vertex = path[start];

			if (vertex == 0)
				break;

			pathDeque.add(vertex);
			start = vertex;
		}
	}
}
