import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<Integer> firstArr = new PriorityQueue<>();
	static PriorityQueue<Integer> secondArr = new PriorityQueue<>();

	static int Answer;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		while (N-- > 0)
			firstArr.offer(Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		while (M-- > 0)
			secondArr.offer(Integer.parseInt(st.nextToken()));

		while (!firstArr.isEmpty() && !secondArr.isEmpty()) {

			if (firstArr.peek() < secondArr.peek())
				sb.append(firstArr.poll());
			else
				sb.append(secondArr.poll());
			sb.append(" ");
		}
		while (!firstArr.isEmpty())
			sb.append(firstArr.poll() + " ");
		while (!secondArr.isEmpty())
			sb.append(secondArr.poll() + " ");
		
		System.out.println(sb.toString());
		br.close();
	}
}