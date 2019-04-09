import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Location implements Comparable<Location> {

    int x;
    int y;
    int c;

    public Location(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public int compareTo(Location other) {
        return this.c < other.c ? -1 : 1;
    }
}

public class Main {

    static ArrayDeque<Location> waters = new ArrayDeque<>();

    static char[][] lake;
    static int[][] meltingPoint;

    static int[][] swan = new int[2][2];

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int R;
    static int C;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("3197\\sample_input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] container;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        swan[0][0] = swan[0][1] = swan[1][0] = swan[1][1] = -1;

        lake = new char[R][C];
        meltingPoint = new int[R][C];

        for (int i = 0; i < R; i++) {

            container = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {

                lake[i][j] = container[j];

                if (lake[i][j] == 'L') {

                    if (swan[0][0] == -1) {

                        swan[0][0] = i;
                        swan[0][1] = j;
                    } else {

                        swan[1][0] = i;
                        swan[1][1] = j;
                    }
                }

                if (lake[i][j] == '.' || lake[i][j] == 'L')
                    waters.add(new Location(i, j, 0));
            }
        }
        meltingIce();
        System.out.println(swansLake());
        br.close();
    }

    static void meltingIce() {

        boolean[][] visited = new boolean[R][C];

        Location location;
        int x, y, nx, ny, size, day = 0;

        while (!waters.isEmpty()) {

            size = waters.size();
            while (size-- > 0) {

                location = waters.remove();
                x = location.x;
                y = location.y;

                meltingPoint[x][y] = day;

                for (int i = 0; i < 4; i++) {

                    nx = x + loX[i];
                    ny = y + loY[i];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                        continue;
                    if (lake[nx][ny] != 'X' || visited[nx][ny])
                        continue;

                    visited[nx][ny] = true;
                    waters.add(new Location(nx, ny, 0));
                }
            }
            day++;
        }
    }

    static int swansLake() {

        PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
        boolean[][] visited = new boolean[R][C];

        Location location;
        int x, y, c, nx, ny;

        priorityQueue.offer(new Location(swan[0][0], swan[0][1], 0));
        visited[swan[0][0]][swan[0][1]] = true;
        ;

        while (!priorityQueue.isEmpty()) {

            location = priorityQueue.poll();
            x = location.x;
            y = location.y;
            c = location.c;

            if (x == swan[1][0] && y == swan[1][1])
                return c;

            for (int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                    continue;
                if (visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                priorityQueue.offer(new Location(nx, ny, Math.max(c, meltingPoint[nx][ny])));
            }
        }
        return -1;
    }
}