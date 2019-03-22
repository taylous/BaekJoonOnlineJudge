import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {

	static int[] house;
	
	static int Answer;
	static int N;
	static int K;
	
	public static void main(String[] args) throws IOException {
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("s_input.txt"));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			house = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				house[i] = Integer.parseInt(st.nextToken());
			
			
			
			System.out.println("#"+(test_case+1)+" "+Answer);
		}
		br.close();
	}

}
