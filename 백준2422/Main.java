import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[][] tastes;
        
        int N, M, from, to;
        int answer = 0;
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tastes = new boolean[N + 1][N + 1];
        
        for(int i = 0; i < M; i++) {
        	
        	st = new StringTokenizer(br.readLine());
        	
        	from = Integer.parseInt(st.nextToken());
        	to = Integer.parseInt(st.nextToken());
        	
        	tastes[from][to] = true;
        	tastes[to][from] = true;
        }
        
        for(int i = 1; i <= N - 2; i++) {
        	for(int j = i + 1; j <= N - 1;j ++) {
        		
        		if(tastes[i][j])
        			continue;
        		
        		for(int k = j + 1; k <= N; k++) {
        			
        			if(tastes[i][k] || tastes[j][k])
        				continue;
        			
        			answer++;
        		}
        	}
        }
        System.out.println(answer);
        br.close();
    }
}