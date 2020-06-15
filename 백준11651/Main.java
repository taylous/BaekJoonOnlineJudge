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
		
		Location[] a = new Location[n];
		
		for(int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			a[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		sort(a, 0, n - 1);
		
		for(Location t : a) {
			sb.append(t.x);
			sb.append(" ");
			sb.append(t.y);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void exch(Location[] a, int i, int j) {
		
		Location swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean less(Location a, Location b) {
		
		boolean ret = false;
		
		if(a.y < b.y)
			ret = true;
		else if(a.y > b.y)
			ret = false;
		else {
			if(a.x < b.x)
				ret = true;
			else if(a.x > b.x)
				ret = false;
		}
		return ret;
	}
	
	private static void InsertionSort(Location[] a) {
		
		for(int i = 1; i < a.length; i++) {
			
			for(int j = i; j > 0 && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
		}
	}
	
	private static void sort(Location[] a, int lo, int hi) {
		
		if(hi <= lo + CUTOFF) {
			
			InsertionSort(a);
			return;
		}
		
		int lt = lo, i = lo + 1, gt = hi;
		Location v = a[lo];
		
		while(i <= gt) {
			
			if(a[i].y < v.y)
				exch(a, lt++, i++);
			else if(a[i].y > v.y)
				exch(a, i, gt--);
			else {
				if(a[i].x < v.x)
					exch(a, lt++, i++);
				else if(a[i].x > v.x)
					exch(a, i, gt--);
				else
					i++;
			}
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
}

