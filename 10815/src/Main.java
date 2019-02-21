import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static HashSet<Integer> set = new HashSet<>();

	static int Answer;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		while (N-- > 0)
			set.add(Integer.parseInt(st.nextToken()));

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (M-- > 0)
			sb.append((set.contains(Integer.parseInt(st.nextToken())) ? 1 : 0) + " ");
		System.out.println(sb.toString());
		br.close();
	}
}