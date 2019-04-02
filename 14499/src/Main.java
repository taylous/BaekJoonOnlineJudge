import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[] dice = new int[6];
	
	static int[] loX = { 0, 0, 0, -1, 1 };
	static int[] loY = { 0, 1, -1, 0, 0 };
	
	static int N;
	static int M;
	static int K;

	static int x;
	static int y;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int command, nx, ny;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		while(K-- > 0) {
			
			command = Integer.parseInt(st.nextToken());
			
			nx = x + loX[command];
			ny = y + loY[command];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			
			rollingDice(command);
			
			if(map[nx][ny] == 0)
				map[nx][ny] = dice[5];
			else
				dice[5] = map[nx][ny];
			
			System.out.println(dice[0]);
			x = nx;
			y = ny;
		}
		br.close();
	}

	static void rollingDice(int command) {
		
		switch(command) {
		
		case 1:
			dice[5] = dice[2];
			dice[2] = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			break;
			
		case 2:
			dice[5] = dice[4];
			dice[4] = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			break;
			
		case 3:
			dice[1] = dice[0];
			dice[5] = dice[1];
			dice[0] = dice[3];
			dice[3] = dice[5];
			break;
			
		case 4:
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[1];
			break;
		}		
	}
}
