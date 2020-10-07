package 백준18809Gaaaaaaaaaarden;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static ArrayList<Location> spots;
    private static int[][] map;

    private static int[] loX = {-1, 0, 1, 0};
    private static int[] loY = {0, 1, 0, -1};

    private static int N, M;
    private static int G, R;
    private static int Answer;

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("./src/백준18809Gaaaaaaaaaarden/sample_input.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            G = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            spots = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < M; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] == 2)
                        spots.add(new Location(i, j));
                }
            }
            combinations(new int[spots.size()], R, G, 0);
            System.out.println("#" + test_case + " " + Answer);
            Answer = 0;
        }
        br.close();
    }

    static int Gaaaaaaaaaarden(int[][] garden, ArrayDeque<Location> mediums) {

        int[][] visited = new int[N][M];
        int flowers = 0, time = 0;
        int color, x, y, nx, ny, size;

        for (Location medium : mediums) {

            garden[medium.x][medium.y] = medium.color;
            visited[medium.x][medium.y] = -1;
        }

        while (!mediums.isEmpty()) {

            size = mediums.size();

            while (size-- > 0) {

                Location medium = mediums.remove();
                x = medium.x;
                y = medium.y;
                color = medium.color;

                if (garden[x][y] == 7)
                    continue;

                for (int i = 0; i < 4; i++) {

                    nx = x + loX[i];
                    ny = y + loY[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || garden[nx][ny] == 7)
                        continue;
                    if (garden[nx][ny] == 0 || garden[nx][ny] == color)
                        continue;

                    // 다른 배양액이 있을 때
                    if (garden[nx][ny] != 1 && garden[nx][ny] != 2) {

                        // 해당 지점에서 만난 다면 꽃을 피움
                        if (visited[nx][ny] == time + 1) {

                            garden[nx][ny] = 7;
                            flowers += 1;
                        }
                        continue;
                    }
                    garden[nx][ny] = color;
                    visited[nx][ny] = time + 1;
                    mediums.add(new Location(color, nx, ny));
                }
            }
            time += 1;
        }
        return flowers;
    }

    static void combinations(int[] check, int G, int R, int idx) {

        if (G == 0 && R == 0) {
            ArrayDeque<Location> mediums = new ArrayDeque<>();

            for (int i = 0; i < check.length; i++) {
                if (check[i] > 0) {
                    Location spot = spots.get(i);
                    mediums.add(new Location(check[i], spot.x, spot.y));
                }
            }
            int[][] garden = new int[N][M];

            for (int i = 0; i < N; i++)
                System.arraycopy(map[i], 0, garden[i], 0, M);
            Answer = Math.max(Answer, Gaaaaaaaaaarden(garden, mediums));
            return;
        }
        if (idx == check.length)
            return;

        combinations(check, G, R, idx + 1);
        if (check[idx] == 0) {

            if (G > 0) {
                check[idx] = 3;
                combinations(check, G - 1, R, idx + 1);
                check[idx] = 0;
            }
            if (R > 0) {
                check[idx] = 4;
                combinations(check, G, R - 1, idx + 1);
                check[idx] = 0;
            }
        }
    }
}

class Location {

    int color;
    int x;
    int y;

    public Location(int x, int y) {
        this('\u0000', x, y);
    }

    public Location(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "color=" + (color == 3 ? "G" : "R") +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}