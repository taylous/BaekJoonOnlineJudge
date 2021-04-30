package 백준16929TwoDots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        boolean[] side;
        int visitCount;

        public Node() {
            this.side = new boolean[4];
            this.visitCount = 0;
        }
    }

    private static Node[][] visited;
    private static char[][] game;

    private static final int[][] loXY = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    private static boolean cycle;
    private static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] inputs;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        game = new char[N][M];
        visited = new Node[N][M];

        for (int i = 0; i < N; i++) {

            inputs = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                game[i][j] = inputs[j];
                visited[i][j] = new Node();
            }
        }

        for (int i = 0; i < N && !cycle; i++) {
            for (int j = 0; j < M && !cycle; j++) {

                for (int k = 0; k < 4; k++) {

                    int nx = i + loXY[k][0];
                    int ny = j + loXY[k][1];

                    if (check(nx, ny, i, game[i][j]) || visited[nx][ny].visitCount != 0)
                        continue;

                    visited[i][j].side[k] = true;
                    isCycle(i, j, nx, ny, game[i][j]);
                }
            }
        }
        System.out.println(cycle ? "Yes" : "No");
        br.close();
    }

    private static int getReverseDirection(int direction) {

        if (direction == 0)
            return 2;
        else if (direction == 1)
            return 3;
        else if (direction == 2)
            return 0;
        return 1;
    }

    private static boolean check(int x, int y, int dir, char color) {

        if (x < 0 || x >= N || y < 0 || y >= M)
            return true;
        return game[x][y] != color || visited[x][y].side[getReverseDirection(dir)];
    }

    private static void isCycle(int srcX, int srcY, int x, int y, char color) {

        if (srcX == x && srcY == y || visited[x][y].visitCount > 1) {
            cycle = true;
            return;
        }
        if (cycle)
            return;
        visited[x][y].visitCount += 1;

        for (int i = 0; i < 4; i++) {

            int nx = x + loXY[i][0];
            int ny = y + loXY[i][1];

            if (check(nx, ny, i, color))
                continue;

            visited[x][y].side[i] = true;
            isCycle(srcX, srcY, nx, ny, color);
        }
    }
}