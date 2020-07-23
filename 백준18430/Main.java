import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] wood;

    static int[][] loX = {
            {0, 1},
            {-1, 0},
            {-1, 0},
            {1, 0}
    };
    static int[][] loY = {
            {-1, 0},
            {0, -1},
            {0, 1},
            {0, 1}
    };
    static int Answer;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == 1 || M == 1) {
            System.out.println(0);
        } else {

            wood = new int[N][M];

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < M; j++)
                    wood[i][j] = Integer.parseInt(st.nextToken());
            }
            int max = (N * M) / 3;

            for (int make = 1; make <= max; make++)
                makeBoomerangs(new boolean[N][M], 0, -1, make, 0, 0);
            System.out.println(Answer);
        }
        br.close();
    }

    static void makeBoomerangs(boolean[][] check, int preX, int preY, int make, int select, int score) {

        if (make == select) {

            Answer = Math.max(Answer, score);
            return;
        }
        int j = preY + 1;

        for (int i = preX; i < N; i++) {
            for (; j < M; j++) {

                if (!check[i][j]) {

                    for (int k = 0; k < 4; k++) {

                        int ax = i + loX[k][0];
                        int ay = j + loY[k][0];
                        int bx = i + loX[k][1];
                        int by = j + loY[k][1];

                        if (ax < 0 || ay < 0 || bx < 0 || by < 0)
                            continue;
                        if (ax >= N || ay >= M || bx >= N || by >= M)
                            continue;

                        if (!check[ax][ay] && !check[bx][by]) {

                            check[i][j] = true;
                            check[ax][ay] = true;
                            check[bx][by] = true;

                            makeBoomerangs(check, i, j, make, select + 1, score + (wood[i][j] * 2) +
                                    wood[ax][ay] + wood[bx][by]);

                            check[i][j] = false;
                            check[ax][ay] = false;
                            check[bx][by] = false;
                        }
                    }
                }
            }
            j = 0;
        }
    }
}