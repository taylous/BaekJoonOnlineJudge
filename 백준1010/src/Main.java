import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	static int N;
	static int M;
	
	public static void main(String args[]) throws Exception	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {

			temp = br.readLine().split(" ");
			
			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);

            BigInteger answer = new BigInteger("1");

            for (int i = M; i > M - N; i--)
                answer = answer.multiply(BigInteger.valueOf(i));            
            for (int i = N; i > 0; i--) 
                answer = answer.divide(BigInteger.valueOf(i));
            

			//System.out.print("Case #"+(test_case+1)+" ");
			System.out.println(answer);
		}
		
		br.close();
	}

}