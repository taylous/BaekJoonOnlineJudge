import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Solution5607 {
 
    static long[] factorial;
    static int modular = 1234567891;
 
    static long Answer;
    static int N;
    static int R;
 
    public static void main(String args[]) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
 
            st = new StringTokenizer(br.readLine());
 
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
 
            factorial = new long[N + 1];
            factorial[0] = 1;
            factorial[1] = 1;
            for (int i = 2; i <= N; i++)
                factorial[i] = (i * factorial[i - 1]) % modular;
 
            long denominator = (factorial[R] * factorial[N - R]) % modular;
            long index = modular - 2;
            long fermatNum = 1;
             
            while (index > 0) {
                 
                if (index % 2 == 1) {
                    fermatNum *= denominator;
                    fermatNum %= modular;
                }
                denominator = (denominator * denominator) % modular;
                index /= 2;
            }
            Answer = ((factorial[N] % modular) * (fermatNum % modular)) % modular;
            System.out.println("#" + test_case + " " + Answer);
        }
        br.close();
    }
}