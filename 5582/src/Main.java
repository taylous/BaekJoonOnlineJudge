import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] cache;
    static char[] s;
    static char[] t;

	static int Answer;

	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sLen, tLen;

		s = br.readLine().toCharArray();
		t = br.readLine().toCharArray();

		sLen = s.length;
		tLen = t.length;

		cache = new int[sLen + 1][tLen + 1];

		for(int i = 0; i < tLen; i++)
            cache[0][i] = s[0] == t[i] ? 1 : 0;

		for(int i = 1; i < sLen; i++) {

		    for(int j = 0; j < tLen; j++) {

		        if(j == 0) {

		            cache[i][j] = s[i] == t[j] ? 1 : 0;
		            continue;
                }

		        if(s[i] == t[j]) {

                    cache[i][j] += cache[i - 1][j - 1] + 1;
                    Answer = Math.max(Answer, cache[i][j]);
                }
            }
        }
        System.out.println(Answer);
		br.close();
	}
}