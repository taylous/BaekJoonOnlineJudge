import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static LinkedList<Character> keyLogger = new LinkedList<>();
	static int Answer;
	static int cursor;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		char[] container;
		int size;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
		
			container = br.readLine().toCharArray();
			size = container.length;
			cursor = 0;
			
			for(int i = 0; i < size; i++) {
				
				switch(container[i]) {
				
				case '<':
				
					if(cursor - 1 >= 0)
						cursor--;
					break;
					
				case '>':
					
					if(cursor + 1 <= keyLogger.size())
						cursor++;
					break;
					
				case '-':
					
					if(cursor - 1 >= 0) {
						
						keyLogger.remove(cursor - 1);
						cursor--;
					}
					break;
					
				default:
					
					keyLogger.add(cursor++, container[i]);
					break;
				}
				//System.out.println("CURSOR> " + cursor + " KEYLOGGER> " + keyLogger + " " + container[i]);
			}
			for(char ch : keyLogger)
				System.out.print(ch);
			System.out.println();
			keyLogger.clear();
		}
		br.close();
	}

}
