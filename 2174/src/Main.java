import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Robot {

    int number;
    int direction;
    int x;
    int y;

    public Robot(int number, int direction, int x, int y) {
        this.number = number;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static HashMap<Integer, Robot> hashMap = new HashMap<>();

    static int[][] map;

    static int[] loX = {-1, 0, 1, 0};
    static int[] loY = {0, 1, 0, -1};

    static int A;
    static int B;
    static int N;
    static int M;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x, y, direction = 0;
        int errorCode, number, times;
        boolean flag = false;
        char command;


        B = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        map = new int[A][B];

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());

            y = Integer.parseInt(st.nextToken()) - 1;
            x = A - Integer.parseInt(st.nextToken());

            switch (st.nextToken()) {

                case "N":
                    direction = 0;
                    break;
                case "E":
                    direction = 1;
                    break;
                case "S":
                    direction = 2;
                    break;
                case "W":
                    direction = 3;
                    break;
            }
            map[x][y] = i;
            hashMap.put(i, new Robot(i, direction, x, y));
        }

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());

            number = Integer.parseInt(st.nextToken());
            command = st.nextToken().charAt(0);
            times = Integer.parseInt(st.nextToken());

            errorCode = robotSimulation(number, command, times);

            if (errorCode > 0) {

                System.out.println("Robot " + number + " crashes into robot " + errorCode);
                flag = true;
                break;
            } else if (errorCode < 0) {

                System.out.println("Robot " + number + " crashes into the wall");
                flag = true;
                break;
            }
        }
        if (!flag)
            System.out.println("OK");

        br.close();
    }

    static int robotSimulation(int number, char command, int times) {

        Robot robot = hashMap.get(number);
        int x = robot.x;
        int y = robot.y;
        int direction = robot.direction;

        while (times-- > 0) {

            if (command == 'L') {

                direction = direction - 1 < 0 ? 3 : direction - 1;
            } else if (command == 'R') {

                direction = direction + 1 > 3 ? 0 : direction + 1;
            } else {

                int nx = x + loX[direction];
                int ny = y + loY[direction];

                if (nx < 0 || nx >= A || ny < 0 || ny >= B)
                    return -1;
                if (map[nx][ny] != 0)
                    return map[nx][ny];

                map[x][y] = 0;
                map[nx][ny] = number;

                x = nx;
                y = ny;
            }
            hashMap.replace(number, new Robot(number, direction, x, y));
        }
        return 0;
    }
}