import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {

    int type;
    int x;
    int y;

    public CCTV(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static ArrayList<CCTV> cctvs = new ArrayList<>();

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int Answer;
    static int N;
    static int M;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] map;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Answer = Integer.MAX_VALUE;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0 && map[i][j] < 6)
                    cctvs.add(new CCTV(map[i][j], i, j));
            }
        }
        surveillance(map, 0);
        System.out.println(Answer);
        br.close();
    }

    static int getBlindSpot(int[][] map) {

        int ret = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++)
                ret += map[i][j] == 0 ? 1 : 0;
        }
        return ret;
    }

    static void surveillance(int[][] map, int idx) {

        if (idx >= cctvs.size()) {

            Answer = Math.min(Answer, getBlindSpot(map));
            return;
        }
        int[][] copyMap = new int[N][M];
        int nx, ny;

        int x = cctvs.get(idx).x;
        int y = cctvs.get(idx).y;

        switch (cctvs.get(idx).type) {

            case 1:

                for (int i = 0; i < 4; i++) {

                    for (int k = 0; k < N; k++)
                        System.arraycopy(map[k], 0, copyMap[k], 0, M);
                    nx = x;
                    ny = y;

                    while (true) {

                        nx += loX[i];
                        ny += loY[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }
                    surveillance(copyMap, idx + 1);
                }
                break;
            case 2:

                for (int k = 0; k < N; k++)
                    System.arraycopy(map[k], 0, copyMap[k], 0, M);

                for (int i = 1; i < 4; i += 2) {

                    nx = x;
                    ny = y;

                    while (true) {

                        nx += loX[i];
                        ny += loY[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }
                }
                surveillance(copyMap, idx + 1);

                for (int k = 0; k < N; k++)
                    System.arraycopy(map[k], 0, copyMap[k], 0, M);

                for (int i = 0; i < 4; i += 2) {

                    nx = x;
                    ny = y;
                    while (true) {

                        nx += loX[i];
                        ny += loY[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }
                }
                surveillance(copyMap, idx + 1);

                break;
            case 3:

                int j;

                for (int i = 0; i < 4; i++) {

                    for (int k = 0; k < N; k++)
                        System.arraycopy(map[k], 0, copyMap[k], 0, M);
                    nx = x;
                    ny = y;
                    j = (i + 1 == 4 ? 0 : i + 1);

                    while (true) {

                        nx += loX[i];
                        ny += loY[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }

                    nx = x;
                    ny = y;
                    while (true) {

                        nx += loX[j];
                        ny += loY[j];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }
                    surveillance(copyMap, idx + 1);
                }

                break;
            case 4:

                for (int omit = 0; omit < 4; omit++) {

                    for (int k = 0; k < N; k++)
                        System.arraycopy(map[k], 0, copyMap[k], 0, M);

                    for (int i = 0; i < 4; i++) {

                        if (omit == i)
                            continue;
                        nx = x;
                        ny = y;

                        while (true) {

                            nx += loX[i];
                            ny += loY[i];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                                break;
                            if (copyMap[nx][ny] == 6)
                                break;

                            copyMap[nx][ny] = -1;
                        }
                    }
                    surveillance(copyMap, idx + 1);
                }
                break;
            case 5:

                for (int k = 0; k < N; k++)
                    System.arraycopy(map[k], 0, copyMap[k], 0, M);

                for (int i = 0; i < 4; i++) {

                    nx = x;
                    ny = y;

                    while (true) {

                        nx += loX[i];
                        ny += loY[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            break;
                        if (copyMap[nx][ny] == 6)
                            break;

                        copyMap[nx][ny] = -1;
                    }
                }
                surveillance(copyMap, idx + 1);
                break;
        }
    }
}