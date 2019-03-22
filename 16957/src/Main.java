import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Ball {

	int x;
	int y;

	public Ball(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int[][] Answer;
	static int[][] board;

	static int[] loX = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] loY = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int R;
	static int C;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		Answer = new int[R][C];
		board = new int[R][C];

		for (int i = 0; i < R; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < C; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		ballOnChessboard();

		for (int i = 0; i < R; i++) {

			for (int j = 0; j < C; j++)
				System.out.print(Answer[i][j] + " ");
			System.out.println();
		}

		br.close();
	}

	static void ballOnChessboard() {

		ArrayDeque<Ball> arrayDeque = new ArrayDeque<>();
		int min, minX, minY;

		for (int i = 0; i < R; i++) {

			for (int j = 0; j < C; j++) {

				arrayDeque.add(new Ball(i, j));
				min = board[i][j];
				minX = minY = -1;

				while (!arrayDeque.isEmpty()) {

					Ball ball = arrayDeque.remove();
					int x = ball.x;
					int y = ball.y;

					for (int k = 0; k < 8; k++) {

						int nx = x + loX[k];
						int ny = y + loY[k];

						if (nx < 0 || nx >= R || ny < 0 || ny >= C)
							continue;

						if (min > board[nx][ny]) {

							min = board[nx][ny];
							minX = nx;
							minY = ny;
						}
					}

					if (minX == -1)
						Answer[x][y]++;
					else {

						min = board[minX][minY];
						arrayDeque.add(new Ball(minX, minY));
						minX = minY = -1;
					}
				}
			}
		}
	}
}
