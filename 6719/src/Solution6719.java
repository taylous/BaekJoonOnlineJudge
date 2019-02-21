import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution6719 {
 
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
     
    static double Answer;
    static int N;
    static int K;
     
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++) {
             
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++)
                priorityQueue.offer(Integer.parseInt(st.nextToken()));
            while(K != priorityQueue.size())
                priorityQueue.poll();
            while(K-- > 0)
                Answer = (Answer + priorityQueue.poll()) / 2;
            System.out.println("#"+(test_case+1)+" "+Answer);
            priorityQueue.clear();
            Answer = 0.0;
        }
        br.close();
    }
}