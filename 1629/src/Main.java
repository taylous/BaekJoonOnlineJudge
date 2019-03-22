import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static HashMap<Long, Long> hashMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		System.out.println(multiplication(A, B, C) % C);
		hashMap.clear();
		br.close();
	}

	static long multiplication(long A, long B, long C) {
		
		if(B == 1)
			return A;
		
		if(B % 2 == 0) {
			
			if(hashMap.containsKey(B))
				return hashMap.get(B);
			else {
				
				hashMap.put(B, ((multiplication(A, B / 2, C)) % C * (multiplication(A, B / 2, C)) % C) % C);
				return hashMap.get(B);
			}
		}
		else {
			
			if(hashMap.containsKey(B))
				return hashMap.get(B);
			else {
				
				hashMap.put(B, ((multiplication(A, (B - 1) / 2, C)) % C * (multiplication(A, (B - 1) / 2, C)) % C * A) % C);
				return hashMap.get(B);
			}
		}
	}
}
