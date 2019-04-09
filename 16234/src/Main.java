import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Location {

    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[][] prePopultation;
    static int[][] visited;

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

    static int Answer;

    static int N;
    static int L;
    static int R;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] nation;

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nation = new int[N][N];
        prePopultation = new int[N][N];
        visited = new int[N][N];

        for(int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++)
                prePopultation[i][j] = nation[i][j] = Integer.parseInt(st.nextToken());
        }

        while(true) {

            for(int i = 0; i < N; i++) {

                for(int j = 0; j < N; j++) {

                    if(visited[i][j] == 0)
                        populationMovement(nation, i, j);
                }
            }
            if(!previousPopulationComparison(nation))
                break;

            for(int k = 0; k < N; k++)
                Arrays.fill(visited[k], 0);
            Answer++;
        }
        System.out.println(Answer);
        br.close();
    }

    static boolean previousPopulationComparison(int[][] nation) {

        boolean flag = false;

        for(int i = 0; i < N; i++) {

            for(int j = 0; j < N; j++) {

                if(nation[i][j] != prePopultation[i][j]) {

                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }

        if(flag) {
            for (int i = 0; i < N; i++)
                System.arraycopy(nation[i], 0, prePopultation[i], 0, N);
            return true;
        }
        return false;
    }

    static void populationMovement(int[][] nation, int startX, int startY) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        ArrayDeque<Location> union = new ArrayDeque<>();
        Location location;
        int x, y, nx, ny;
        int population = nation[startX][startY];
        int nationCount = 1;

        arrayDeque.add(new Location(startX, startY));
        union.add(new Location(startX, startY));
        visited[startX][startY] = 1;

        while(!arrayDeque.isEmpty()) {

            location = arrayDeque.remove();
            x = location.x;
            y = location.y;

            for(int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] == 1 || visited[nx][ny] == -1)
                    continue;
                if(Math.abs(nation[x][y] - nation[nx][ny]) >= L &&
                        Math.abs(nation[x][y] - nation[nx][ny]) <= R) {

                    visited[nx][ny] = 1;
                    nationCount++;
                    population += nation[nx][ny];
                    arrayDeque.add(new Location(nx, ny));
                    union.add(new Location(nx, ny));
                }
            }
        }

        while(!union.isEmpty()) {

            location = union.remove();

            nation[location.x][location.y] = population / nationCount;
            visited[location.x][location.y] = -1;
        }
    }
}