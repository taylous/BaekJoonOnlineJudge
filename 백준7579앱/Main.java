package 백준7579앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] memoryUse, disabledCost;
    private static int[][] cache;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int cost = 0;

        memoryUse = new int[N];
        disabledCost = new int[N];
        cache = new int[10001][N];

        for (int i = 0; i <= 10000; i++)
            Arrays.fill(cache[i], 987654321);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            memoryUse[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            disabledCost[i] = Integer.parseInt(st.nextToken());

        while (true) {

            if (quitApps(cost, 0) >= m) {

                System.out.println(cost);
                break;
            }
            cost += 1;
        }
        br.close();
    }

    private static int quitApps(int cost, int idx) {

        if (idx == N)
            return 0;
        if (cache[cost][idx] != 987654321)
            return cache[cost][idx];

        int ret = quitApps(cost, idx + 1);
        if (cost - disabledCost[idx] >= 0)
            ret = Math.max(ret, quitApps(cost - disabledCost[idx], idx + 1) + memoryUse[idx]);
        return cache[cost][idx] = ret;
    }
}
