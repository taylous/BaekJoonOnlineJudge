import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][][] cache;
    static int N;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        cache = new int[N + 1][N + 1][4];

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(movingPipes(1, 2, 0));
        br.close();
    }

    static int movingPipes(int x, int y, int pre) {

        if (x == N && y == N)
            return 1;

        if (cache[x][y][pre] != 0)
            return cache[x][y][pre];

        if (pre == 0) {

            if (y + 1 <= N && map[x][y + 1] == 0)
                cache[x][y][pre] += movingPipes(x, y + 1, 0);

            if (y + 1 <= N && map[x][y + 1] == 0 && x + 1 <= N && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0)
                cache[x][y][pre] += movingPipes(x + 1, y + 1, 1);
        } else if (pre == 1) {

            if (y + 1 <= N && map[x][y + 1] == 0)
                cache[x][y][pre] += movingPipes(x, y + 1, 0);

            if (x + 1 <= N && map[x + 1][y] == 0)
                cache[x][y][pre] += movingPipes(x + 1, y, 2);

            if (y + 1 <= N && map[x][y + 1] == 0 && x + 1 <= N && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0)
                cache[x][y][pre] += movingPipes(x + 1, y + 1, 1);
        } else {

            if (x + 1 <= N && map[x + 1][y] == 0)
                cache[x][y][pre] += movingPipes(x + 1, y, 2);

            if (y + 1 <= N && map[x][y + 1] == 0 && x + 1 <= N && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0)
                cache[x][y][pre] += movingPipes(x + 1, y + 1, 1);
        }
        return cache[x][y][pre];
    }
}