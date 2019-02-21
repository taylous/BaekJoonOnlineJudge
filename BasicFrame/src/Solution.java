import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {

	static int Answer;
	
	public static void main(String[] args) throws IOException {
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			
			System.out.println("#"+(test_case+1)+" "+Answer);
		}
		br.close();
	}

}
