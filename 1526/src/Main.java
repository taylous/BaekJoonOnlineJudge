import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int Answer;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		theBiggestGeumminsu(0);
		System.out.println(Answer);
		br.close();
	}

	static void theBiggestGeumminsu(int geumminsu) {

		if(geumminsu > N)
			return;
		Answer = Math.max(Answer, geumminsu);
		
		theBiggestGeumminsu((geumminsu * 10) + 4);
		theBiggestGeumminsu((geumminsu * 10) + 7);
	}
}
