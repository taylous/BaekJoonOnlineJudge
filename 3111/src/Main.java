import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    static String src;
    static String ptr;
	static int Answer;
	
	public static void main(String args[]) throws Exception	 {
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("3111\\input.txt"));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

		    ptr = br.readLine();
		    src = br.readLine();

		    LinkedList<Integer> test = search();
		    for(int var : test)
                System.out.println(var);

			System.out.println("Case #"+(test_case+1)+" "+Answer);
		}
		
		br.close();
	}
    static LinkedList<Integer> search() {
        LinkedList<Integer> searchIndex = new LinkedList<>();

        char[] s = src.toCharArray();
        char[] p = ptr.toCharArray();
        int[] pi = getPI();
        int n = src.length() - 1;
        int m = ptr.length();
        int j = m - 1;

        for (int i = n; i >= 0; i--) {
            while (j > 0 && s[i] != p[j]) {
                j = pi[j - 1];
            }
            if (s[i] == p[j]) {
                if (j == m - 1) {
                    searchIndex.add(i - m + 1);
                    j = pi[j];
                    break;
                } else {
                    j++;
                }
            }
        }
        return searchIndex;
    }

    static int[] getPI() {
        int m = ptr.length();
        int j = 0;
        char[] p = ptr.toCharArray();
        int[] pi = new int[m];

        for (int i = 1; i < m; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}