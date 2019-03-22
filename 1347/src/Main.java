import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static char[][] map = new char[101][101];
	
	static int[] loX = { -1, 0, 1, 0 };
	static int[] loY = { 0, 1, 0, -1 };
	
	static int startX;
	static int startY;
	
	static int endX;
	static int endY;
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x, y, direction = 2;
		
		N = Integer.parseInt(br.readLine());
		if(N == 0) {
			
			System.out.println('#');
		}
		else {
			
			x = y = startX = startY = endX = endY = 50;
			char[] container = br.readLine().toCharArray();
			
			for(int i = 0; i <= 100; i++)
				Arrays.fill(map[i], '#');
			map[50][50] = '.';
			
			for(int i = 0; i < N; i++) {
				
				switch(container[i]) {
				
				case 'R':
					
					direction = direction + 1 == 4 ? 0 : direction + 1;
					break;
					
				case 'L':
					
					direction = direction - 1 == -1 ? 3 : direction - 1;
					break;
					
				case 'F':
					
					if(direction == 0)
						x--;
					else if(direction == 1)
						y++;
					else if(direction == 2)
						x++;
					else
						y--;
					
					startX = Math.min(startX, x);
					startY = Math.min(startY, y);
					endX = Math.max(endX,  x);
					endY = Math.max(endY, y);
					
					map[x][y] = '.';
					
					break;
				}
			}
			for(int i = startX; i <= endX; i++) {
				
				for(int j = startY; j <= endY; j++)
					System.out.print(map[i][j]);
				System.out.println();
			}
		}
		br.close();
	}
}