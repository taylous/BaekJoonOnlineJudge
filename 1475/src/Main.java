import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean[] available = new boolean[10];
	static char[] N;

	static int Answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] needNumber = new int[10];
		int number, sixAndNine, remain;

		N = br.readLine().toCharArray();
		sixAndNine = 0;
		remain = 0;

		for (int i = 0; i < N.length; i++) {

			number = N[i] - '0';

			if (number == 6 || number == 9)
				sixAndNine++;
			else {

				needNumber[number]++;
				remain = Math.max(remain, needNumber[number]);
			}
		}
		Answer += sixAndNine / 2;
		Answer += sixAndNine % 2;

		if (Answer == 0) {

			Answer = remain;

		} else {

			for (int set = 0; set < Answer; set++) {

				remain = 0;

				for (int i = 0; i < 10; i++) {

					if (needNumber[i] > 0) {
						needNumber[i]--;

						remain = Math.max(remain, needNumber[i]);
					}
				}
			}
			Answer += remain > 0 ? remain : 0;
		}
		System.out.println(Answer);
		br.close();
	}

}
