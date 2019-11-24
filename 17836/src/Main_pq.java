import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Warrior implements Comparable<Warrior> {

    int x;
    int y;
    int time;
    boolean gram;

    Warrior(int x, int y, int time, boolean gram) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.gram = gram;
    }

    @Override
    public int compareTo(Warrior other) {
        return this.time < other.time ? -1 : 1;
    }
}

public class Main {

    private static int[][] castle;

    private static int[] loX = {-1, 0, 1, 0};
    private static int[] loY = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int T;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Answer;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        castle = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++)
                castle[i][j] = Integer.parseInt(st.nextToken());
        }
        Answer = findThePrincess();
        System.out.println(Answer == Integer.MAX_VALUE ? "Fail" : Answer);
        br.close();
    }

    private static int findThePrincess() {

        PriorityQueue<Warrior> priorityQueue = new PriorityQueue<>();
        int[][][] visited = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j][0] = Integer.MAX_VALUE;
                visited[i][j][1] = Integer.MAX_VALUE;
            }
        }
        priorityQueue.offer(new Warrior(0, 0, 0, false));
        visited[0][0][0] = 0;

        Warrior warrior;
        boolean gram;
        int x, y, nx, ny, time;
        int ret = Integer.MAX_VALUE;

        while (!priorityQueue.isEmpty()) {

            warrior = priorityQueue.poll();
            x = warrior.x;
            y = warrior.y;
            time = warrior.time;
            gram = warrior.gram;

            if (time > T)
                continue;
            if (x == N - 1 && y == M - 1) {

                ret = Math.min(ret, time);
                continue;
            }
            time++;

            for (int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][gram ? 1 : 0] <= time)
                    continue;
                if (castle[nx][ny] == 1 && !gram)
                    continue;

                visited[nx][ny][gram ? 1 : 0] = time;
                if (!gram)
                    priorityQueue.offer(new Warrior(nx, ny, time, castle[nx][ny] == 2));
                else
                    priorityQueue.offer(new Warrior(nx, ny, time, true));
            }
        }
        return ret;
    }
}