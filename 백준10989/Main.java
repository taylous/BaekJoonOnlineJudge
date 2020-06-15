import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		
		for(int i = 0; i < n; i++)
			a[i] = Integer.parseInt(br.readLine());
		sort(a, 0, n - 1);
		
		for(int value : a) {
			
			sb.append(value);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void exch(int[] a, int i, int j) {
		
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean less(int a, int b) {
		
		return a < b;
	}
	
    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
	
	public static void sort(int[] a, int lo, int hi) {
		
		if(hi <= lo)
			return;
		
		int lt = lo, i = lo + 1, gt = hi;
		int v = a[lo];
		
		while(i <= gt) {
			
			if(a[i] < v)
				exch(a, lt++, i++);
			else if(a[i] > v)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
		
		if(isSorted(a, lo, hi)) return;
	}
}