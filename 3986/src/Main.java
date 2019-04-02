import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static Stack<Character> stack = new Stack<>();
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] words;
		
		N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			
			words = br.readLine().toCharArray();
			
			for(char word : words) {
				
				if(stack.isEmpty()) {
					
					stack.push(word);
					continue;
				}
				
				if(stack.peek() == word)
					stack.pop();
				else
					stack.push(word);
			}
			Answer += stack.isEmpty() ? 1 : 0;
			stack.clear();
		}
		System.out.println(Answer);
		br.close();
	}
}