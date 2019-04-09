import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long Answer;
	static int A;
	static int B;
	static int V;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long left, right, mid;

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        Answer = 1000000001;

        left = 0;
        right = V / (A - B) + 1;

        while(left <= right) {

            mid = (left + right) / 2;

            if(V <= mid * (A - B) + A) {

                right = mid - 1;
                Answer = Math.min(Answer, mid + 1);
            }
            else
                left = mid + 1;
        }
        System.out.println(Answer);
		br.close();
	}
}