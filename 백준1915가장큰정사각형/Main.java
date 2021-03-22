package 백준1915가장큰정사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            char[] inputChars = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = inputChars[j - 1] - '0';
            }
        }

        for (int row = 1; row <= n; row += 1) {
            for (int col = 1; col <= m; col += 1) {

                if (matrix[row][col] != 0) {

                    matrix[row][col] = Math.min(matrix[row - 1][col], Math.min(matrix[row - 1][col - 1], matrix[row][col - 1])) + 1;
                    if (answer < matrix[row][col])
                        answer = matrix[row][col];
                }
            }
        }
        System.out.println(answer * answer);
        br.close();
    }
}
