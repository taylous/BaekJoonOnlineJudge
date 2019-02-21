import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] lan;
	
	static int Answer;
	static int N;
	static int K;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE;
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		lan = new int[N];
		for(int i = 0; i < N; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			
			if(min > lan[i])
				min = lan[i];
		}
		cuttingLanWire(min);
		System.out.println(Answer);
		br.close();
	}
	
	static int cutting(double offset) {
		
		int ret = 0;
		
		for(int i = 0; i < N; i++)
			ret += lan[i] / (int)offset;
		return ret;
	}

	static void cuttingLanWire(int min) {
		
		double low = 0;
		double high = min;
		int ret;
		double mid;
		
		while(low <= high) {
			
			mid = (low + high) / 2;
			
			ret = cutting(mid);
			
			if(ret < K) {
				
				high = mid - 1;
			}
			else if(ret == K) {
				
				Answer = (int) Math.round(mid + 0.4f);
				break;
			}
			else {

				low = mid + 1;
			}
		}
	}
}
