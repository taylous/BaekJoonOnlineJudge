import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] src = new int[n];
		int[] dest = new int[n];
		
		for(int i = 0; i < n; i++) {
			
			src[i] = Integer.parseInt(br.readLine());
			dest[i] = src[i];
		}
		sort(src, dest, 0, n - 1);
		
		for(int value : dest) {
			
			sb.append(value);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	public static boolean less(int i, int j) {
		
		return i < j ? true : false;
	}
	
	public static void merge(int[] src, int[] dest, int lo, int mid, int hi) {
		
		int i = lo, j = mid + 1;
		
		for(int k = lo; k <= hi; k++) {
			
			if(i > mid)
				dest[k] = src[j++];
			else if(j > hi)
				dest[k] = src[i++];
			else if(less(src[j], src[i]))
				dest[k] = src[j++];
			else
				dest[k] = src[i++];
		}
	}
	
	public static void sort(int[] src, int[] dest, int lo, int hi) {
		
		if(hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		
		sort(dest, src, lo, mid);
		sort(dest, src, mid + 1, hi);
		merge(src, dest, lo, mid, hi);
	}
}