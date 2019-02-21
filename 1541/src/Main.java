import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int Answer;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sequence = br.readLine();
		String[] minus = sequence.split("\\-");
		
		int temp;
		for (int i = 0; i < minus.length; i++) {

			String[] plus = minus[i].split("\\+");

			temp = 0;
			for (String x : plus)
				temp += Integer.parseInt(x);
			
			if (i == 0)
				temp *= -1;

			Answer -= temp;
		}
		System.out.println(Answer);
		br.close();
	}
}
