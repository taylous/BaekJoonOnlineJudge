import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] trees;

	static long Answer;
	static int N;
	static long M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long max;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		max = 0;

		trees = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());

			if (max < trees[i])
				max = trees[i];
		}
		treeCutting(max);
		System.out.println(Answer);

		br.close();
	}

	static long gettingApieceOfWood(long offset) {

		long pieceOfWood = 0;

		for (int i = 0; i < N; i++) {

			if (offset < trees[i])
				pieceOfWood += trees[i] - offset;
		}
		return pieceOfWood;
	}

	static void treeCutting(long max) {

		long low = 0;
		long high = max;
		long mid;
		long ret;

		while (low <= high) {

			mid = (low + high) / 2;
			ret = gettingApieceOfWood(mid);

			if (ret >= M) {

				if (Answer < mid)
					Answer = mid;
				low = mid + 1;
			} else {

				high = mid - 1;
			}
		}
	}
}
