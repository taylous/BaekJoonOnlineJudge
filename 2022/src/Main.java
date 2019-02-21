import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double a = Double.parseDouble(st.nextToken());
		double b = Double.parseDouble(st.nextToken());
		double c = Double.parseDouble(st.nextToken());
		
		double low = 0.0;
		double high = Math.min(a, b);
		double mid, hx, hy, k, f;
		
		while(high - low > 1e-6) {
			
			mid = (low + high) / 2.0;
			hx = Math.sqrt(a * a - mid * mid);
			hy = Math.sqrt(b * b - mid * mid);
			k = mid * c / hy;
			f = (-hx * k / mid) + hx;
			
			if(f > c)
				low = mid;
			else
				high = mid;
		}
		System.out.format("%.3f\n", low);
		br.close();
	}
}
