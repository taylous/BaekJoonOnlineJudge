import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		if(n == 1) {
			
			System.out.println(1);
		}
		else if( n == 2) {
			
		}
		
		
		if {
			
			if(m % 2 == 1)
				System.out.println(m - 1);
			else
				System.out.println(m / 2);
		}
		br.close();
	}

}
