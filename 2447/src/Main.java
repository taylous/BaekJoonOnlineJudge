import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {

	static String[][] stars = {
			{"***************************", 	//0
			 "* ** ** ** ** ** ** ** ** *",
			 "***************************",
			 "***   ******   ******   ***",
			 "* *   * ** *   * ** *   * *",
			 "***   ******   ******   ***",
			 "***************************",
			 "* ** ** ** ** ** ** ** ** *",		
			 "***************************"},	//8
			{"*********         *********",		//9
			 "* ** ** *         * ** ** *",
			 "*********         *********",
			 "***   ***         ***   ***",
			 "* *   * *         * *   * *",
			 "***   ***         ***   ***",
			 "*********         *********",
			 "* ** ** *         * ** ** *",
			 "*********         *********"},	//17
			{"***************************",		//18 
			 "* ** ** ** ** ** ** ** ** *",	
			 "***************************",
			 "***   ******   ******   ***",
			 "* *   * ** *   * ** *   * *",
			 "***   ******   ******   ***",
			 "***************************",
			 "* ** ** ** ** ** ** ** ** *",
			 "***************************"}		//26
	};
	
	static int Answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		
		System.out.println(Answer);
		br.close();
	}

}