import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int var;
		
		N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			
			var = Integer.parseInt(br.readLine());
			
			if(var == 0) {
				
				if(priorityQueue.isEmpty())
					System.out.println(0);
				else
					System.out.println(priorityQueue.poll());
			}
			else
				priorityQueue.offer(var);
		}
		br.close();
	}

}
