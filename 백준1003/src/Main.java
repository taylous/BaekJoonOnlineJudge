import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Fibonacci {
	
	int zero;
	int one;
	
	public Fibonacci(int zero, int one) {
		
		this.zero = zero;
		this.one = one;
	}
}

public class Main {

	static Fibonacci[] cache = new Fibonacci[41];
	
	static int answerZero;
	static int answerOne;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			if(N <= 4)
				System.out.println(cache[N].zero + " " + cache[N].one);
			else {

				for(int n = 5; n <= N; n++)
					cache[n] = new Fibonacci(cache[n - 1].zero + cache[n - 2].zero, cache[n - 1].one + cache[n - 2].one);
				System.out.println(cache[N].zero + " " + cache[N].one);
			}
		}
		br.close();
	}

	static void init() {
		
		cache[0] = new Fibonacci(1, 0);
		cache[1] = new Fibonacci(0, 1);
		cache[2] = new Fibonacci(1, 1);
		cache[3] = new Fibonacci(1, 2);
		cache[4] = new Fibonacci(2, 3);
	}
}
