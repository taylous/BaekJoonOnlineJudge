package 백준1937욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[][] bambooForest;
    private static int[][] cache;

    private static final int[] loX = {-1, 0, 1, 0};
    private static final int[] loY = {0, 1, 0, -1};

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        bambooForest = new int[N][N];
        cache = new int[N][N];

        for (int i = 0; i < N; i++)
            Arrays.fill(cache[i], 1);

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                bambooForest[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(greedyPanda());
        br.close();
    }

    public static int solve(int x, int y) {

        if (cache[x][y] > 1)
            return cache[x][y];

        int preBamboo = bambooForest[x][y];
        for (int i = 0; i < 4; i++) {

            int nx = x + loX[i];
            int ny = y + loY[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;
            if (bambooForest[nx][ny] <= preBamboo)
                continue;

            cache[x][y] = Math.max(cache[x][y], solve(nx, ny) + 1);
        }
        return cache[x][y];
    }

    public static int greedyPanda() {

        int ret = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret = Math.max(ret, solve(i, j));
            }
        }
        return ret;
    }
}