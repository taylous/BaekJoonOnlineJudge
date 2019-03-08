import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long[] cache = new long[101];
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cache[1] = 1;
		cache[2] = 1;
		cache[3] = 1;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			for(int i = 4; i <= N; i++)
				cache[i] = cache[i - 3] + cache[i - 2];
			System.out.println(cache[N]);
		}
		br.close();
	}
}