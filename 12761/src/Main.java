import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class DongGyu {
	
	int x;
	int c;
	
	public DongGyu(int x, int c) {
		super();
		this.x = x;
		this.c = c;
	}
}

public class Main {
	
	static int[] bridge = new int[100001];
	static int[] jump;
	
	static int Answer;
	static int A;
	static int B;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		jump = new int[] { -1, 1, A, B, -A, -B };
		System.out.println(StoneBridge());
		br.close();
	}

	static int StoneBridge() {
		
		ArrayDeque<DongGyu> arrayDeque = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		
		DongGyu dongGyu;
		int x, nx, c;
		
		arrayDeque.add(new DongGyu(N, 0));
		visited[N] = true;
		
		while(!arrayDeque.isEmpty()) {
		
			dongGyu = arrayDeque.remove();
			x = dongGyu.x;
			c = dongGyu.c;
			
			if(x == M)
				return c;
			
			for(int i = 0; i < 6; i++) {
				
				nx = x + jump[i];
				
				if(nx < 0 || nx > 100000 || visited[nx])
					continue;
				
				visited[nx] = true;
				arrayDeque.add(new DongGyu(nx, c + 1));
			}
			
			if(x * A <= 100000 && !visited[x * A]) {
				
				visited[x * A] = true;
				arrayDeque.add(new DongGyu(x * A, c + 1));
			}
			
			if(x * B <= 100000 && !visited[x * B]) {
				
				visited[x * B] = true;
				arrayDeque.add(new DongGyu(x * B, c + 1));
			}
		}
		return 0;
	}
}
