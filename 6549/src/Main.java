import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] rectangles;
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		while (true) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			rectangles = new long[N];
			for (int i = 0; i < N; i++)
				rectangles[i] = Long.parseLong(st.nextToken());
			sb.append(theLargestRectangleInTheHistogram());
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static long theLargestRectangleInTheHistogram() {

		long height, offset;
		long max = 0;

		for (int i = 0; i < N; i++) {

			height = rectangles[i];
			offset = 1;

			for (int j = i - 1; j >= 0; j--) {

				if(rectangles[j] >= height) {
					
					offset++;
				}
				else {
				
//					if(height * offset < rectangles[j] * (offset + 1)) {
//						
//						height = rectangles[j];
//						offset++;
//					}
//					else
						break;
				}
			}

			for (int j = i + 1; j < N; j++) {

				if(rectangles[j] >= height) {
					
					offset++;
				}
				else {
					
//					if(height * offset < rectangles[j] * (offset + 1)) {
//						
//						height = rectangles[j];
//						offset++;
//					}
//					else
						break;
				}
			}
			max = Math.max(max, height * offset);
		}
		return max;
	}
}
