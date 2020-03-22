package BOJ4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    private static int[][] room;
    private static int[][] distance;
    private static int[][] cache;
    private static ArrayList<Location> locations = new ArrayList<>();

    private static int[] loX = { -1, 0, 1, 0 };
    private static int[] loY = { 0, 1, 0, -1 };

    private static int Answer;

    private static int w, h;
    private static int robotX, robotY;

    public static void main(String[] args) throws IOException {

        //BufferedReader br = new BufferedReader(new FileReader("src\\BOJ4991\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        char[] container;
        int dustNumber;

        while(true) {

            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            Answer = Integer.MAX_VALUE;
            room = new int[h][w];
            dustNumber = 1;

            for(int i = 0 ; i < h; i++) {

                container = br.readLine().toCharArray();

                for(int j = 0; j < w; j++) {

                    if(container[j] == 'o') {
                        locations.add(0, new Location(i, j));
                        room[i][j] = 0;
                    }
                    else if(container[j] == '*') {
                        locations.add(new Location(i, j));
                        room[i][j] = dustNumber++;
                    }
                    else if(container[j] == 'x') {
                        room[i][j] = -2;
                    }
                    else {
                        room[i][j] = -1;
                    }
                }
            }
            if(calculateDistance(dustNumber)) {
                cache = new int[dustNumber][(1 << dustNumber)];
                for(int i = 0; i < dustNumber; i++)
                    Arrays.fill(cache[i], Integer.MAX_VALUE);

                Answer = robotCleaner(0, (1 << 0), dustNumber);
            }
            sb.append(Answer == Integer.MAX_VALUE ? -1 : Answer);
            sb.append("\n");

            locations.clear();
        }
        System.out.println(sb.toString());
        br.close();
    }

    static boolean calculateDistance(int dustNumber) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];

        distance = new int[dustNumber][dustNumber];

        Location location;
        int x, y, nx, ny, size, move, count;

        for(int from = 0; from < dustNumber; from++) {

            location = locations.get(from);

            x = location.x;
            y = location.y;
            move = 1;
            count = dustNumber - 1;

            arrayDeque.add(new Location(x, y));
            visited[x][y] = true;

            while(!arrayDeque.isEmpty()) {

                size = arrayDeque.size();

                while(size-- > 0) {

                    location = arrayDeque.remove();
                    x = location.x;
                    y = location.y;

                    for(int i = 0; i < 4; i++) {

                        nx = x + loX[i];
                        ny = y + loY[i];

                        if(nx < 0 || nx >= h || ny < 0 || ny >= w || visited[nx][ny])
                            continue;
                        if(room[nx][ny] == -2)
                            continue;
                        if(room[nx][ny] >= 0) {
                            distance[from][room[nx][ny]] = move;
                            count--;
                        }
                        visited[nx][ny] = true;
                        arrayDeque.add(new Location(nx, ny));
                    }
                }
                move++;
                if(count == 0)
                    break;
            }
            if(count != 0)
                return false;

            for(int i = 0; i < h; i++)
                Arrays.fill(visited[i], false);
            arrayDeque.clear();
        }
        return true;
    }

    static int robotCleaner(int start, int visited, int size) {

        if(visited == (1 << size) - 1) {
            return 0;
        }

        if(cache[start][visited] != Integer.MAX_VALUE)
            return cache[start][visited];

        for(int to = 0; to < size; to++) {

            if(start == to)
                continue;

            if((visited & (1 << to)) == 0) {
                cache[start][visited] = Math.min(cache[start][visited], robotCleaner(to, (visited | (1 << to)), size) + distance[start][to]);
            }
        }
        return cache[start][visited];
    }

    private static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
