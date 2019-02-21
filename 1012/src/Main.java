import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


class Location {

	int x;
	int y;

	Location(int x, int y) {

		this.x = x;
		this.y = y;
	}
}

public class Main {

	static boolean[][] cabbageField;
	static boolean[][] visited;

	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int N;
	static int M;
	static int K;
	
	public static void main(String args[]) throws Exception	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cabbageField = new boolean[N + 1][M + 1];
			visited = new boolean[N + 1][M + 1];
			while(K-- > 0) {

				st = new StringTokenizer(br.readLine(), " ");

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				cabbageField[x][y] = true;
			}

			for(int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (cabbageField[i][j] && !visited[i][j]) {

						OrganicCabbage(i, j);
						Answer++;
					}
				}
			}

			System.out.println(Answer);
			Answer = 0;
		}
		
		br.close();
	}

	static void OrganicCabbage(int startX, int startY) {

		ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
		arrayDeque.add(new Location(startX, startY));
		visited[startX][startY] = true;

		while(!arrayDeque.isEmpty()) {

			Location container = arrayDeque.remove();
			int x = container.x;
			int y = container.y;

			for(int i = 0; i < 4; i++) {

				int nx = x + loX[i];
				int ny = y + loY[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
					continue;
				if(!cabbageField[nx][ny])
					continue;

				arrayDeque.add(new Location(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}