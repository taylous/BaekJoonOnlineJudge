import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] city;
    static int N;
    static int M;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
        int from, to, cost;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        city = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(city[i], 987654321);
            city[i][i] = 0;
        }

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            if (city[from][to] > cost)
                city[from][to] = cost;
        }
        floyd();

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= N; j++) {

                if (city[i][j] == 987654321)
                    sb.append(0);
                else
                    sb.append(city[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void floyd() {

        for (int k = 1; k <= N; k++) {

            for (int i = 1; i <= N; i++) {

                for (int j = 1; j <= N; j++) {

                    if (city[i][j] > city[i][k] + city[k][j])
                        city[i][j] = city[i][k] + city[k][j];
                }
            }
        }
    }
}