import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

class Location {

    int x;
    int y;

    Location(int x, int y) {

        this.x = x;
        this.y = y;
    }
}

public class Main {

    static char[][] grid;
    static boolean[][] visited;

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

	static int[] Answer =  new int[2];
	static int N;
	
	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] container;

		N = Integer.parseInt(br.readLine());

		grid = new char[N][N];
        visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {

		    container = br.readLine().toCharArray();

		    for(int j = 0; j < N; j++)
		        grid[i][j] = container[j];
        }

		for(int k = 0; k < 2; k++) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (!visited[i][j]) {

                        redEyeColorPowder(i, j, grid[i][j], k == 1);
                        Answer[k]++;
                    }
                }
            }

            if(k == 0) {

                for(int row = 0; row < N; row++)
                    Arrays.fill(visited[row], false);
            }
        }
        System.out.println(Answer[0] + " " + Answer[1]);
		br.close();
	}

    static void redEyeColorPowder(int startX, int startY, char color, boolean chk) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        Location t;
        int x, y, nx, ny;

        arrayDeque.add(new Location(startX, startY));
        visited[startX][startY] = true;

        while(!arrayDeque.isEmpty()) {

            t = arrayDeque.remove();
            x = t.x;
            y = t.y;

            for(int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if(visited[nx][ny])
                    continue;
                if(chk) {
                    if (color == 'R' || color == 'G') {

                        if (grid[nx][ny] == 'B')
                            continue;
                    } else {

                        if (grid[nx][ny] != 'B')
                            continue;
                    }
                }
                else {

                    if(grid[nx][ny] != color)
                        continue;
                }
                visited[nx][ny] = true;
                arrayDeque.add(new Location(nx, ny));
            }
        }
    }
}