import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static double[][] circlePlate;
	private static int[][] check;

	private static int[] loX = { -1, 0, 1, 0 };
	private static int[] loY = { 0, 1, 0, -1 };

	private static int N;
	private static int M;
	private static int T;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int number, direction, times;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		circlePlate = new double[N + 1][M];
		check = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++)
				circlePlate[i][j] = Double.parseDouble(st.nextToken());
		}

		while (T-- > 0) {

			st = new StringTokenizer(br.readLine());

			number = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			times = Integer.parseInt(st.nextToken());

			for (int i = number; i <= N; i += number)
				rotate(i, direction, times);

			for (int i = 1; i <= N; i++) {

				for (int j = 0; j < M; j++) {

					if (circlePlate[i][j] != 0) {
						examine(i, j, circlePlate[i][j]);
					}
				}
			}
			delete();
		}
		System.out.println(sum());
		br.close();
	}

	private static void delete() {

		double avg = 0;
		int total = 0;
		boolean flag = false;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {

				if (check[i][j] > 1) {
					circlePlate[i][j] = 0;
					flag = true;
				} else if (check[i][j] == 1) {
					avg += (double) circlePlate[i][j];
					total++;
				}
				check[i][j] = 0;
			}
		}

		if (!flag) {

			avg /= total;

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {

					if (circlePlate[i][j] != 0)
						circlePlate[i][j] += (double) circlePlate[i][j] > avg ? -1
								: (double) circlePlate[i][j] < avg ? 1 : 0;
				}
			}
		}
	}

	private static void rotate(int idx, int direction, int times) {

		double temp;

		if (direction == 0) {

			while (times-- > 0) {

				temp = circlePlate[idx][M - 1];

				for (int i = M - 2; i >= 0; i--)
					circlePlate[idx][i + 1] = circlePlate[idx][i];
				circlePlate[idx][0] = temp;
			}
		} else {

			while (times-- > 0) {

				temp = circlePlate[idx][0];

				for (int i = 0; i < M - 1; i++)
					circlePlate[idx][i] = circlePlate[idx][i + 1];
				circlePlate[idx][M - 1] = temp;
			}
		}
	}

	private static void examine(int row, int col, double pre) {

		check[row][col] += 1;

		for (int i = 0; i < 4; i++) {

			int nx = row + loX[i];
			int ny = col + loY[i];

			if (nx <= 0 || nx > N)
				continue;
			if (ny < 0)
				ny = M - 1;
			if (ny >= M)
				ny = 0;
			if (circlePlate[nx][ny] != pre)
				continue;

			check[nx][ny] += 1;
		}
	}

	private static int sum() {

		int ret = 0;

		for (int i = 1; i <= N; i++)
			for (int j = 0; j < M; j++)
				ret += circlePlate[i][j];
		return ret;
	}
}
