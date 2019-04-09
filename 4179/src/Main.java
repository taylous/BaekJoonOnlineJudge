import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Maze {

    char type;
    int setFire;

    Maze(char type) {
        super();
        this.type = type;
    }

    Maze(char type, int setFire) {
        super();
        this.type = type;
        this.setFire = setFire;
    }
}

class Location {

    int x;
    int y;
    int t;

    public Location(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}

public class Main {

    static ArrayDeque<Location> fireLocation = new ArrayDeque<>();
    static Maze[][] maze;
    static boolean[][] visited;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int Answer;
    static int R;
    static int C;

    static int jihunX;
    static int jihunY;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] container;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new Maze[R + 2][C + 2];
        visited = new boolean[R + 2][C + 2];
        init();

        for (int i = 1; i <= R; i++) {

            container = br.readLine().toCharArray();

            for (int j = 1; j <= C; j++) {

                switch (container[j - 1]) {

                    case 'J':

                        jihunX = i;
                        jihunY = j;
                        maze[i][j] = new Maze('.');
                        break;

                    case 'F':

                        fireLocation.add(new Location(i, j, 0));
                        maze[i][j] = new Maze('F', 0);
                        visited[i][j] = true;
                        break;

                    default:

                        maze[i][j] = new Maze(container[j - 1], 987654321);
                        break;
                }
            }
        }
        spreadingFire();
        Answer = fire();
        System.out.println(Answer == 0 ? "IMPOSSIBLE" : Answer);
        br.close();
    }

    static void init() {

        for (int i = 0; i <= R + 1; i++) {

            maze[i][0] = new Maze('E', 987654321);
            maze[i][C + 1] = new Maze('E', 987654321);
        }
        for (int i = 0; i <= C; i++) {

            maze[0][i] = new Maze('E', 987654321);
            maze[R + 1][i] = new Maze('E', 987654321);
        }
    }

    static void spreadingFire() {

        Location location;
        int x, y, t, nx, ny;

        while (!fireLocation.isEmpty()) {

            location = fireLocation.remove();
            x = location.x;
            y = location.y;
            t = location.t;

            for (int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if (nx <= 0 || nx >= R + 1 || ny <= 0 || ny >= C + 1)
                    continue;
                if (maze[nx][ny].type != '.' || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                maze[nx][ny].setFire = t + 1;
                fireLocation.add(new Location(nx, ny, t + 1));
            }
        }
    }

    static int fire() {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        Location location;
        int x, y, t, nx, ny;
        int ret = 0;

        for (int i = 0; i <= R + 1; i++)
            Arrays.fill(visited[i], false);

        arrayDeque.add(new Location(jihunX, jihunY, 0));
        visited[jihunX][jihunY] = true;

        while (!arrayDeque.isEmpty()) {

            location = arrayDeque.remove();
            x = location.x;
            y = location.y;
            t = location.t;

            if (maze[x][y].type == 'E')
                return t;
            t++;

            for (int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if (nx < 0 || nx > R + 1 || ny < 0 || ny > C + 1)
                    continue;
                if (maze[nx][ny].type == '#' || maze[nx][ny].setFire <= t || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                arrayDeque.add(new Location(nx, ny, t));
            }
        }
        return ret;
    }
}