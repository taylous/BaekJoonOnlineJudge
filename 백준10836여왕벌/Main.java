package 백준10836여왕벌;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] line = new int[(m * 2) - 1];
        int length = line.length;
        int index, until;

        Arrays.fill(line, 1);

        while (n-- > 0) {

            st = new StringTokenizer(br.readLine());
            index = Integer.parseInt(st.nextToken());
            until = Integer.parseInt(st.nextToken());

            for (; index < length && until-- > 0; index++)
                line[index] += 1;

            until = Integer.parseInt(st.nextToken());
            for (; index < length && until-- > 0; index++)
                line[index] += 2;
        }
        System.out.print(printHoneycomb(line, m));
        br.close();
    }

    private static String printHoneycomb(int[] line, int m) {

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < m; col++) {
                if (col == 0)
                    sb.append(line[m - row - 1]).append(" ");
                else
                    sb.append(line[m + col - 1]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
