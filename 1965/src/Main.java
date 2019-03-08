import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] box;
	static int[] cache;
	
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		box = new int[N + 1];
		cache = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
			box[i] = Integer.parseInt(st.nextToken());
		
		for(int select = 1; select <= N; select++) {
			
			cache[select] = 1;
			
			for(int compare = 1; compare < select; compare++) {
				
				if(box[compare] < box[select] && cache[select] < cache[compare] + 1)
					cache[select] = cache[compare] + 1;
			}
			Answer = Math.max(Answer, cache[select]);
		}
		System.out.println(Answer);
		br.close();
	}

}
