import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static LinkedList<Integer> list = new LinkedList<>();

    static int Answer;
    static int N;
    static int M;

	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int object, forward, backward;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++)
            list.add(i);

        st = new StringTokenizer(br.readLine());
        while(M-- > 0) {

            object = Integer.parseInt(st.nextToken());

            forward = findBackwardAndForward(true, object);
            backward = findBackwardAndForward(false, object);

            if(forward == 0 || backward == 0) {

                list.remove();
                continue;
            }

            if(forward < backward) {

                Answer += forward;
                while(forward-- > 0)
                    list.addLast(list.removeFirst());
            }
            else {

                Answer += backward;
                while(backward-- > 0)
                    list.addFirst(list.removeLast());
            }
            list.removeFirst();
        }
        System.out.println(Answer);
		br.close();
	}

	static int findBackwardAndForward(boolean direction, int object) {

	    int offset = direction ? 1 : -1;
	    int ptr = 0;
	    int count = 0;

	    while(true) {

	        if(list.get(ptr) == object)
	            return count;

	        ptr += offset;

	        if(ptr < 0)
	            ptr = list.size() - 1;
	        else if(ptr == list.size())
	            ptr = 0;
	        count++;
        }
    }
}