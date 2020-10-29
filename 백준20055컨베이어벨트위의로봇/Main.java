package 백준20055컨베이어벨트위의로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> conveyorBelt = new ArrayList<>();
        int n, k;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n * 2; i++)
            conveyorBelt.add(Integer.parseInt(st.nextToken()));
        System.out.println(process(conveyorBelt, n, k));
        br.close();
    }

    private static int process(ArrayList<Integer> conveyorBelt, int n, int k) {

        ArrayList<Integer> robots = new ArrayList<>();
        int abrasion = 0, step = 0, durability, robot, location, robotsLen;
        int size = n * 2;

        while (abrasion < k) {

            durability = conveyorBelt.remove(size - 1);
            conveyorBelt.add(0, durability);

            robotsLen = robots.size();
            for (int i = 0; i < robotsLen; i++) {
                location = robots.get(i) + 1;

                if (location >= n - 1) {

                    robots.remove(i);
                    robotsLen--;
                    i--;
                    continue;
                }
                robots.set(i, location);
            }
            robotsLen = robots.size();
            for (int i = 0; i < robotsLen; i++) {

                robot = robots.get(i) + 1;

                if (conveyorBelt.get(robot) == 0 || robots.contains(robot))
                    continue;

                durability = conveyorBelt.get(robot) - 1;

                conveyorBelt.set(robot, durability);
                robots.set(i, robot);

                abrasion += durability == 0 ? 1 : 0;

                if (robot >= n - 1) {

                    robots.remove(i);
                    robotsLen--;
                    i--;
                }
            }

            if (!robots.contains(0) && conveyorBelt.get(0) >= 1) {

                robots.add(0);
                durability = conveyorBelt.get(0) - 1;
                conveyorBelt.set(0, durability);
                abrasion += durability == 0 ? 1 : 0;
            }
            step += 1;
        }
        return step;
    }
}
