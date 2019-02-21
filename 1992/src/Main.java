import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[][] video;
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] container;
		
		N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			
			container = br.readLine().toCharArray();
			
			for(int j = 0; j < N; j++)
				video[i][j] = container[j] - '0';
		}
		quadTree(0, 0, N);
		System.out.println(sb.toString());
		br.close();
	}

	static void quadTree(int start, int end, int size) {
		
		int pixel = video[start][end];
		boolean flag = false;
		
		for(int i = start; i < start+ size; i++) {
			
			for(int j = end; j < end + size; j++) {
				
				if(pixel != video[i][j]) {
					
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		
		if(flag) {
			
			sb.append("(");
			quadTree(start, end, size / 2);
			quadTree(start, end + (size / 2), size / 2);
			quadTree(start + (size / 2), end, size / 2);
			quadTree(start + (size / 2), end + (size / 2), size / 2);
			sb.append(")");
		}
		else
			sb.append(pixel);
	}
}
