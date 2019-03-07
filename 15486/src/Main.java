import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] time;
	static int[] money;
	static int[] cache;
	
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N + 1];
		money = new int[N + 1];
		cache = new int[N + 1];
		
		for(int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			time[i] = Integer.parseInt(st.nextToken());
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i <= N; i++) {
			
			Answer = Math.max(Answer, cache[i]);
			
			if(i + time[i] > N)
				continue;
			
			if(i + time[i] <= N)
				cache[i + time[i]] = Math.max(Answer + money[i], cache[i + time[i]]);
		}
		System.out.println(Answer);
		br.close();
	}
}
