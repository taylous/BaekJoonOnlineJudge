import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("14391//input.txt"));
        StringTokenizer st;

        char[] container;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

		    st = new StringTokenizer(br.readLine());

		    N = Integer.parseInt(st.nextToken());
		    M = Integer.parseInt(st.nextToken());

		    board = new int[N][M];

		    for(int i = 0; i < N; i++) {

		        container = br.readLine().toCharArray();

		        for(int j = 0; j < M; j++)
		            board[i][j] = container[j] - '0';
            }
		    pieceOfPaper(new boolean[N][M], N * M, 0);
			System.out.println("Case #"+(test_case+1)+" "+Answer);
		}
		
		br.close();
	}

	static void pieceOfPaper(boolean[][] visited, int remain, int total) {

	    if(remain == 0) {

	        Answer = Math.max(Answer, total);
	        return;
        }
	    int nx, ny;
	    int temp, offset;

	    for(int i = 0; i < N; i++) {

	        for(int j = 0; j < M; j++) {

	            if(!visited[i][j]) {

	                visited[i][j] = true;
	                pieceOfPaper(visited, remain - 1, total + board[i][j]);

	                for (int k = 0; k < 4; k++) {

	                    temp = board[i][j];
	                    offset = 10;
	                    nx = i;
	                    ny = j;

	                    while(nx < N && ny < M && !visited[nx][ny]) {

	                        nx += loX[k];
	                        ny += loY[k];

	                        visited[nx][ny] = true;
	                        temp += board[nx][ny] * offset;
	                        pieceOfPaper(visited, remain - k + 1, total + temp);
                        }
                    }
                }
            }
        }
    }
}