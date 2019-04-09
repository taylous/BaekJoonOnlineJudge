import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> order = new ArrayList<>();
    static ArrayDeque<Integer> Answer = new ArrayDeque<>();

    static int[] targetVertexCount;
	static int N;
	static int M;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int from, to;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        targetVertexCount = new int[N + 1];

        for(int i = 0; i <= N; i++)
            order.add(new ArrayList<>());

        while(M-- > 0) {

            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            order.get(from).add(to);
            targetVertexCount[to]++;
        }

        for(int i = 1; i <= N; i++) {
            if (targetVertexCount[i] == 0)
                arrayDeque.add(i);
        }

        while(!arrayDeque.isEmpty()) {

            from = arrayDeque.remove();
            Answer.add(from);

            for(int there : order.get(from)) {

                targetVertexCount[there]--;
                if(targetVertexCount[there] == 0)
                    arrayDeque.add(there);
            }
        }

        for(int here : Answer) {

            sb.append(here);
            sb.append(" ");
        }
        System.out.println(sb.toString());
		br.close();
	}
}