import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Location implements Comparable<Location> {

	int x;
	int y;
	int times;

	Location(int x, int y, int times) {

		this.x = x;
		this.y = y;
		this.times = times;
	}

	@Override
	public int compareTo(Location other) {

		return this.times < other.times ? 1 : -1;
	}
}

public class Main {

	static char[][] map;

	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] container;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {

			container = br.readLine().toCharArray();

			for (int j = 0; j < M; j++)
				map[i][j] = container[j];
		}
		System.out.println(alphabet());

		br.close();
	}

	static int alphabet() {

		PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
		int x, y, nx, ny, times, temp = 0;
		int ret = 0;

		temp |= (1 << (map[0][0] - 'A'));
		priorityQueue.offer(new Location(0, 0, temp));

		while (!priorityQueue.isEmpty()) {

			Location location = priorityQueue.poll();
			x = location.x;
			y = location.y;
			times = location.times;

			ret = Math.max(ret, Integer.bitCount(times));

			for (int i = 0; i < 4; i++) {

				nx = x + loX[i];
				ny = y + loY[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if ((times & (1 << (map[nx][ny] - 'A'))) > 0)
					continue;

				temp = (times | (1 << (map[nx][ny] - 'A')));
				priorityQueue.offer(new Location(nx, ny, temp));
			}
		}
		return ret;
	}
}
