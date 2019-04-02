import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char[][][] picture;
	static int Answer;
	static int N;
	
	static int pictureA;
	static int pictureB;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] container;
		
		N = Integer.parseInt(br.readLine());
		picture = new char[N][5][7];
		
		for(int i = 0; i < N; i++) {
		
			for(int row = 0; row < 5; row++) {
		
				container = br.readLine().toCharArray();
				
				for(int col = 0; col < 7; col++)
					picture[i][row][col] = container[col];
			}
		}
		comparePictures();
		System.out.println(pictureA + " " + pictureB);
		br.close();
	}

	static void comparePictures() {
		
		int max = Integer.MAX_VALUE, diff;
		
		for(int i = 0; i < N; i++) {
			
			for(int j = i + 1; j < N; j++) {

				diff = 0;
				
				for(int row = 0; row < 5; row++) {
					
					for(int col = 0; col < 7; col++)
						diff += picture[i][row][col] != picture[j][row][col] ? 1 : 0; 
				}
				
				if(max > diff) {
					
					pictureA = i + 1;
					pictureB = j + 1;
					max = diff;
				}
			}
		}
	}
}
