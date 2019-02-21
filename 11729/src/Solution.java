import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
        bw.write((int) Math.pow(2, N) - 1 + "\n");
        solve(N, 1, 3);

        bw.flush();
        bw.close();
        br.close();
    }

    static void solve(int N, int from, int to) throws IOException {

        if (N == 0) return;

        solve(N - 1, from, 6 - from - to);
        bw.write(from + " " + to + "\n");
        solve(N - 1, 6 - from - to, to);
    }
}
