import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static int[] sequence;
	static long Answer;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> data = new Stack<>();
		int ret;

		N = Integer.parseInt(br.readLine());

		sequence = new int[N];
		for (int i = 0; i < N; i++)
			sequence[i] = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {

			ret = 1;

			while (!stack.isEmpty()) {

				if (stack.peek() <= sequence[i]) {

					Answer += data.peek();

					if (stack.peek() == sequence[i])
						ret += data.peek();
					stack.pop();
					data.pop();
				} else
					break;
			}
			if (!stack.isEmpty())
				Answer++;

			stack.push(sequence[i]);
			data.push(ret);
		}
		System.out.println(Answer);
		stack.clear();
		br.close();
	}
}