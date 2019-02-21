import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	
	static BigInteger Answer;
	static int N;
	static int M;

	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Answer = new BigInteger("1");

        if(N != M) {

            int remain = N - M;
            int[] number = new int[N + 1];
            int[] remains = new int[remain + 1];

            for(int i = M + 1; i <= N; i++)
                number[i] = i;
            for(int i = remain; i >= 1; i--)
                remains[i] = i;

            for(int i = M + 1; i <= N; i++) {
                for(int j = remain; j >= 1; j--) {

                    if(number[i] % remains[j] == 0 && remains[j] > 1) {

                        number[i] /= remains[j];
                        remains[j] = 1;
                        break;
                    }
                }
            }
            for(int i = M + 1; i <= N; i++)
                Answer = Answer.multiply(BigInteger.valueOf(number[i]));
            for(int i = 1; i <= remain; i++) {

                if (remains[i] > 1)
                    Answer = Answer.divide(BigInteger.valueOf(remains[i]));
            }
        }
        System.out.println(Answer);
		br.close();
	}
}