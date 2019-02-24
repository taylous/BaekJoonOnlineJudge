import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean Answer;
	
	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[9][9];

        for(int i = 0; i < 9; i++) {

            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 9; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


		br.close();
	}

	static void sudoku(int[][] map, int startX, int startY) {

	    if(startX == 8 && startY == 8)
	        return;
	    boolean flag = false;
	    int x = 0, y = 0;

	    for(int i = startX; i < 9; i++) {
	        for(int j = startY; j < 9; j++) {

	            if(map[i][j] == 0) {

	                x = i;
	                y = j;
	                flag = true;
	                break;
                }
            }
	        if(flag)
	            break;
        }

    }
}