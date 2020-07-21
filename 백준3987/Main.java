package 백준3987보이저1호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] space;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        space = new char[N][M];

        for (int i = 0; i < N; i++)
            System.arraycopy(br.readLine().toCharArray(), 0, space[i], 0, M);

        st = new StringTokenizer(br.readLine());
        int[] answer = signal(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        if (answer[0] == 0)
            System.out.println("U");
        else if (answer[0] == 1)
            System.out.println("R");
        else if (answer[0] == 2)
            System.out.println("D");
        else
            System.out.println("L");
        System.out.println(answer[1] == -1 ? "Voyager" : answer[1]);
        br.close();
    }

    static int[] signal(int startX, int startY) {

        int x, y, d, count;
        int dir = 5, time = 0;

        for (int i = 0; i < 4; i++) {

            x = startX;
            y = startY;
            count = 1;
            d = i;

            while (true) {

                x += loX[d];
                y += loY[d];

                if (count > M * N)
                    return new int[]{i, -1};

                if (x < 0 || x >= N || y < 0 || y >= M || space[x][y] == 'C')
                    break;

                if (space[x][y] != '.')
                    d = changeDirection(space[x][y], d);
                count += 1;
            }
            if (time < count) {

                time = count;
                dir = i;
            } else if (time == count)
                dir = Math.min(dir, d);
        }
        return new int[]{dir, time};
    }

    static int changeDirection(char planet, int dir) {

        int ret = 0;

        if (planet == '/') {

            if (dir == 0)
                ret = 1;
            else if (dir == 2)
                ret = 3;
            else if (dir == 3)
                ret = 2;
        } else {

            if (dir == 0)
                ret = 3;
            else if (dir == 1)
                ret = 2;
            else if (dir == 2)
                ret = 1;
        }
        return ret;
    }
}
