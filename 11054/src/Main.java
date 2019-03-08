import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] sequence = new int[1001];
	static int[][] cache = new int[1001][2];
	
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++)
			sequence[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			
			cache[i][0] = 1;
			
			for(int j = 1; j < i; j++) {
				
				if(sequence[i] > sequence[j] && cache[i][0] < cache[j][0] + 1)
					cache[i][0] = cache[j][0] + 1;
			}
		}
		
		for(int i = N; i >= 1; i--) {
			
			cache[i][1] = 1;
			
			for(int j = N; j > i; j--) {
				
				if(sequence[i] > sequence[j] && cache[i][1] < cache[j][1] + 1)
					cache[i][1] = cache[j][1] + 1;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			
			if(Answer < cache[i][0] + cache[i][1])
				Answer = cache[i][0] + cache[i][1];
		}
		System.out.println(Answer - 1);
		br.close();
	}

}
