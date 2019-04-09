import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] students;

	static int Answer;
	static int N;
	static int M;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {

            for(int j = 1; j <= N; j++) {

                if(i == j)
                    students[i][j] = 0;
                else
                    students[i][j] = 987654321;
            }
        }

        while(M-- > 0) {

            st = new StringTokenizer(br.readLine());
            students[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for(int k = 1; k <= N; k++) {

            for (int i = 1; i <= N; i++) {

                for (int j = 1; j <= N; j++) {

                    if (students[i][j] > students[i][k] + students[k][j])
                        students[i][j] = students[i][k] + students[k][j];
                }
            }
        }

        for(int i = 1; i <= N; i++) {

            flag = true;

            for(int j = 1; j <= N; j++) {

                if(students[i][j] >= 987654321 && students[j][i] >= 987654321) {

                    flag = false;
                    break;
                }
            }
            Answer += flag ? 1 : 0;
        }
        System.out.println(Answer);
		br.close();
	}
}