import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuffer Answer = new StringBuffer();
	static int N;

	public static void main(String args[]) throws Exception	 {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] sequence = new int[N];
		for(int i = 0; i < N; i++)
		    sequence[i] = i + 1;
		allPermutations(sequence, 0);
        System.out.println(Answer.toString());
		br.close();
	}

	static void allPermutations(int[] sequence, int idx) {

	    if(idx == N) {

	        for(int var : sequence) {
                Answer.append(var);
                Answer.append(" ");
            }
	        Answer.append("\n");
	        return;
        }
	    int[] copySequence = Arrays.copyOf(sequence, N);

	    for(int i = idx; i < N; i++) {

	        int temp = copySequence[idx];
            copySequence[idx] = copySequence[i];
            copySequence[i] = temp;

	        allPermutations(copySequence, idx + 1);
        }
    }
}