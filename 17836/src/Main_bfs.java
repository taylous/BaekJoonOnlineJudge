import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Warrior {

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

        ArrayDeque<Warrior> arrayDeque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        arrayDeque.add(new Warrior(0, 0, 0, false));
        visited[0][0][0] = true;

        Warrior warrior;
        boolean gram, save = false;
        int x, y, nx, ny, time, size;
        int ret = Integer.MAX_VALUE;

        for(int t = 0; t <= T; t++) {

            size = arrayDeque.size();

            while(size-- > 0) {
                warrior = arrayDeque.remove();
                x = warrior.x;
                y = warrior.y;
                time = warrior.time;
                gram = warrior.gram;

                if (x == N - 1 && y == M - 1) {

                    save = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {

                    nx = x + loX[i];
                    ny = y + loY[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][gram ? 1 : 0])
                        continue;
                    if (castle[nx][ny] == 1 && !gram)
                        continue;

                    visited[nx][ny][gram ? 1 : 0] = true;
                    if (!gram)
                        arrayDeque.add(new Warrior(nx, ny, time + 1, castle[nx][ny] == 2));
                    else
                        arrayDeque.add(new Warrior(nx, ny, time + 1, true));
                }
            }

            if(save)
                return t;
        }
        return ret;
    }
}