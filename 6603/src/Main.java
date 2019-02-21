import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuffer Answer = new StringBuffer();
    static int[] sequence;
	static int N;
	
	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if(N == 0)
                break;

            sequence = new int[N];
            for(int i = 0; i < N; i++)
                sequence[i] = Integer.parseInt(st.nextToken());
            Lotto(new int[6], 0, 0);
            Answer.append("\n");
        }
        System.out.println(Answer.toString());
		br.close();
	}

	static void Lotto(int[] pick, int idx, int times) {

	    if(times == 6) {

	        for(int i = 0; i < 6; i++) {
                Answer.append(pick[i]);
                Answer.append(" ");
            }
	        Answer.append("\n");
	        return;
        }

	    for(int i = idx; i < N; i++) {

            pick[times] = sequence[i];
	        Lotto(pick, i + 1, times + 1);
        }
    }
}