import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static LinkedList<Character> str = new LinkedList<>();

	static char[] source;
	static char[] pattern;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
		StringBuffer sb;

		boolean flag;
		int start, size;

		source = br.readLine().toCharArray();
		pattern = br.readLine().toCharArray();

		start = 0;
		size = source.length;

		while (start < size) {

			while (start < size) {

				str.addLast(source[start++]);

				if (str.size() >= pattern.length) {

					flag = true;

					for (int i = 0; i < pattern.length; i++) {

						if (pattern[i] != str.get(str.size() - pattern.length + i)) {

							flag = false;
							break;
						}
					}
					if (flag) {

						for (int i = 0; i < pattern.length; i++)
							str.removeLast();
						break;
					}
				}
			}
		}
		sb = new StringBuffer();
		while (!str.isEmpty())
			sb.append(str.removeFirst());
		System.out.println(sb.toString().length() == 0 ? "FRULA" : sb.toString());
		br.close();
	}

}
