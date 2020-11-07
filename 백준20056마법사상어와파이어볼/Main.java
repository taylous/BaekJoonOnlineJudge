package 백준20056마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] loX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] loY = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./src/백준20056마법사상어와파이어볼/sample_input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayDeque<FireBall> fireBalls = new ArrayDeque<>();
        int n, m, k, r, c, w, s, d;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            fireBalls.add(new FireBall(r, c, w, s, d));
        }
        System.out.println(solve(fireBalls, n, k));
        br.close();
    }

    private static FireBall getFireBall(FireBall fireBall, int n) {

        int x = fireBall.x;
        int y = fireBall.y;
        int d = fireBall.direction;
        int s = fireBall.speed;

        int offset = s % n;

        while (offset-- > 0) {

            x += loX[d];
            y += loY[d];

            if (x < 0)
                x = n - 1;
            else if (x >= n)
                x = 0;

            if (y < 0)
                y = n - 1;
            else if (y >= n)
                y = 0;
        }
//        System.out.println(x + " " + y);
        return new FireBall(x, y, fireBall.weight, s, d);
    }

    private static void spreadFireBalls(ArrayDeque<FireBall> src, ArrayDeque<FireBall> dest) {

        int totalWeight = 0, totalSpeed = 0;
        int odd = 0, even = 0;
        int size = src.size();
        int start = 0;

        int x = 0, y = 0;

        for (FireBall fireBall : src) {

            totalWeight += fireBall.weight;
            totalSpeed += fireBall.speed;

            if (fireBall.direction % 2 == 0)
                even += 1;
            else
                odd += 1;

            x = fireBall.x;
            y = fireBall.y;
        }
        totalWeight /= 5;
        totalSpeed /= size;

        if (totalWeight == 0)
            return;

        if (even != size && odd != size)
            start = 1;

        for (; start < 8; start += 2)
            dest.add(new FireBall(x, y, totalWeight, totalSpeed, start));
    }

    public static int solve(ArrayDeque<FireBall> fireBalls, int n, int k) {

        Matrix[][] matrices = new Matrix[n][n];
        FireBall fireBall;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                matrices[i][j] = new Matrix();
        }

        while (k-- > 0) {

            while (!fireBalls.isEmpty()) {

                fireBall = getFireBall(fireBalls.remove(), n);
                matrices[fireBall.x][fireBall.y].deque.add(fireBall);
            }

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (matrices[i][j].deque.size() >= 2) {
                        spreadFireBalls(matrices[i][j].deque, fireBalls);
                    } else {
                        fireBalls.addAll(matrices[i][j].deque);
                    }
                    matrices[i][j].deque.clear();
                }
            }
        }
        for (FireBall t : fireBalls)
            ret += t.weight;
        return ret;
    }
}

class Matrix {

    ArrayDeque<FireBall> deque;

    public Matrix() {
        this.deque = new ArrayDeque<>();
    }
}

class FireBall {

    int x, y;
    int weight;
    int direction;
    int speed;

    public FireBall(int x, int y, int weight, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.direction = direction;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "FireBall{" +
                "x=" + x +
                ", y=" + y +
                ", weight=" + weight +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}