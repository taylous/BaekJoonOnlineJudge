import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static long[][] cache;
    static long Answer;
    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((N = Integer.parseInt(br.readLine())) != 0) {

            cache = new long[N + 1][N + 1];
            for(int i = 0; i <= N; i++)
                java.util.Arrays.fill(cache[i],-1);

            Answer += Pill(N, 0);
            System.out.println(Answer);
            Answer = 0;
        }
        br.close();
    }

    static long Pill(int intactPill, int halfPill) {

        if (intactPill == 0 && halfPill == 0)
            return 1;

        if(cache[intactPill][halfPill] != -1)
            return cache[intactPill][halfPill];

        long ret = 0;
        if (halfPill != 0)
            ret += Pill(intactPill, halfPill - 1);
        if (intactPill != 0)
            ret += Pill(intactPill - 1, halfPill + 1);

        return cache[intactPill][halfPill] = ret;
    }
}