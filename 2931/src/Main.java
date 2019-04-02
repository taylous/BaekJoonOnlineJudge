import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Path {

	boolean[] connected;
	char type;

	public Path(char type) {
		super();
		this.connected = new boolean[4];
		this.type = type;
	}
}

class Location {

	int x;
	int y;

	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static Path[][] map;

	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };

	static int AnswerX;
	static int AnswerY;
	static char AnswerType;

	static int N;
	static int R;
	static int C;

	static int destX;
	static int destY;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		char[] container;
		int HomeX = 0, HomeY = 0;

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new Path[R][C];

		for (int i = 0; i < R; i++) {

			container = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {

				map[i][j] = new Path(container[j]);

				switch (map[i][j].type) {

				case 'M':

					HomeX = i;
					HomeY = j;
					map[i][j].connected[0] = true;
					map[i][j].connected[1] = true;
					map[i][j].connected[2] = true;
					map[i][j].connected[3] = true;
					break;

				case 'Z':

					destX = i;
					destY = j;
					map[i][j].connected[0] = true;
					map[i][j].connected[1] = true;
					map[i][j].connected[2] = true;
					map[i][j].connected[3] = true;
					break;

				case '|':

					map[i][j].connected[0] = true;
					map[i][j].connected[2] = true;
					break;

				case '-':

					map[i][j].connected[1] = true;
					map[i][j].connected[3] = true;
					break;

				case '+':

					map[i][j].connected[0] = true;
					map[i][j].connected[1] = true;
					map[i][j].connected[2] = true;
					map[i][j].connected[3] = true;
					break;

				case '1':

					map[i][j].connected[1] = true;
					map[i][j].connected[2] = true;
					break;

				case '2':

					map[i][j].connected[0] = true;
					map[i][j].connected[1] = true;
					break;

				case '3':

					map[i][j].connected[0] = true;
					map[i][j].connected[3] = true;
					break;

				case '4':

					map[i][j].connected[2] = true;
					map[i][j].connected[3] = true;
					break;
				}
			}
		}
		pathConnect(HomeX, HomeY);
		sb.append(AnswerX);
		sb.append(" ");
		sb.append(AnswerY);
		sb.append(" ");
		sb.append(AnswerType);
		sb.append("\n");
		System.out.println(sb.toString());
		br.close();
	}

	static char getPath(boolean[] connected) {

		if (connected[0] && !connected[1] && connected[2] && !connected[3])
			return '|';
		else if (!connected[0] && connected[1] && !connected[2] && connected[3])
			return '-';
		else if (connected[0] && connected[1] && connected[2] && connected[3])
			return '+';
		else if (!connected[0] && connected[1] && connected[2] && !connected[3])
			return '1';
		else if (connected[0] && connected[1] && !connected[2] && !connected[3])
			return '2';
		else if (connected[0] && !connected[1] && !connected[2] && connected[3])
			return '3';
		return '4';
	}

	static void pathConnect(int startX, int startY) {

		ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];

		Location location;
		boolean[] connected;
		int x, y, nx, ny;

		for (int i = 0; i < 4; i++) {

			nx = startX + loX[i];
			ny = startY + loY[i];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			if (map[nx][ny].type == '.') {
				
				
				continue;
			}

			visited[nx][ny] = true;
			arrayDeque.add(new Location(nx, ny));
			break;
		}

		while (!arrayDeque.isEmpty()) {

			location = arrayDeque.remove();
			x = location.x;
			y = location.y;

			if (map[x][y].type == '.') {

				System.out.println("x : " + x + " y : " + y);
				connected = new boolean[4];

				for (int i = 0; i < 4; i++) {

					nx = x + loX[i];
					ny = y + loY[i];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;

					if (i == 0)
						connected[i] = map[nx][ny].connected[2];
					else if (i == 1)
						connected[i] = map[nx][ny].connected[3];
					else if (i == 2)
						connected[i] = map[nx][ny].connected[0];
					else
						connected[i] = map[nx][ny].connected[1];
				}

				AnswerX = x + 1;
				AnswerY = y + 1;
				AnswerType = getPath(connected);
				break;
			}
			connected = map[x][y].connected;

			for (int i = 0; i < 4; i++) {

				if (connected[i]) {

					nx = x + loX[i];
					ny = y + loY[i];

					if (visited[nx][ny])
						continue;

					visited[nx][ny] = true;
					arrayDeque.add(new Location(nx, ny));
				}
			}
		}
	}
}
