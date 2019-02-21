import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] matrix;
	static int[][] origin;
	
	static int Answer;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		char[] container;
		boolean flag = false;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][M];
		origin = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			
			container = br.readLine().toCharArray();
			
			for(int j = 0; j < M; j++)
				matrix[i][j] = container[j] - '0';
		}
		
		for(int i = 0; i < N; i++) {
			
			container = br.readLine().toCharArray();
			
			for(int j = 0; j < M; j++)
				origin[i][j] = container[j] - '0';
		}
		
		for(int i = 0; i <= N - 3; i++) {
			
			for(int j = 0; j <= M - 3; j++) {
				
				if(matrix[i][j] != origin[i][j]) {
					reverseMatrix(i, j);
					Answer++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			
			for(int j = 0; j < M; j++) {
				if(origin[i][j] != matrix[i][j]) {
					
					Answer = -1;
					flag = true;
				}
			}
			if(flag)
				break;
		}
		System.out.println(Answer);
		br.close();
	}

	static void reverseMatrix(int x, int y) {
		
		for(int i = x; i < x + 3; i++) {
			for(int j = y; j < y + 3; j++)
				matrix[i][j] = matrix[i][j] == 1 ? 0 : 1; 
		}
	}
}
