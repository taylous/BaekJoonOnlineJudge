import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] area;
    private static int[] population;

    private static int Answer;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n;

        N = Integer.parseInt(br.readLine());

        area = new boolean[N][N];
        population = new int[N];
        Answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            population[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            while (n-- > 0)
                area[i][Integer.parseInt(st.nextToken()) - 1] = true;
        }
        allocateArea(new boolean[N], 0, N, 0);
        System.out.println(Answer == Integer.MAX_VALUE ? -1 : Answer);
        br.close();
    }

    private static boolean checkConnection(boolean[] check, int count, boolean flag) {

        int start = 0;

        for (int i = 0; i < N; i++) {
            if (check[i] == flag) {

                start = i;
                break;
            }
        }
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int section = count - 1;
        int here, there;

        arrayDeque.add(start);
        visited[start] = true;

        while (!arrayDeque.isEmpty()) {

            here = arrayDeque.remove();

            for (there = 0; there < N; there++) {

                if (area[here][there] && check[there] == flag) {

                    if (visited[there])
                        continue;
                    section--;
                    arrayDeque.add(there);
                    visited[there] = true;
                }
            }
        }
        return section == 0;
    }

    private static int populationDifferences(boolean[] check) {

        int red = 0;
        int blue = 0;

        for (int i = 0; i < N; i++) {

            if (check[i])
                blue += population[i];
            else
                red += population[i];
        }
        return Math.abs(red - blue);
    }

    private static void allocateArea(boolean[] check, int idx, int red, int blue) {

        if (idx == N) {

            if (blue == 0 || red == 0)
                return;

            boolean redFlag = checkConnection(check, red, false);
            boolean blueFlag = checkConnection(check, blue, true);

            if (!redFlag || !blueFlag)
                return;

            Answer = Math.min(Answer, populationDifferences(check));
            return;
        }
        allocateArea(check, idx + 1, red, blue);
        check[idx] = true;
        allocateArea(check, idx + 1, red - 1, blue + 1);
        check[idx] = false;
    }
}