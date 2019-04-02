import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] cup = new int[4];
	
	static int Answer;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int changedCup1, changedCup2, handOn;
		
		M = Integer.parseInt(br.readLine());
		for(int i = 1; i <= 3; i++)
			cup[i] = i;
		
		while(M-- > 0) {
			
			st = new StringTokenizer(br.readLine());
			
			changedCup1 = Integer.parseInt(st.nextToken());
			changedCup2 = Integer.parseInt(st.nextToken());
			
			handOn = cup[changedCup1];
			cup[changedCup1] = cup[changedCup2];
			cup[changedCup2] = handOn;
		}
		for(int i = 1; i <= 3; i++)
			if(cup[i] == 1)
				Answer = i;
		System.out.println(Answer);
		br.close();
	}
}