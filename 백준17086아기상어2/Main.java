package 백준17086아기상어2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int x, y, c;

        public Node(int x, int y) {
            this(x, y, 0);
        }

        public Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    private static final int[][] loXY = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}
    };
    private static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Node> sharks = new ArrayList<>();
        boolean[][] map, visited;
        int answer = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                if (st.nextToken().equals("1"))
                    map[i][j] = true;
                else
                    sharks.add(new Node(i, j));
            }
        }

        for (Node shark : sharks) {

            answer = Math.max(answer, calculateSafeDistance(map, visited, shark.x, shark.y));

            for (int j = 0; j < N; j++)
                Arrays.fill(visited[j], false);
        }
        System.out.println(answer);
        br.close();
    }

    private static int calculateSafeDistance(boolean[][] map, boolean[][] visited, int startX, int startY) {

        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(startX, startY));
        visited[startX][startY] = true;

        while (!deque.isEmpty()) {

            Node node = deque.remove();
            int x = node.x;
            int y = node.y;
            int c = node.c;

            if (map[x][y])
                return c;

            for (int i = 0; i < 8; i++) {

                int nx = x + loXY[i][0];
                int ny = y + loXY[i][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, c + 1));
            }
        }
        return 0;
    }
}
