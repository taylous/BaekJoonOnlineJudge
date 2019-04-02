import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int Answer;
	static int K;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int taken = 0;
		char status;
		
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			
			st = new StringTokenizer(br.readLine());
			
			taken += Integer.parseInt(st.nextToken());
			status = st.nextToken().charAt(0);
			
			if(taken >= 210) {
				
				Answer = K;
				break;
			}
			
			if(status == 'T')
				K = K + 1 == 9 ? 1 : K + 1;
			else
				continue;
		}
		System.out.println(Answer);
		br.close();
	}
}