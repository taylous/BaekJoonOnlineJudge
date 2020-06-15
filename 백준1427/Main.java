import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		char[] n = br.readLine().toCharArray();
		
		int[] a = new int[n.length];
		int[] aux = new int[n.length];
		
		for(int i = 0; i < a.length; i++) {
			a[i] = n[i] - '0';
			aux[i] = a[i];
		}
		sort(a, aux, 0, a.length - 1);
		
		for(int value : aux)
			sb.append(value);
		sb.append("\n");
		System.out.println(sb.toString());
		br.close();
	}
	
	private static boolean less(int a, int b) {
		
		return a < b ? false : true;
	}

	private static void merge(int[] src, int[] dest, int lo, int mid, int hi) {
		
		int i = lo;
		int j = mid + 1;
		
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
	
	private static void sort(int[] src, int[] dest, int lo, int hi) {
		
		if(hi <= lo)
			return;
		
		int mid = lo + (hi - lo) / 2;
		
		sort(dest, src, lo, mid);
		sort(dest, src, mid + 1, hi);
		merge(src, dest, lo, mid, hi);
	}
}
