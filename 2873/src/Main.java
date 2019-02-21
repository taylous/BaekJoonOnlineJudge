import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] enjoyment;

	static int Answer;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {

		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < 3; test_case++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			StringBuilder sb = new StringBuilder();
			int row = 0, col = 0;

			if (N % 2 == 1) {
				
				while(true) {
					
					for(col = 0; col < M - 1; col++)
						sb.append("R");
					sb.append("D");
					row++;
					
					if(row == N - 1)
						break;
					
					for(col = M - 1; col >= 0; col--)
						sb.append("L");
					row++;	
				}

			} else if (N % 2 == 0 && M % 2 == 1) {
				
				while(true) {
					
					for(row = 0; row < N - 1; row++)
						sb.append("D");
					sb.append("R");
					col++;
					
					if(col == M - 1)
						break;
					
					for(row = N - 1; row >= 0; row--)
						sb.append("U");
					sb.append("R");
					col++;	
				}

			} else {

//				enjoyment = new int[N][M];
//
//				for (int i = 0; i < N; i++) {
//
//					st = new StringTokenizer(br.readLine());
//
//					for (int j = 0; j < M; j++)
//						enjoyment[i][j] = Integer.parseInt(st.nextToken());
//				}
			}
			System.out.println(sb.toString());
		}
		br.close();
	}

}
