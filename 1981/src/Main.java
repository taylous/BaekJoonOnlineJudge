import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

    static int[][] matrix;

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

	static int N;

	static int max;
	static int min;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;

		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {

		    st = new StringTokenizer(br.readLine());

		    for(int j = 0; j < N; j++) {

		        matrix[i][j] = Integer.parseInt(st.nextToken());

		        max = Math.max(max, matrix[i][j]);
		        min = Math.min(min, matrix[i][j]);
            }
        }
        System.out.println(moveInAnArray());
		br.close();
	}

	static int moveInAnArray() {

	    int left = 0;
	    int right = max - min;
	    int mid;

	    while(left <= right) {

	        mid = (left + right) / 2;

	        if(move(mid))
	            right = mid - 1;
	        else
	            left = mid + 1;

        }
	    return right + 1;
    }

    static boolean move(int offset) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
	    boolean[][] check = new boolean[N][N];

	    Location location;
	    int x, y, nx, ny;

	    for(int boundary = min; boundary <= max; boundary++) {

            for(int i = 0; i < N; i++)
                Arrays.fill(check[i], false);

	        for(int i = 0; i < N; i++) {

	            for(int j = 0; j < N; j++) {

	                if(matrix[i][j] >= boundary && matrix[i][j] <= boundary + offset)
	                    check[i][j] = true;
                }
            }
	        if(!check[0][0])
	            continue;

	        arrayDeque.add(new Location(0, 0));
	        check[0][0] = false;

	        while(!arrayDeque.isEmpty()) {

	            location = arrayDeque.remove();
	            x = location.x;
	            y = location.y;

	            if(x == N - 1 && y == N - 1)
	                return true;

	            for(int i = 0; i < 4; i++) {

	                nx = x + loX[i];
	                ny = y + loY[i];

	                if(nx < 0 || nx >= N || ny < 0 || ny >= N)
	                    continue;
	                if(!check[nx][ny])
	                    continue;

	                check[nx][ny] = false;
	                arrayDeque.add(new Location(nx, ny));
                }
            }
        }
	    return false;
    }
}