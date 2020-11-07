package 백준1671상어의저녁식사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] adjacent;
    private static int[] visited;
    private static int[] leftSharks;
    private static int[] rightSharks;

    private static int visitCount;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Shark[] sharks;
        int size, speed, intellect;
        N = Integer.parseInt(br.readLine());

        sharks = new Shark[N];
        adjacent = new boolean[N][N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            size = Integer.parseInt(st.nextToken());
            speed = Integer.parseInt(st.nextToken());
            intellect = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(size, speed, intellect);
        }

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (i == j)
                    continue;
                if (sharks[i].size == sharks[j].size && sharks[i].speed == sharks[j].speed && sharks[i].intellect == sharks[j].intellect && i > j)
                    continue;
                if (sharks[i].size >= sharks[j].size && sharks[i].speed >= sharks[j].speed && sharks[i].intellect >= sharks[j].intellect)
                    adjacent[i][j] = true;
            }
        }
        System.out.println(N - bipartiteMatching());
        br.close();
    }

    public static boolean search(int current) {

        if (visited[current] == visitCount)
            return false;

        visited[current] = visitCount;

        for (int next = 0; next < N; next++) {

            if (adjacent[current][next]) {

                if (rightSharks[next] == -1 || search(rightSharks[next])) {

                    leftSharks[current] = next;
                    rightSharks[next] = current;
                    return true;
                }
            }
        }
        return false;
    }

    public static int bipartiteMatching() {

        int ret = 0;
        visitCount = 1;

        visited = new int[N];
        leftSharks = new int[N];
        rightSharks = new int[N];

        Arrays.fill(leftSharks, -1);
        Arrays.fill(rightSharks, -1);

        for (int i = 0; i < N; i++) {

            visitCount += 1;
            ret += search(i) ? 1 : 0;
            visitCount += 1;
            ret += search(i) ? 1 : 0;
        }
        return ret;
    }
}

class Shark {

    int size;
    int speed;
    int intellect;

    public Shark(int size, int speed, int intellect) {
        this.size = size;
        this.speed = speed;
        this.intellect = intellect;
    }
}
