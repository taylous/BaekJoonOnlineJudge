import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] sequence;
	
	static int N;
	static int C;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		sequence = new int[N];
		for(int i = 0; i < N; i++)
			sequence[i] = Integer.parseInt(br.readLine());
		Arrays.sort(sequence);
		
		System.out.println(installRouter());
		br.close();
	}

	static int installRouter() {
		
		int distance, standard, count;
		int answer = 0;
		
		int left = 1;
		int right = sequence[N - 1] - sequence[0];
		
		while(left <= right) {
			
			distance = (left + right) / 2;
			standard = sequence[0];
			count = 1;
			
			for(int i = 1; i < N; i++) {
				
				if(sequence[i] - standard >= distance) {
					
					count++;
					standard = sequence[i];
				}
			}
			
			if(count >= C) {
				
				answer = distance;
				left = distance + 1;
			}
			else
				right = distance - 1;
		}
		return answer;
	}
}
