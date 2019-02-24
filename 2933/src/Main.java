import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Location implements Comparable<Location> {

    int x;
    int y;

    Location(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Location other) {

        if (this.x < other.x)
            return 1;
        else if (this.x > other.x)
            return -1;
        else {

            if (this.y < other.y)
                return 1;
            else if (this.y > other.y)
                return -1;
        }
        return 0;
    }
}

class Main {

    static boolean[][] map;
    static int[] height;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int R;
    static int C;
    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];

        for (int i = 0; i < R; i++) {

            char[] container = br.readLine().toCharArray();

            for (int j = 0; j < C; j++)
                if (container[j] == 'x')
                    map[i][j] = true;
        }
        N = Integer.parseInt(br.readLine());
        height = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            height[i] = R - Integer.parseInt(st.nextToken());
        Mineral();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(map[i][j] ? 'x' : '.');
            System.out.println();
        }
        br.close();
    }

    static void relocation(int startX, int startY) {

        ArrayList<Location> arrayList = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];

        boolean chk = false;
        int[] maxRow = new int[C];
        int offset = Integer.MAX_VALUE;
        java.util.Arrays.fill(maxRow, -1);

        arrayList.add(new Location(startX, startY));
        visited[startX][startY] = true;
        maxRow[startY] = startX;

        int size = arrayList.size();
        for (int k = 0; k < size; k++) {

            Location t = arrayList.get(k);
            int x = t.x;
            int y = t.y;

            for (int i = 0; i < 4; i++) {

                int nx = x + loX[i];
                int ny = y + loY[i];

                if (nx >= R) {

                    chk = true;
                    break;
                }
                if (nx < 0 || ny < 0 || ny >= C)
                    continue;
                if (visited[nx][ny] || !map[nx][ny])
                    continue;

                maxRow[ny] = Math.max(maxRow[ny], nx);
                visited[nx][ny] = true;
                arrayList.add(new Location(nx, ny));
            }
            if (chk)
                return;
            size = arrayList.size();
        }

        for (int i = 0; i < C; i++) {

            if (maxRow[i] != -1) {

                int count = 0;

                for (int j = maxRow[i] + 1; j < R; j++) {
                    if (!map[j][i])
                        count++;
                    else
                        break;
                }
                offset = Math.min(offset, count);
            }
        }
        if (offset == Integer.MAX_VALUE)
            return;
        Collections.sort(arrayList);

        for (Location t : arrayList) {

            map[t.x][t.y] = false;
            map[t.x + offset][t.y] = true;
        }
        arrayList.clear();
    }

    static void Mineral() {

        boolean side = true;

        for (int i = 0; i < N; i++) {

            if (side) {

                for (int col = 0; col < C; col++) {

                    if (map[height[i]][col]) {

                        map[height[i]][col] = false;

                        for (int k = 0; k < 4; k++) {

                            int nx = height[i] + loX[k];
                            int ny = col + loY[k];

                            if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                                continue;
                            if (!map[nx][ny])
                                continue;

                            relocation(nx, ny);
                        }
                        break;
                    }
                }
                side = false;
            } else {

                for (int col = C - 1; col >= 0; col--) {

                    if (map[height[i]][col]) {

                        map[height[i]][col] = false;

                        for (int k = 0; k < 4; k++) {

                            int nx = height[i] + loX[k];
                            int ny = col + loY[k];

                            if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                                continue;
                            if (!map[nx][ny])
                                continue;

                            relocation(nx, ny);
                        }
                        break;
                    }
                }
                side = true;
            }
        }
    }
}