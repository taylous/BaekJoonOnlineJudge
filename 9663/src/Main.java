import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static boolean[][] chess;
	static int Answer;
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		chess = new boolean[N][N];
		nQueen(0);
		
		System.out.println(Answer);
		br.close();
	}
	
	static boolean chk(int x, int y) {
		
		int nx = x - 1;
		int ny = y - 1;
		
		while(nx >= 0 && ny >= 0)	
			if(chess[nx--][ny--])
				return false;
		
		nx = x;
		while(nx >= 0)
			if(chess[nx--][y])
				return false;
		
		nx = x - 1;
		ny = y + 1;
		while(nx >= 0 && ny < N)
			if(chess[nx--][ny++])
				return false;
		return true;
	}

	static void nQueen(int x) {

		if(x == N) {
			
			Answer++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			
			if(chk(x, i)) {
				
				chess[x][i] = true;
				nQueen(x + 1);
				chess[x][i] = false;
			}
		}
	}
}