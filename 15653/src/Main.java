import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Beads implements Comparable<Beads> {

    int rx, ry, bx, by;
    int times;

    Beads(int rx, int ry, int bx, int by, int times) {

        this.rx = rx;
        this.bx = bx;
        this.ry = ry;
        this.by = by;
        this.times = times;
    }

    @Override
    public int compareTo(Beads other) {
        return this.times < other.times ? -1 : 1;
    }
}

public class Main {

    static char[][] map;
    static boolean[][][][] visited;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int N;
    static int M;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int rx = 0, ry = 0, bx = 0, by = 0;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[11][11][11][11];

        for (int i = 0; i < N; i++) {

            char[] container = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {

                map[i][j] = container[j];

                if (map[i][j] == 'R') {

                    rx = i;
                    ry = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {

                    bx = i;
                    by = j;
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(EscapeBeads4(new Beads(rx, ry, bx, by, 1)));

        br.close();
    }

    static boolean FirstMovement(Beads beads, int dir) {

        if (dir == 0 || dir == 2) {

            if (beads.ry != beads.by)
                return true;
            else if (dir == 0 && beads.rx < beads.bx)
                return true;
            else if (dir == 2 && beads.rx > beads.bx)
                return true;
        } else {

            if (beads.rx != beads.bx)
                return true;
            else if (dir == 1 && beads.ry > beads.by)
                return true;
            else if (dir == 3 && beads.ry < beads.by)
                return true;
        }
        return false;
    }

    static int EscapeBeads4(Beads _beads) {

        PriorityQueue<Beads> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(_beads);
        visited[_beads.rx][_beads.ry][_beads.bx][_beads.by] = true;

        while (!priorityQueue.isEmpty()) {

            Beads beads = priorityQueue.poll();
            int rx = beads.rx;
            int ry = beads.ry;
            int bx = beads.bx;
            int by = beads.by;
            int times = beads.times;

            for (int i = 0; i < 4; i++) {

                boolean red = false, blue = false;
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                if (FirstMovement(beads, i)) {

                    while (true) {

                        int nx = nrx + loX[i];
                        int ny = nry + loY[i];

                        if (map[nx][ny] == 'O') {

                            nrx = nx;
                            nry = ny;
                            red = true;
                            break;
                        }
                        if (map[nx][ny] == '#' || (nx == nbx && ny == nby))
                            break;
                        nrx = nx;
                        nry = ny;
                    }

                    while (true) {

                        int nx = nbx + loX[i];
                        int ny = nby + loY[i];

                        if (map[nx][ny] == 'O') {

                            nbx = nx;
                            nby = ny;
                            blue = true;
                            break;
                        }
                        if (map[nx][ny] == '#' || (nx == nrx && ny == nry))
                            break;
                        nbx = nx;
                        nby = ny;
                    }
                } else {

                    while (true) {

                        int nx = nbx + loX[i];
                        int ny = nby + loY[i];

                        if (map[nx][ny] == 'O') {

                            nbx = nx;
                            nby = ny;
                            blue = true;
                            break;
                        }
                        if (map[nx][ny] == '#' || (nx == nrx && ny == nry))
                            break;
                        nbx = nx;
                        nby = ny;
                    }

                    while (true) {

                        int nx = nrx + loX[i];
                        int ny = nry + loY[i];

                        if (map[nx][ny] == 'O') {

                            nrx = nx;
                            nry = ny;
                            red = true;
                            break;
                        }
                        if (map[nx][ny] == '#' || (nx == nbx && ny == nby))
                            break;
                        nrx = nx;
                        nry = ny;
                    }
                }
                if (blue)
                    continue;
                if (red)
                    return times;

                if (visited[nrx][nry][nbx][nby])
                    continue;
                visited[nrx][nry][nbx][nby] = true;
                priorityQueue.offer(new Beads(nrx, nry, nbx, nby, times + 1));
            }
        }
        return -1;
    }
}