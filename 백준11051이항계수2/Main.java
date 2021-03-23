package 백준11051이항계수2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] cache = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j || j == 0) {
                    cache[i][j] = 1;
                    continue;
                }
                cache[i][j] = (cache[i - 1][j] + cache[i - 1][j - 1]) % 10007;
            }
        }
        System.out.println(cache[n][k]);
        br.close();
    }
}
