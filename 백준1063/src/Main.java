import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] board = new int[8][8];
	
	static int[] loX = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] loY = { -1, 0, 1, 1, 1, 0, -1, -1 };
	
	static int Answer;
	static int N;
	
	static int kingX;
	static int kingY;
	
	static int stoneX;
	static int stoneY;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String container;
		int x, y, direction = 0;
		
		container = st.nextToken();
		
		kingY = container.charAt(0) - 'A';
		kingX = Math.abs((container.charAt(1) - '0') - 8);
		
		container = st.nextToken();
		
		stoneY = container.charAt(0) - 'A';
		stoneX = Math.abs((container.charAt(1) - '0') - 8);
		
		board[kingX][kingY] = 1;
		board[stoneX][stoneY] = 2;
		
		N = Integer.parseInt(st.nextToken());
		while(N-- > 0) {
		
			switch(br.readLine()) {
				
			case "R":
				direction = 3;
				break;
			case "L":
				direction = 7;
				break;				
			case "B":
				direction = 5;
				break;				
			case "T":
				direction = 1;
				break;				
			case "RT":
				direction = 2;
				break;				
			case "LT":
				direction = 0;
				break;				
			case "RB":
				direction = 4;
				break;				
			case "LB":
				direction = 6;
				break;
			}
			x = kingX + loX[direction];
			y = kingY + loY[direction];
			
			if(x < 0 || x >= 8 || y < 0 || y >= 8)
				continue;
			
			if(x == stoneX && y == stoneY) {
				
				int nx = stoneX + loX[direction];
				int ny = stoneY + loY[direction];
				
				if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8)
					continue;				
				
				board[stoneX][stoneY] = 1;
				board[nx][ny] = 2;

				kingX = stoneX;
				kingY = stoneY;
				
				stoneX = nx;
				stoneY = ny;
			}
			else {
				
				kingX = x;
				kingY = y;
			}
		}
		System.out.print((char)(kingY + 'A'));
		System.out.println(Math.abs(kingX - 8));

		System.out.print((char)(stoneY + 'A'));
		System.out.println(Math.abs(stoneX - 8));
		br.close();
	}

}
