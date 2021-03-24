package 백준1028다이아몬드광산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] diamondMine;
    private static int[][][] cache;

    private static final int[][] offset = {
            {-1, -1},
            {-1, 1},
            {1, 1},
            {1, -1}
    };
    private static int R, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        diamondMine = new boolean[R][C];
        cache = new int[R][C][4];

        for (int i = 0; i < R; i++) {

            char[] inputChars = br.readLine().toCharArray();

            for (int j = 0; j < C; j++)
                diamondMine[i][j] = inputChars[j] == '1';
        }
        countingDiamondOre();
        System.out.println(findTheLargestDiamondSize());
        br.close();
    }

    private static boolean check(int x, int y) {

        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private static void countingDiamondOre() {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (diamondMine[i][j]) {

                    for (int k = 0; k < 4; k++) {

                        int row = i, col = j;
                        cache[i][j][k] = 1;

                        while (true) {
                            row += offset[k][0];
                            col += offset[k][1];

                            if (!check(row, col) || !diamondMine[row][col])
                                break;
                            cache[i][j][k] += 1;
                        }
                    }
                }
            }
        }
    }

    private static int findTheLargestDiamondSize() {

        int largestSize = 0;
        int currentSize = R < C ? (R / 2) + 1 : (C / 2) + 1;

        while (currentSize > 0) {

            int count = 0;

            for (int i = 0; i <= R - currentSize; i++) {
                for (int j = 0; j <= C - currentSize; j++) {

                    if (diamondMine[i][j]) {

                        int row = i, col = j;
                        boolean flag = false;

                        for (int dir = 3; dir >= 0; dir--) {

                            if (cache[row][col][dir] < currentSize) {

                                flag = true;
                                break;
                            }
                            row += offset[dir][0] * (currentSize - 1);
                            col += offset[dir][1] * (currentSize - 1);
                        }

                        if (!flag)
                            count += 1;
                    }
                }
            }
            if (count > 0) {
                largestSize = currentSize;
                break;
            }
            currentSize -= 1;
        }
        return largestSize;
    }
}
