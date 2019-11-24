import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        IPriorityQueue priorityQueue = new IPriorityQueue();
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

class IPriorityQueue {

    private Warrior[] pq;
    private int N;

    IPriorityQueue() {

        this.pq = new Warrior[10000];
        this.N = 0;
    }

    boolean isEmpty() {

        return this.N == 0;
    }

    void offer(Warrior warrior) {

        this.pq[++N] = warrior;
        swim(N);
    }

    Warrior poll() {

        Warrior warrior = this.pq[1];
        exch(1, N--);
        this.pq[N + 1] = null;
        sink(1);
        return warrior;
    }

    private boolean less(int i, int j) {

        return pq[i].time > pq[j].time;
    }

    private void exch(int i, int j) {

        Warrior t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {

        while (k > 1 && less(k / 2, k)) {

            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= N) {

            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }
}