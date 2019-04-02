import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int Answer;
	
	static int N;
	static int kimJiMin;
	static int imHanSu;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		kimJiMin = Integer.parseInt(st.nextToken());
		imHanSu = Integer.parseInt(st.nextToken());
		
		while(kimJiMin != imHanSu) {
			
			kimJiMin = kimJiMin/2 + kimJiMin%2;
			imHanSu = imHanSu/2 + imHanSu%2;
			Answer++;
		}
		System.out.println(Answer);
		br.close();
	}
}
