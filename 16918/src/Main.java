import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Boomb {

    int x;
    int y;

    public Boomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static ArrayDeque<Boomb> boombs = new ArrayDeque<>();
    static int[][] map;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int R;
    static int C;
    static int N;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        char[] container;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {

            container = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {

                map[i][j] = container[j] == 'O' ? 3 : 0;

                if (map[i][j] > 0)
                    boombs.add(new Boomb(i, j));
            }
        }
        boomberMan();

        for (int i = 0; i < R; i++) {

            for (int j = 0; j < C; j++)
                sb.append(map[i][j] > 0 ? 'O' : '.');
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void bombInstallation() {

        for (int i = 0; i < R; i++) {

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {

                    map[i][j] = 3;
                    boombs.add(new Boomb(i, j));
                } else
                    map[i][j]--;
            }
        }
    }

    static void boomberMan() {

        Boomb boomb;
        int x, y, nx, ny, time, size;

        for (int remain = 1; remain <= N; remain++) {

            if (remain % 2 == 1) {

                size = boombs.size();

                while (size-- > 0) {

                    boomb = boombs.remove();
                    x = boomb.x;
                    y = boomb.y;

                    if (map[x][y] == 0)
                        continue;

                    if (map[x][y] - 1 == 0) {

                        map[x][y] = 0;

                        for (int i = 0; i < 4; i++) {

                            nx = x + loX[i];
                            ny = y + loY[i];

                            if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                                continue;
                            if (map[nx][ny] == 1)
                                continue;

                            map[nx][ny] = 0;
                        }
                    } else {

                        map[x][y]--;
                        boombs.add(new Boomb(x, y));
                    }
                }
            } else
                bombInstallation();
        }
    }
}