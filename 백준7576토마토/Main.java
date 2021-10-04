package 백준7576토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static ArrayDeque<int[]> deque;
    static int[][] tomato;

    static int[][] loXY = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tomato = new int[N][M];
        deque = new ArrayDeque<>();

        int empty = 0;

        for (int i = 0; i < N; i += 1) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j += 1) {
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if (tomato[i][j] == 1)
                    deque.add(new int[]{i, j});
                else if (tomato[i][j] == -1)
                    empty += 1;
            }
        }
        System.out.println(ripeTomato(empty));
        br.close();
    }

    static int ripeTomato(int empty) {

        int day = 0, ripedTomato, size;
        int remains = (N * M) - deque.size() - empty;

        while (remains > 0) {

            size = deque.size();
            ripedTomato = 0;

            while (size-- > 0) {

                int[] lo = deque.remove();
                int x = lo[0];
                int y = lo[1];

                for (int i = 0; i < 4; i += 1) {

                    int nx = x + loXY[i][0];
                    int ny = y + loXY[i][1];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                        continue;
                    if (tomato[nx][ny] == 1 || tomato[nx][ny] == -1)
                        continue;

                    ripedTomato += 1;
                    tomato[nx][ny] = 1;

                    deque.add(new int[]{nx, ny});
                }
            }
            if (ripedTomato == 0)
                break;
            day += 1;
            remains -= ripedTomato;
        }
        return remains == 0 ? day : -1;
    }
}
