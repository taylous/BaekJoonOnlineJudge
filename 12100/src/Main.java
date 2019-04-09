import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int Answer;
    static int N;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] board;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        twoZeroFourEight(board, 0);
        System.out.println(Answer);
        br.close();
    }

    static int summedUp(int[][] board) {

        int ret = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++)
                ret = Math.max(ret, board[i][j]);
        }
        return ret;
    }

    static void twoZeroFourEight(int[][] board, int times) {

        if (times == 5) {

            Answer = Math.max(Answer, summedUp(board));
            return;
        }
        int[][] copy = new int[N][N];

        for (int i = 0; i < 4; i++) {

            for (int k = 0; k < N; k++)
                System.arraycopy(board[k], 0, copy[k], 0, N);

            if (i == 0 || i == 2)
                pushingUpAndDown(copy, i);
            else
                pushingSideways(copy, i);

            twoZeroFourEight(copy, times + 1);
        }
    }

    static void pushingSideways(int[][] board, int dir) {

        boolean[][] visited = new boolean[N][N];
        int y;

        if (dir == 1) {

            for (int row = 0; row < N; row++) {

                for (int col = N - 2; col >= 0; col--) {

                    if (board[row][col] == 0)
                        continue;
                    y = col;

                    while (true) {

                        if (y == N - 1)
                            break;

                        if (board[row][y + 1] == board[row][y]) {

                            if (!visited[row][y] && !visited[row][y + 1]) {

                                board[row][y + 1] += board[row][y];
                                board[row][y] = 0;

                                visited[row][y + 1] = true;
                                y++;

                                continue;
                            }
                        }

                        if (board[row][y + 1] == 0) {

                            board[row][y + 1] = board[row][y];
                            board[row][y] = 0;
                            y++;
                        } else
                            break;
                    }
                }
            }
        } else {

            for (int row = 0; row < N; row++) {

                for (int col = 1; col < N; col++) {

                    if (board[row][col] == 0)
                        continue;
                    y = col;

                    while (true) {

                        if (y == 0)
                            break;

                        if (board[row][y - 1] == board[row][y]) {

                            if (!visited[row][y] && !visited[row][y - 1]) {

                                board[row][y - 1] += board[row][y];
                                board[row][y] = 0;

                                visited[row][y - 1] = true;
                                y--;

                                continue;
                            }
                        }

                        if (board[row][y - 1] == 0) {

                            board[row][y - 1] = board[row][y];
                            board[row][y] = 0;
                            y--;
                        } else
                            break;
                    }
                }
            }
        }
    }

    static void pushingUpAndDown(int[][] board, int dir) {

        boolean[][] visited = new boolean[N][N];
        int x;

        if (dir == 0) {

            for (int col = 0; col < N; col++) {

                for (int row = 1; row < N; row++) {

                    if (board[row][col] == 0)
                        continue;
                    x = row;

                    while (true) {

                        if (x == 0)
                            break;

                        if (board[x - 1][col] == board[x][col]) {

                            if (!visited[x][col] && !visited[x - 1][col]) {

                                board[x - 1][col] += board[x][col];
                                board[x][col] = 0;
                                visited[x - 1][col] = true;
                                x--;

                                continue;
                            }
                        }

                        if (board[x - 1][col] == 0) {

                            board[x - 1][col] = board[x][col];
                            board[x][col] = 0;
                            x--;
                        } else
                            break;
                    }
                }
            }
        } else {

            for (int col = 0; col < N; col++) {

                for (int row = N - 2; row >= 0; row--) {

                    if (board[row][col] == 0)
                        continue;
                    x = row;

                    while (true) {

                        if (x == N - 1)
                            break;

                        if (board[x + 1][col] == board[x][col]) {

                            if (!visited[x][col] && !visited[x + 1][col]) {

                                board[x + 1][col] += board[x][col];
                                board[x][col] = 0;
                                visited[x + 1][col] = true;
                                x++;

                                continue;
                            }
                        }

                        if (board[x + 1][col] == 0) {

                            board[x + 1][col] = board[x][col];
                            board[x][col] = 0;
                            x++;
                        } else
                            break;
                    }
                }
            }
        }
    }
}