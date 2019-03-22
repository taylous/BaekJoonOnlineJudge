import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[][][] cache;
	
	static int N;
	static int L;
	static int R;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		cache = new long[N + 1][L + 1][R + 1];
		cache[1][1][1] = 1;
		
		for(int building = 2; building <= N; building++) {
			
			for(int left = 1; left <= L; left++) {
				
				for(int right = 1; right <= R; right++) {
					
					cache[building][left][right] = cache[building - 1][left - 1][right] + cache[building - 1][left][right - 1] + cache[building - 1][left][right] * (building - 2);
					cache[building][left][right] %= 1000000007;
				}
			}
		}
		System.out.println(cache[N][L][R]);
		br.close();
	}
}