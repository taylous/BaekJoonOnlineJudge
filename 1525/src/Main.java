import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };
    static int Answer;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] puzzle = new int[3][3];
        int x = -1, y = -1;

        for(int i = 0; i < 3; i++) {

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());

                if(puzzle[i][j] == 0) {

                    x = i;
                    y = j;
                }
            }
        }
        if(x == -1)
            System.out.println(-1);
        else {

            Answer = Integer.MAX_VALUE;
            movingPuzzle(puzzle, new boolean[3][3], 0, x, y);
            System.out.println(Answer);
        }
        br.close();
    }

    static boolean chk(int[][] puzzle) {

        int var = 1;

        for(int i = 0; i < 3; i++) {

            for(int j = 0; j < 3; j++) {

                if(i == 2 && j == 2 && puzzle[i][j] == 0)
                    return true;
                else if (puzzle[i][j] != var++)
                    return false;
            }
        }
        return false;
    }

    static void movingPuzzle(int[][] puzzle, boolean[][] visited, int times, int x, int y) {

        if(chk(puzzle)) {

            if(Answer > times)
                Answer = times;
            return;
        }

        for(int i = 0; i < 4; i++) {

            int nx = x + loX[i];
            int ny = y + loY[i];

            if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3 || visited[nx][ny])
                continue;

            visited[nx][ny] = true;

            int temp = puzzle[nx][ny];
            puzzle[nx][ny] = 0;
            puzzle[x][y] = temp;
            movingPuzzle(puzzle, visited, times + 1, nx, ny);
            puzzle[x][y] = 0;
            puzzle[nx][ny] = temp;
            visited[nx][ny] = false;
        }
    }
}