import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static LinkedList<Integer> hackedComputer = new LinkedList<>();
	static ArrayList<ArrayList<Integer>> network = new ArrayList<>();

	static int hackedValue;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int x, y, ret;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ret = 0;

		for(int i = 0; i <= N; i++)
			network.add(new ArrayList<>());
		
		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());

			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			network.get(x).add(y);
		}

		for (int i = 1; i <= N; i++) {

			ret = efficientHacking(i);

			if (ret > hackedValue) {

				hackedComputer.clear();
				hackedValue = ret;
				hackedComputer.add(i);
			} else if (ret == hackedValue) {

				hackedComputer.add(i);
			}
		}
		Collections.sort(hackedComputer);
		while (!hackedComputer.isEmpty())
			System.out.print(hackedComputer.remove() + " ");
		System.out.println();
		br.close();
	}

	static int efficientHacking(int start) {

		ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
		ArrayList<Integer> adjacent = new ArrayList<>();
		
		boolean[] visited = new boolean[N + 1];
		int ret = 1, here;

		arrayDeque.add(start);
		visited[start] = true;

		while (!arrayDeque.isEmpty()) {

			here = arrayDeque.remove();
			
			if(network.get(here).isEmpty())
				continue;
			adjacent.addAll(network.get(here));
			
			for(int there : adjacent) {

				if (!visited[there]) {

					visited[there] = true;
					ret++;
					arrayDeque.add(there);
				}
			}
			adjacent.clear();
		}
		return ret;
	}
}