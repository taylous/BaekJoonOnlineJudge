import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

class RoundWood {

	int[][] log = new int[3][2];
	int shape;
	int count;

	public RoundWood(int leftX, int leftY, int midX, int midY, int rightX, int rightY, int shape, int count) {
		super();
		this.log[0][0] = leftX;
		this.log[0][1] = leftY;
		this.log[1][0] = midX;
		this.log[1][1] = midY;
		this.log[2][0] = rightX;
		this.log[2][1] = rightY;
		this.shape = shape;
		this.count = count;
	}
}

public class Main {

	static char[][] field;
	static int[][] destination = new int[3][2];

	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] location = new int[3][2];
		char[] container;

		int start = 0, end = 0;

		N = Integer.parseInt(br.readLine());
		field = new char[N][N];

		for (int i = 0; i < N; i++) {

			container = br.readLine().toCharArray();

			for (int j = 0; j < N; j++) {

				field[i][j] = container[j];

				if (field[i][j] == 'B') {

					location[start][0] = i;
					location[start++][1] = j;
				} else if (field[i][j] == 'E') {

					destination[end][0] = i;
					destination[end++][1] = j;
				}
			}
		}
		movingLogs(location);
		System.out.println(Answer);
		br.close();
	}
	
	static boolean chk(int[][] log) {
		
		for(int i = 0; i < 3; i++) {
			
			if(log[i][0] != destination[i][0] || log[i][1] != destination[i][1])
				return false;
		}
		return true;
	}

	static void movingLogs(int[][] location) {

		ArrayDeque<RoundWood> arrayDeque = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][N][2];
		boolean[] chk = new boolean[4];
		boolean flag, turn;

		int[][] log;
		int[][] temp = new int[3][2];
		int nx, ny, count, shape = 0;

		if (location[0][0] == location[1][0])
			shape = 1;

		arrayDeque.add(new RoundWood(location[0][0], location[0][1], location[1][0], location[1][1], location[2][0],
				location[2][1], shape, 0));
		visited[location[1][0]][location[1][1]][shape] = true;

		while (!arrayDeque.isEmpty()) {

			RoundWood roundWood = arrayDeque.remove();
			log = roundWood.log.clone();
			shape = roundWood.shape;
			count = roundWood.count;
			
			if(chk(log)) {
				
				Answer = Math.max(Answer, count);
				continue;
			}
			
			Arrays.fill(chk, false);
			for (int i = 0; i < 4; i++) {

				flag = false;
				turn = true;

				for (int offset = 0; offset < 3; offset++) {

					nx = roundWood.log[offset][0] + loX[i];
					ny = roundWood.log[offset][1] + loY[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || field[nx][ny] == '1') {

						flag = true;
						turn = false;
						break;
					}
					if(visited[nx][ny][shape]) {

						if(shape == 1) {
							
							if((i == 1 && offset == 0) || (i == 3 && offset == 2)) {
								
								temp[offset][0] = nx;
								temp[offset][1] = ny;
								continue;
							}
						}
						else {
							
							if((i == 0 && offset == 2) || (i == 2 && offset == 0)) {
								
								temp[offset][0] = nx;
								temp[offset][1] = ny;
								continue;
							}
						}
						flag = true;
						break;
					}
					
					temp[offset][0] = nx;
					temp[offset][1] = ny;
				}
				if(turn)
					chk[i] = true;

				if (!flag) {

					if(!visited[temp[1][0]][temp[1][1]][shape]) {
						
						visited[temp[1][0]][temp[1][1]][shape] = true;
						arrayDeque.add(new RoundWood(temp[0][0], temp[0][1], temp[1][0],
							temp[1][1], temp[2][0], temp[2][1], shape, count + 1));
					}
				}
			}
			
			if(shape == 1) {
				
				if(chk[0] && chk[2] && !visited[log[1][0]][log[1][1]][0]) {
					
					visited[log[1][0]][log[1][1]][0] = true;
					arrayDeque.add(new RoundWood(log[1][0] - 1, log[1][1], log[1][0], log[1][1], log[1][0] + 1, log[1][1], 0, count + 1));
				}
			}
			else {
				
				if(chk[1] && chk[3] && !visited[log[1][0]][log[1][1]][1]) {
					
					visited[log[1][0]][log[1][1]][1] = true;
					arrayDeque.add(new RoundWood(log[1][0], log[1][1] - 1, log[1][0], log[1][1], log[1][0], log[1][1] + 1, 1, count + 1));
				}
			}
		}
	}
}