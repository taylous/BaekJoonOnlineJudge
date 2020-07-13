import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	static boolean[][] board;

	static int Answer;
	static int N;
	static int M;

	public static void main(String args[]) throws Exception	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = null;
        
		temp = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		board = new boolean[N + 1][M + 1];

		for(int i = 0; i < N; i++) {

			temp = br.readLine().split("");

			//B : true , W : false
			for(int j = 0; j < M; j++)
				board[i][j] = temp[j].equals("B") ? true : false;
		}
		ChessBoard();
		System.out.println(Answer);
		
		
		br.close();
	}

	static int Repaint(int x, int y) {

		int[] ret = new int[2];
		boolean criteria = true;
		int rowEnd = x + 8;
		int colEnd = y + 8;
		int sum = 0;
		
		for(int k = 0; k < 2; k++) {
			for (int i = x; i < rowEnd; i++) {

				boolean tmp = criteria;
				
				for (int j = y; j < colEnd; j++) {

					//Starting Color : Criteria
					if (board[i][j] != criteria)
						sum += 1;

					criteria = criteria == true ? false : true;
				}

				criteria = tmp;
				criteria = criteria == true ? false : true;
			}

			criteria = false;
			ret[k] = sum;
			sum = 0;
		}

		return Math.min(ret[0], ret[1]);
	}

	static void ChessBoard() {

		Answer = Integer.MAX_VALUE;

		for(int i = 0; i <= N - 8; i++)
			for(int j = 0; j <= M - 8; j++)
				Answer = Math.min(Answer, Repaint(i, j));
	}
}