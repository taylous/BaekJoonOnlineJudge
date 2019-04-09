import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] gear = new int[4][8];
    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] container;
        int gearNumber, dir;

        for (int i = 0; i < 4; i++) {

            container = br.readLine().toCharArray();

            for (int j = 0; j < 8; j++)
                gear[i][j] = container[j] == '1' ? 1 : 0;
        }
        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine());

            gearNumber = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());

            checkGear(gearNumber, dir);
        }
        System.out.println((gear[0][0] + (gear[1][0] * 2) +
                (gear[2][0] * 4) + (gear[3][0] * 8)));
        br.close();
    }

    static void checkGear(int number, int dir) {

        if (number == 0) {

            if (gear[0][2] != gear[1][6]) {

                if (gear[1][2] != gear[2][6]) {

                    if (gear[2][2] != gear[3][6]) {

                        rotateGear(3, dir == 1 ? -1 : -dir);
                    }
                    rotateGear(2, dir);
                }
                rotateGear(1, dir == 1 ? -1 : -dir);
            }
            rotateGear(0, dir);
        } else if (number == 1) {

            if (gear[1][6] != gear[0][2])
                rotateGear(0, dir == 1 ? -1 : -dir);

            if (gear[1][2] != gear[2][6]) {

                if (gear[2][2] != gear[3][6]) {

                    rotateGear(3, dir);
                }
                rotateGear(2, dir == 1 ? -1 : -dir);
            }
            rotateGear(1, dir);
        } else if (number == 2) {

            if (gear[2][2] != gear[3][6])
                rotateGear(3, dir == 1 ? -1 : -dir);

            if (gear[2][6] != gear[1][2]) {

                if (gear[0][2] != gear[1][6]) {

                    rotateGear(0, dir);
                }
                rotateGear(1, dir == 1 ? -1 : -dir);
            }
            rotateGear(2, dir);
        } else {

            if (gear[3][6] != gear[2][2]) {

                if (gear[2][6] != gear[1][2]) {

                    if (gear[1][6] != gear[0][2]) {

                        rotateGear(0, dir == 1 ? -1 : -dir);
                    }
                    rotateGear(1, dir);
                }
                rotateGear(2, dir == 1 ? -1 : -dir);
            }
            rotateGear(3, dir);
        }
    }

    static void rotateGear(int gearNumber, int dir) {

        int temp;

        if (dir == 1) {

            temp = gear[gearNumber][7];

            for (int i = 6; i >= 0; i--)
                gear[gearNumber][i + 1] = gear[gearNumber][i];
            gear[gearNumber][0] = temp;
        } else {

            temp = gear[gearNumber][0];

            for (int i = 1; i <= 7; i++)
                gear[gearNumber][i - 1] = gear[gearNumber][i];
            gear[gearNumber][7] = temp;
        }
    }
}