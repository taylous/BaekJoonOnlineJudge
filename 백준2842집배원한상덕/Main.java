package 백준2842집배원한상덕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static House[][] town;

    private static int[] loX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] loY = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> heights;
        ArrayList<Integer> sortedHeights;
        StringTokenizer st;

        char[] container;
        int startX = 0, startY = 0, target;

        N = Integer.parseInt(br.readLine());
        town = new House[N][N];

        target = 0;
        heights = new HashSet<>();

        for (int i = 0; i < N; i++) {
            container = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {

                town[i][j] = new House();

                if (container[j] == 'K') {
                    town[i][j].target = true;
                    target++;
                } else if (container[j] == 'P') {
                    startX = i;
                    startY = j;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                town[i][j].height = Integer.parseInt(st.nextToken());
                heights.add(town[i][j].height);
            }
        }
        sortedHeights = new ArrayList<>(heights);
        Collections.sort(sortedHeights);
        System.out.println(solve(sortedHeights, startX, startY, target));
        br.close();
    }

    private static int solve(ArrayList<Integer> sortedHeights, int startX, int startY, int target) {

        int left = 0, right = 0;
        int result = Integer.MAX_VALUE;

        while (right < sortedHeights.size() && left < sortedHeights.size()) {

            if (postmanHanSangDeok(startX, startY, sortedHeights.get(right), sortedHeights.get(left), target)) {

                result = Math.min(result, sortedHeights.get(right) - sortedHeights.get(left));
                left++;
            } else {
                right++;
            }
        }
        return result;
    }

    private static boolean postmanHanSangDeok(int startX, int startY, int max, int min, int target) {

        if (town[startX][startY].height < min || max < town[startX][startY].height)
            return false;

        ArrayDeque<Location> deque = new ArrayDeque<>();
        Location location;

        boolean[][] visited = new boolean[N][N];
        int x, y, nx, ny;
        int count = 0;

        deque.add(new Location(startX, startY));
        visited[startX][startY] = true;

        while (!deque.isEmpty()) {

            location = deque.remove();
            x = location.x;
            y = location.y;

            if (town[x][y].target)
                count++;
            if (count == target)
                return true;

            for (int i = 0; i < 8; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                    continue;
                if (town[nx][ny].height < min | max < town[nx][ny].height)
                    continue;

                visited[nx][ny] = true;
                deque.add(new Location(nx, ny));
            }
        }
        return false;
    }
}

class Location {

    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class House {

    boolean target;
    int height;

    public House() {

        this.target = false;
        this.height = 0;
    }
}