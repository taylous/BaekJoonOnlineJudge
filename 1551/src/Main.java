import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] sequence;
	
	static int Answer;
	static int N;
	static int K;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		sequence = new int[N];
		
		st = new StringTokenizer(br.readLine(), ",");
		for(int i = 0; i < N; i++)
			sequence[i] = Integer.parseInt(st.nextToken());
		
		while(K-- > 0) {
			
			--N;
			for(int i = 0; i < N; i++)
				sequence[i] = sequence[i + 1] - sequence[i];
		}
		for(int i = 0; i < N - 1; i++) {
			sb.append(sequence[i]);
			sb.append(",");
		}
		if(N - 1 >= 0)
			sb.append(sequence[N - 1]);
		sb.append("\n");
		System.out.println(sb.toString());
		br.close();
	}
}