import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {

    boolean isLeaf;
    int passBy;

    public Node(boolean isLeaf, int passBy) {
        this.isLeaf = isLeaf;
        this.passBy = passBy;
    }
}

public class Main {

    static int[] parents;
    static Node[] leaf;

	static int Answer;
	static int N;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int node;

		N = Integer.parseInt(br.readLine());

		parents = new int[N];
		leaf = new Node[N];
		for(int i = 0; i < N; i++)
		    leaf[i] = new Node(false, 0);

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		    parents[i] = Integer.parseInt(st.nextToken());
		parents[Integer.parseInt(br.readLine())] = -2;

		for(int i = 0; i < N; i++) {

            if(leaf[i].passBy > 0)
                continue;
		    leaf[i].isLeaf = true;
		    node = i;

		    while(true) {

		        if(parents[node] == -1)
		            break;
		        if(parents[node] == -2) {

		            leaf[i].isLeaf = false;
		            break;
                }
		        node = parents[node];
                leaf[node].isLeaf = false;
                leaf[node].passBy++;
            }
        }
		for(int i = 0; i < N; i++)
            Answer += leaf[i].isLeaf ? 1 : 0;
        System.out.println(Answer);
		br.close();
	}
}