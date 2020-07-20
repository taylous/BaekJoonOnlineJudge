package 백준3067Coins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] coins;
    static int[] cache;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(br.readLine());

            coins = new int[N];
            cache = new int[M + 1];

            for (int i = 0; i < N; i++)
                coins[i] = Integer.parseInt(st.nextToken());

            cache[0] = 1;
            for (int coin : coins) {
                for (int money = 1; money <= M; money++) {
                    if (money - coin >= 0)
                        cache[money] += cache[money - coin];
                }
            }
            sb.append(cache[M]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
