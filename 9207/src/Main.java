import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] loX = { -1, 0, 1, 0 };
    static int[] loY = { 0, 1, 0, -1 };

	static int Answer;
	static int remainPin;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("9207\\input.txt"));

        char[][] board = new char[5][9];
		char[] container;

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

		    Answer = remainPin = Integer.MAX_VALUE;

		    for(int i = 0; i < 5; i++) {

		        container = br.readLine().toCharArray();

		        for(int j = 0; j < 9; j++)
		            board[i][j] = container[j];
            }
		    pegSolitaire(board, 0);
			System.out.println(remainPin + " " + Answer);
			br.readLine();
		}
		br.close();
	}

	static boolean validationCheck(char[][] board, int x, int y, int dir) {

	    switch(dir) {

            case 0:

                if(x - 2 >= 0 && board[x - 2][y] == '.')
                    return true;
                break;
            case 1:

                if(y + 2 < 9 && board[x][y + 2] == '.')
                    return true;
                break;
            case 2:

                if(x + 2 < 5 && board[x + 2][y] == '.')
                    return true;
                break;
            case 3:

                if(y - 2 >= 0 && board[x][y - 2] == '.')
                    return true;
                break;
        }
        return false;
    }

	static void pegSolitaire(char[][] copy, int count) {

	    char[][] board = new char[5][9];
	    boolean flag = false;
	    int nx, ny, remain = 0;

	    for(int i = 0; i < 5; i++) {

	        for(int j = 0; j < 9; j++) {

	            if(copy[i][j] == 'o') {

	                for(int k = 0; k < 4; k++) {

	                    nx = i + loX[k];
	                    ny = j + loY[k];

	                    if(nx < 0 || nx >= 5 || ny < 0 || ny >= 9)
	                        continue;
	                    if(copy[nx][ny] == '#')
	                        continue;

	                    if(copy[nx][ny] == 'o') {

	                        if(validationCheck(copy, i, j, k)) {

                                for (int row = 0; row < 5; row++)
                                    System.arraycopy(copy[row], 0, board[row], 0, 9);

                                board[i][j] = '.';
                                board[nx][ny] = '.';
                                board[i + loX[k] + loX[k]][j + loY[k] + loY[k]] = 'o';

                                pegSolitaire(board, count + 1);
                                flag = true;
                            }

                        }
                    }
                    remain++;
                }
            }
        }

	    if(!flag) {

	        if(remainPin > remain) {

	            Answer = count;
	            remainPin = remain;
            }
        }
    }
}