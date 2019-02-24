import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class DragonCurve {

    int x;
    int y;
    int d;
    int g;

    DragonCurve(int x, int y, int d, int g) {

        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}

public class Main {

    static DragonCurve[] dragonCurves;
    static boolean[][] map = new boolean[101][101];;

    static int[] loX = {0, -1, 0, 1};
    static int[] loY = {1, 0, -1, 0};

    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dragonCurves = new DragonCurve[N];
        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine(), " ");
            dragonCurves[i] = new DragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(DC());
        br.close();
    }

    static int getAdjacentInfo() {

        int ret = 0;

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 100; j++) {

                if (map[i][j] && map[i + loX[0]][j + loY[0]] && map[i + loX[3]][j + loY[3]] && map[i + 1][j + 1])
                    ret++;
            }
        }
        return ret;
    }

    static int DC() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        int x, y;

        for (int i = 0; i < N; i++) {

            linkedList.add(dragonCurves[i].d);
            int generation = dragonCurves[i].g;

            for (int j = 0; j < generation; j++) {

                int times = linkedList.size();
                for (int k = times - 1; k >= 0; k--) {

                    int dir = linkedList.get(k);
                    linkedList.add((dir == 3 ? 0 : dir + 1));
                }
            }

            x = dragonCurves[i].y;
            y = dragonCurves[i].x;
            map[x][y] = true;
            while (!linkedList.isEmpty()) {

                int dir = linkedList.remove();
                x += loX[dir];
                y += loY[dir];

                map[x][y] = true;
            }
        }
        return getAdjacentInfo();
    }
}