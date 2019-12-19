import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int DECIMAL = 10;
    static int[][] board;

    static int[] loX = {1, 0};
    static int[] loY = {0, 1};

    static int Answer;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] container;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {

            container = br.readLine().toCharArray();

            for (int j = 0; j < M; j++)
                board[i][j] = container[j] - '0';
        }
        pieceOfPaper(new boolean[N][M], N * M, 0);
        System.out.println(Answer);
        br.close();
    }

    static void pieceOfPaper(boolean[][] origin, int remain, int total) {

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++)
            System.arraycopy(origin[i], 0, visited[i], 0, M);

        if (remain == 0) {

            Answer = Math.max(Answer, total);
            return;
        }
        int nx, ny;
        int temp, len;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                if (!visited[i][j]) {

                    for (int k = 0; k < 2; k++) {

                        temp = 0;
                        len = 1;
                        nx = i;
                        ny = j;

                        while (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {

                            visited[nx][ny] = true;
                            temp *= DECIMAL;
                            temp += board[nx][ny];

                            pieceOfPaper(visited, remain - len++, total + temp);

                            nx += loX[k];
                            ny += loY[k];
                        }
                        for (int r = 0; r < N; r++)
                            System.arraycopy(origin[r], 0, visited[r], 0, M);
                    }
                    return;
                }
            }
        }
    }
}