import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int in, out, passengers = 0, answer = 0;
		
		for(int i = 0; i < 4; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			out = Integer.parseInt(st.nextToken());
			in = Integer.parseInt(st.nextToken());
			
			passengers += in;
			passengers -= out;
			answer = Math.max(answer, passengers);
		}
		System.out.println(answer);
		br.close();
	}
}