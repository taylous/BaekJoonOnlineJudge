package 백준18808스티커붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static ArrayList<Sticker> stickers;
    private static int[][] notebook;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Sticker sticker;
        int n, m, area;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];
        stickers = new ArrayList<>();

        while (k-- > 0) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            area = 0;

            sticker = new Sticker(n, m);

            for (int row = 0; row < n; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < m; col++) {
                    sticker.space[row][col] = Integer.parseInt(st.nextToken());
                    area += sticker.space[row][col];
                }
            }
            sticker.area = area;
            stickers.add(sticker);
        }
        System.out.println(stickingStickers());
        br.close();
    }

    static void rotate(Sticker sticker, int rotation) {

        if (rotation == 360)
            return;

        int[][] rotated = new int[sticker.m][sticker.n];
        int index;

        for (int col = 0; col < sticker.m; col++) {

            index = 0;

            for (int row = sticker.n - 1; row >= 0; row--)
                rotated[col][index++] = sticker.space[row][col];
        }
        int t = sticker.n;
        sticker.n = sticker.m;
        sticker.m = t;

        sticker.space = new int[sticker.n][sticker.m];
        for (int i = 0; i < sticker.n; i++)
            System.arraycopy(rotated[i], 0, sticker.space[i], 0, sticker.m);

    }

    static boolean attach(Sticker sticker, int x, int y) {

        for (int i = 0; i < sticker.n; i++) {
            for (int j = 0; j < sticker.m; j++) {
                if (sticker.space[i][j] == 1 && notebook[x + i][y + j] == 1)
                    return false;
            }
        }

        for (int i = 0; i < sticker.n; i++) {
            for (int j = 0; j < sticker.m; j++) {
                if (sticker.space[i][j] == 1)
                    notebook[x + i][y + j] = 1;
            }
        }
        return true;
    }

    static int stickingStickers() {

        boolean flag;
        int totalArea = 0;
        int endX, endY;

        for (Sticker sticker : stickers) {

            endX = N - sticker.n;
            endY = M - sticker.m;
            flag = false;

            for (int rotation = 90; rotation <= 360 && !flag; rotation += 90) {
                for (int i = 0; i <= endX && !flag; i++) {

                    for (int j = 0; j <= endY; j++) {
                        if (attach(sticker, i, j)) {
                            totalArea += sticker.area;
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    rotate(sticker, rotation);
                    endX = N - sticker.n;
                    endY = M - sticker.m;
                }
            }
        }
        return totalArea;
    }
}

class Sticker {

    int[][] space;
    int n;
    int m;
    int area;

    public Sticker(int n, int m) {

        space = new int[n][m];
        this.n = n;
        this.m = m;
    }
}