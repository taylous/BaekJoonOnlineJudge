import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static char[] firstName;
	static int N;
	
	public static void main(String args[]) throws Exception	 {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = false;

        firstName = new char[26];
        N = Integer.parseInt(br.readLine());

        while(N-- > 0)
            firstName[br.readLine().charAt(0) - 'a']++;

        for(int i = 0; i < 26; i++) {

            if(firstName[i] >= 5) {

                System.out.print((char)(i + 'a'));
                flag = true;
            }
        }
        if(!flag)
            System.out.println("PREDAJA");
        else
            System.out.println();
		br.close();
	}
}