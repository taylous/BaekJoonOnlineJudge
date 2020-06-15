import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long value = 1;
		
		for(int i = 0; i < 3; i++)
			value *= Integer.parseInt(br.readLine());
		
		char[] chars = String.valueOf(value).toCharArray();
		int[] numbers = new int[10];
		
		for(char ch : chars)
			numbers[ch - '0']++;
		for(int number : numbers)
			System.out.println(number);	
		br.close();
	}
}