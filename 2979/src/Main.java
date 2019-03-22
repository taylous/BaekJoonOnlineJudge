import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] time = new int[101];
	static int[] parkingFee = new int[4];
	
	static int Answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start, end, last = 0;
		
		parkingFee[1] = Integer.parseInt(st.nextToken());
		parkingFee[2] = Integer.parseInt(st.nextToken());
		parkingFee[3] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 3; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			last = Math.max(last, end);
			
			for(int j = start; j < end; j++)
				time[j]++;
		}
		
		for(int i = 1; i < last; i++)
			Answer += time[i] * parkingFee[time[i]];
		System.out.println(Answer);
		br.close();
	}

}
