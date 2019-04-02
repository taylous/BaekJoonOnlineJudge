import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static LinkedList<Integer> card = new LinkedList<>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++)
			card.add(i);
		
		while(card.size() != 1) {
			
			card.removeFirst();
			card.addLast(card.removeFirst());
		}
		sb.append(card.removeFirst());
		System.out.println(sb.toString());
		br.close();
	}
}