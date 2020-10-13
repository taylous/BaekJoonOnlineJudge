package 백준2665미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static char[][] maze;

    private static int[] loX = {-1, 0, 1, 0};
    private static int[] loY = {0, 1, 0, -1};

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maze = new char[N][N];

        for (int i = 0; i < N; i++)
            System.arraycopy(br.readLine().toCharArray(), 0, maze[i], 0, N);
        System.out.println(makeMaze());
        br.close();
    }

    private static int makeMaze() {

        PriorityQueue<Location> pq = new PriorityQueue<>();
        int[][] visited = new int[N][N];
        int erase = 0;

        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        pq.offer(new Location(0, 0, 0));
        visited[0][0] = -1;

        while (!pq.isEmpty()) {

            Location location = pq.poll();
            int x = location.x;
            int y = location.y;
            int e = location.erase;

            if (x == N - 1 && y == N - 1) {

                erase = e;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + loX[i];
                int ny = y + loY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if (visited[nx][ny] < e + 1)
                    continue;

                visited[nx][ny] = e + (maze[nx][ny] == '0' ? 1 : 0);
                pq.offer(new Location(nx, ny, e + (maze[nx][ny] == '0' ? 1 : 0)));
            }
        }
        return erase;
    }
}

class Location implements Comparable<Location> {

    int x;
    int y;
    int erase;

    public Location(int x, int y, int erase) {
        this.x = x;
        this.y = y;
        this.erase = erase;
    }

    @Override
    public int compareTo(Location other) {
        return this.erase <= other.erase ? -1 : 1;
    }
}