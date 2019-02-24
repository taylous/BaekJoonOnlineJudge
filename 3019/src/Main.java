import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][][] tetris = {

            {{0, -1, -1, -1}, {0, 0, 0, 0}, {-2, -1, -1, -1}, {-2, -1, -1, -1}},
            {{0, 0, -1, -1}, {-2, -1, -1, -1}, {-2, -1, -1, -1}, {-2, -1, -1, -1}},
            {{0, 0, 1, -2}, {0, 1, -2, -1}, {-2, -1, -1, -1}, {-2, -1, -1, -1}},
            {{0, 1, 1, -2}, {0, -1, -2, -1}, {-2, -1, -1, -1}, {-2, -1, -1, -1}},
            {{0, 0, 0, -1}, {0, -1, -2, -1}, {0, 1, 0, -1}, {0, 1, -2, -1}},
            {{0, 0, 0, -2}, {0, 0, -2, -1}, {0, -1, -1, -2}, {0, 2, -1, -2}},
            {{0, 0, 0, -2}, {0, 0, -2, -1}, {0, 0, 1, -2}, {0, -2, -1, -1}}
    };
    static int[] height;

    static int Answer;
    static int C;
    static int P;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        height = new int[C + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= C; i++)
            height[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= C; i++) {

            int startIdx = i;
            int endIdx = i;

            for(int form = 0; form < 4; form++) {

                int formIdx = 0;
                while(true) {

                    if(startIdx > C)
                        break;

                    if(height[startIdx] - height[endIdx] == tetris[P][form][formIdx]) {

                        if(formIdx == 3) {

                            Answer++;
                            formIdx = 0;
                            startIdx++;
                            endIdx = startIdx;

                            continue;
                        }
                        formIdx++;
                    }
                    else {

                        startIdx++;
                        endIdx = startIdx;
                        formIdx = 0;
                    }
                }
            }
        }
        System.out.println(Answer);

        br.close();
    }
}