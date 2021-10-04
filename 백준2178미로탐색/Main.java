package 백준2178미로탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<int[]> deque = new ArrayDeque<>();

        int[][] loXY = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;

        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i += 1)
            System.arraycopy(br.readLine().toCharArray(), 0, map[i], 0, m);

        deque.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!deque.isEmpty()) {

            int[] lo = deque.remove();

            if (lo[0] == n - 1 && lo[1] == m - 1) {
                answer = lo[2];
                break;
            }

            for (int i = 0; i < 4; i += 1) {

                int nx = lo[0] + loXY[i][0];
                int ny = lo[1] + loXY[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (map[nx][ny] == '0' || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                deque.add(new int[]{nx, ny, lo[2] + 1});
            }
        }
        System.out.println(answer);
        br.close();
    }
}
