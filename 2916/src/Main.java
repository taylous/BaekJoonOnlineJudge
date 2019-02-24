import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static boolean[] angle = new boolean[361];

    static int N;
    static int K;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        while (N-- > 0) {

            int value = Integer.parseInt(st.nextToken());

            angle[value] = true;
            angle[Math.abs(value - 360)] = true;
        }
        angle[360] = true;

        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {

            int dest = Integer.parseInt(st.nextToken());

            if (RulerAndProtractor(dest))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        br.close();
    }

    static boolean RulerAndProtractor(int dest) {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        boolean[] visited = new boolean[361];

        arrayDeque.add(0);
        visited[0] = true;

        while (!arrayDeque.isEmpty()) {

            int currentAngle = arrayDeque.remove();

            if (currentAngle == dest)
                return true;

            for (int i = 0; i <= 360; i++) {

                if (currentAngle + i <= 360 && !visited[currentAngle + i]) {

                    visited[currentAngle + i] = true;
                    angle[currentAngle + i] = true;
                    arrayDeque.add(currentAngle + i);
                }

                if (!visited[Math.abs(i - currentAngle)]) {

                    visited[Math.abs(i - currentAngle)] = true;
                    angle[i - currentAngle] = true;
                    arrayDeque.add(Math.abs(i - currentAngle));
                }
            }
        }
        return false;
    }
}