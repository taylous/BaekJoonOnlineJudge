import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Location {

    int x;
    int y;

    Location(int x, int y) {

        this.x = x;
        this.y = y;
    }
}

class Main {

    static LinkedList<Location> virusLocation = new LinkedList<>();

    static int[][] laboratory;
    static int[][] copyMap;

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

    static int Answer;
    static int N;
    static int M;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        laboratory = new int[N][M];
        copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < M; j++) {
                laboratory[i][j] = Integer.parseInt(st.nextToken());

                if (laboratory[i][j] == 2)
                    virusLocation.add(new Location(i, j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (laboratory[i][j] == 0) {

                    laboratory[i][j] = 1;
                    createWall(i, j, 1);
                    laboratory[i][j] = 0;
                }
            }
        }
        System.out.println(Answer);

        virusLocation.clear();
        br.close();
    }

    static void createWall(int startX, int startY, int times) {

        if (times == 3) {

            Answer = Math.max(Answer, spreadVirus());
            return;
        }
        int ny = startY + 1;

        for (int nx = startX; nx < N; nx++) {
            for (; ny < M; ny++) {

                if (laboratory[nx][ny] == 0) {

                    laboratory[nx][ny] = 1;
                    createWall(nx, ny, times + 1);
                    laboratory[nx][ny] = 0;
                }
            }
            ny = 0;
        }
    }

    static int spreadVirus() {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int ret = 0;

        for (int i = 0; i < N; i++)
            copyMap[i] = Arrays.copyOf(laboratory[i], M);

        for (Location virus : virusLocation) {

            arrayDeque.add(new Location(virus.x, virus.y));
            visited[virus.x][virus.y] = true;
        }

        while (!arrayDeque.isEmpty()) {

            Location t = arrayDeque.remove();
            int x = t.x;
            int y = t.y;

            for (int i = 0; i < 4; i++) {

                try {

                    int nx = x + loX[i];
                    int ny = y + loY[i];

                    if (visited[nx][ny] || copyMap[nx][ny] != 0)
                        continue;

                    visited[nx][ny] = true;
                    copyMap[nx][ny] = 2;
                    arrayDeque.add(new Location(nx, ny));

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                ret += copyMap[i][j] == 0 ? 1 : 0;
        }
        return ret;
    }
}