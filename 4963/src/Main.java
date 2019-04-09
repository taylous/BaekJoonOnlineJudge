import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Location {

    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[][] earth;
    static boolean[][] visited;

    static int[] loX = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] loY = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int Answer;
	static int width;
	static int height;
	
	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		while(true) {

		    st = new StringTokenizer(br.readLine());

            width = Integer.parseInt(st.nextToken());
		    if(width == 0)
		        break;

            height = Integer.parseInt(st.nextToken());

		    earth = new int[height][width];
		    visited = new boolean[height][width];

		    for(int i = 0; i < height; i++) {

		        st = new StringTokenizer(br.readLine());

		        for(int j = 0; j < width; j++)
		            earth[i][j] = Integer.parseInt(st.nextToken());
            }

		    for(int i = 0; i < height; i++) {

		        for(int j = 0; j < width; j++) {

		            if(!visited[i][j] && earth[i][j] == 1) {
                        numberOfIslands(i, j);
                        Answer++;
                    }
                }
            }
            System.out.println(Answer);
		    Answer = 0;
        }
		br.close();
	}

	static void numberOfIslands(int startX, int startY) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        visited[startX][startY] = true;
        arrayDeque.add(new Location(startX, startY));

        Location location;
        int x, y, nx, ny;

        while(!arrayDeque.isEmpty()) {

            location = arrayDeque.remove();
            x = location.x;
            y = location.y;

            for(int i = 0; i < 8; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if(nx < 0 || nx >= height || ny < 0 || ny >= width)
                    continue;
                if(visited[nx][ny] || earth[nx][ny] == 0)
                    continue;

                visited[nx][ny] = true;
                arrayDeque.add(new Location(nx, ny));
            }
        }
    }
}