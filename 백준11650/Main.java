import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Location {
	
	int x;
	int y;
	
	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	private static final int CUTOFF = 7;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int x, y;
		
		Location[] src = new Location[n];
		Location[] dest = new Location[n];
		
		
		for(int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			src[i] = new Location(x, y);
			dest[i] = new Location(x, y);
		}
		sort(src, dest, 0, n - 1);
		
		for(Location t : dest) {
			sb.append(t.x);
			sb.append(" ");
			sb.append(t.y);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	private static void exch(Location[] dest, int i, int j) {
		
		Location swap = dest[i];
		dest[i] = dest[j];
		dest[j] = swap;
	}
	
	private static void InsertionSort(Location[] dest, int lo, int hi) {
		
		for(int i = lo; i <= hi; i++) {
			
			for(int j = i; j > lo && less(dest[j], dest[j - 1]); j--)
				exch(dest, j, j - 1);
		}
	}
	
	private static boolean less(Location a, Location b) {
		
		if(a.x < b.x)
			return true;
		else if(a.x > b.x)
			return false;
		return a.y < b.y ? true : false;
	}
	
	private static void merge(Location[] src, Location[] dest, int lo, int mid, int hi) {
		
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
	
	private static void sort(Location[] src, Location[] dest, int lo, int hi) {
		
		if(hi <= lo + CUTOFF) {
			InsertionSort(dest, lo, hi);
			return;
		}
		
		int mid = lo + (hi - lo) / 2;
		
		sort(dest, src, lo, mid);
		sort(dest, src, mid + 1, hi);
		merge(src, dest, lo, mid, hi);
	}
}
