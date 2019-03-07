import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
	static int[] rocks;
	
	static int Answer;
	static int N;
	static long T;
	static long P;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum;
		
		N = Integer.parseInt(st.nextToken());
		T = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		
		rocks = new int[N];
		sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			rocks[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {

			sum += rocks[i];
			priorityQueue.offer(-rocks[i]);
			
			while(sum > T - (i * P)) {
				
				if(priorityQueue.isEmpty())
					break;
				sum += priorityQueue.poll();
			}
			Answer = Math.max(Answer, priorityQueue.size());
		}
		System.out.println(Answer);
		br.close();
	}

}
