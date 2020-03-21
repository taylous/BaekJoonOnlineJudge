package BOJ4991;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static char[][] room;
    private static ArrayList<Location> dusts = new ArrayList<>();

    private static int[] loX = { -1, 0, 1, 0 };
    private static int[] loY = { 0, 1, 0, -1 };

    private static int Answer;

    private static int w, h;
    private static int robotX, robotY;

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("src\\BOJ4991\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        char[] container;

        while(true) {

            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            Answer = Integer.MAX_VALUE;
            room = new char[h][w];

            for(int i = 0 ; i < h; i++) {

                container = br.readLine().toCharArray();

                for(int j = 0; j < w; j++) {

                    room[i][j] = container[j];

                    if(room[i][j] == 'o') {
                        robotX = i;
                        robotY = j;
                    }
                    else if(room[i][j] == '*') {
                        dusts.add(new Location(i, j));
                    }
                }
            }
            combinations(new int[dusts.size()], 1);
            sb.append(Answer == Integer.MAX_VALUE ? -1 : Answer);
            sb.append("\n");

            dusts.clear();
        }
        System.out.println(sb.toString());
        br.close();
    }

    static int robotCleaner(int[] comb) {

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];

        Location location;
        int x, y, nx, ny, move;

        int rx = robotX;
        int ry = robotY;

        boolean flag = false;
        int dustIndex = 0;
        int destX, destY;
        int ret = 0;

//        System.out.println("start");

        for(int index : comb) {

            arrayDeque.offer(new Location(rx, ry, 0));
            visited[rx][ry] = true;

            destX = dusts.get(index - 1).x;
            destY = dusts.get(index - 1).y;

//            System.out.println("\t먼지: " + destX + ", " + destY);

            while(!arrayDeque.isEmpty()) {

                location = arrayDeque.remove();
                x = location.x;
                y = location.y;
                move = location.move;
                flag = false;

                for(int i = 0; i < 4; i++) {

                    nx = x + loX[i];
                    ny = y + loY[i];

                    if(nx < 0 || nx >= h || ny < 0 || ny >= w || visited[nx][ny])
                        continue;
                    if(room[nx][ny] == 'x')
                        continue;
                    if(nx == destX && ny == destY) {

//                        System.out.println("\t\t 도착:" + (move + 1));
                        ret += (move + 1);
                        rx = nx;
                        ry = ny;

                        flag = true;
                        break;
                    }
//                    System.out.println("\t\tnx: " + nx + ", ny: " + ny);
                    visited[nx][ny] = true;
                    arrayDeque.add(new Location(nx, ny, move + 1));
                }
                if(flag)
                    break;
            }
            if(!flag)
                return Integer.MAX_VALUE;
            for(int i = 0; i < h; i++)
                Arrays.fill(visited[i], false);
            arrayDeque.clear();
        }
//        System.out.println("\t결과: " + ret);
        return ret;
    }

    static void combinations(int[] comb, int index) {

        if(index == dusts.size() + 1) {
            Answer = Math.min(Answer, robotCleaner(comb));
            return;
        }

        for(int i = 0; i < dusts.size(); i++) {
            if(comb[i] == 0) {
                comb[i] = index;
                combinations(comb, index + 1);
                comb[i] = 0;
            }
        }
    }
}

class Location {
    int x;
    int y;
    int move;

    Location(int x, int y) {
        this(x, y, 0);
    }

    Location(int x, int y, int move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }
}