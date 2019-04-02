import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CHECK {

	public static void main(String[] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new FileReader("sample_out.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("out.txt"));
		int total = 1;
		int count = 1;
		int input = 0;
		int no = 0;
		
		while(true) {
			
			String answer = br.readLine();
			if(answer == null)
				break;
			String answer2 = br2.readLine();
			
			
/*			if(answer.equals("") && answer2.equals("")) {
				input++;
			
				System.out.println("WHITESPACE " + input + "\n");
				continue;
			}
			System.out.println("ORIGIN> " + answer + " MY> " + answer2 + " COUNT : " + count++);
*/
			
			if(answer.equals(answer2))
				System.out.println("OK");
			else {
				System.out.println("NO");
				no++;
			}
			//System.out.println("total : " + total);
			total++;
		}
		System.out.println("틀린갯수> " + no);
		br.close();
		br2.close();
	}

}
