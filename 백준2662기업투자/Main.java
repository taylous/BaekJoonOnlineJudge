package 백준2662기업투자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] enterprise;
    private static int[][] cache;
    private static int[][] path;

    private static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        enterprise = new int[n + 1][M];
        cache = new int[n + 1][M + 1];
        path = new int[n + 1][M + 1];

        for (int i = 1; i <= n; i++) {

            st = new StringTokenizer(br.readLine());
            st.nextToken();

            for (int j = 0; j < M; j++)
                enterprise[i][j] = Integer.parseInt(st.nextToken());
        }
        sb.append(invest(n, 0));
        sb.append("\n");

        for (int company = 0; company < M; company++) {
            sb.append(path[n][company]);
            sb.append(" ");
            n -= path[n][company];
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static int invest(int investment, int company) {

        if (company == M)
            return 0;

        if (cache[investment][company] != 0)
            return cache[investment][company];

        int ret = 0, result;
        for (int money = 0; money <= investment; money++) {
            result = invest(investment - money, company + 1) + enterprise[money][company];

            if (ret < result) {

                path[investment][company] = money;
                ret = result;
            }
        }
        return cache[investment][company] = ret;
    }
}