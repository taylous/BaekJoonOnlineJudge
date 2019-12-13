import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Turn {

    int turn;

    public Turn(int turn) {
        this.turn = turn;
    }
}

public class Main {

    static int[][] hitter;
    static int Answer;
	static int N;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("17281\\input.txt"));
        StringTokenizer st;

        int[] sequence = new int[10];

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

		    N = Integer.parseInt(br.readLine());
		    hitter = new int[N][9];

		    for(int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < 9; j++)
                    hitter[i][j] = Integer.parseInt(st.nextToken());
            }
		    sequence[0] = 4;
		    baseball(sequence, 1);
			System.out.println(Answer);
		    Answer = 0;
		}
		br.close();
	}

	static int score(int[] lineUp, Turn current) {

        int[] base = new int[3];
	    int ret = 0;
	    int out = 0;
	    while(out < 3) {

	        if(lineUp[current.turn] == 0) {
                out++;
            }
	        else {

	            if(lineUp[current.turn] == 1) {

	                ret += base[2];

                    base[2] = base[1];
                    base[1] = base[0];
                    base[0] = 1;
                }
	            else if(lineUp[current.turn] == 2) {

	                ret += base[2];
	                ret += base[1];

	                base[2] = base[0];
	                base[1] = 1;
	                base[0] = 0;
                }
	            else {

                    for(int runner : base)
                        ret += runner;
                    Arrays.fill(base, 0);

                    if(lineUp[current.turn] == 4)
                        ret += 1;
                    if(lineUp[current.turn] == 3)
                        base[2] = 1;
                }
            }
            current.turn++;
	        if(current.turn == 9)
                current.turn = 0;
        }
	    return ret;
    }

	static void baseball(int[] sequence, int turn) {

	    if(turn == 4) {

	        baseball(sequence, turn + 1);
	        return;
        }
	    if(turn == 10) {

	        int[] lineUp = new int[9];
	        int[] inningScores = new int[N];
	        int ret = 0;
	        Turn currentTurn = new Turn(0);

	        for(int inning = 0; inning < N; inning++) {

                for (int i = 0; i < 9; i++) {

                    for (int j = 0; j < 9; j++) {

                        if (sequence[j] == i + 1) {

                            lineUp[i] = hitter[inning][j];
                            break;
                        }
                    }
                }
                inningScores[inning] = Math.max(inningScores[inning], score(lineUp, currentTurn));
            }
	        for(int value : inningScores)
	            ret += value;
	        Answer = Math.max(Answer, ret);
	        return;
        }

	    for(int i = 0; i < 9; i++) {

	        if(sequence[i] == 0) {

                sequence[i] = turn;
	            baseball(sequence, turn + 1);
                sequence[i] = 0;
            }
        }
    }
}