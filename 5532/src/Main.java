import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int Answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int l = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());
		
		int quotient = a / c;
		int remain = a % c;
		
		if(remain != 0)
			Answer = quotient + 1;
		else
			Answer = quotient;
		
		quotient = b / d;
		remain = b % d;
		
		if(remain != 0)
			Answer = Math.max(Answer, quotient + 1);
		else
			Answer = Math.max(Answer, quotient);
		System.out.println(l - Answer);	
		br.close();
	}
}