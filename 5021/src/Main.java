import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Family {

	String leftParent;
	String rightParent;
	double lineageRate;
	
	public Family(String leftParent, String rightParent, double lineageRate) {
		
		this.leftParent = leftParent;
		this.rightParent = rightParent;
		this.lineageRate = lineageRate;
	}
}

public class Main {

	static HashMap<String, Family> familyTree = new HashMap<>();
	
	static String successor;
	static double Answer;
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String leftParent, rightParent, child;
		double ret;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ret = 0.0;
		
		familyTree.put(br.readLine(), new Family("ancestor", "ancestor", 1.0));
		
		while(N-- > 0) {
			
			st = new StringTokenizer(br.readLine());
			
			child = st.nextToken();
			leftParent = st.nextToken();
			rightParent = st.nextToken();
			
			familyTree.put(child, new Family(leftParent, rightParent, 0));
		}
		while(M-- > 0) {
			
			child = br.readLine();
			ret = successionToTheThrone(child);
			
			if(Answer < ret) {
				
				successor = child;
				Answer = ret;
			}
		}
		System.out.println(successor);
		familyTree.clear();
		br.close();
	}

	static double successionToTheThrone(String name) {
		
		if(!familyTree.containsKey(name))
			return 0.0;
		
		if(familyTree.get(name).leftParent.equals("ancestor"))	
			return familyTree.get(name).lineageRate;
		
		Family family = familyTree.get(name);
		double ret = 0.0;
		
		ret += successionToTheThrone(family.leftParent);
		ret += successionToTheThrone(family.rightParent);
		ret /= 2;
		
		return ret;
	}
}
