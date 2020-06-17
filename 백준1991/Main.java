import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Tree tree;
		int n = Integer.parseInt(br.readLine()) - 1;
		char key, left, right;

		st = new StringTokenizer(br.readLine());
		
		key = st.nextToken().charAt(0);
		left = st.nextToken().charAt(0);
		right = st.nextToken().charAt(0);
		
		tree = new Tree(key);
		tree.put(key, left, right);
		
		while(n-- > 0) {
			
			st = new StringTokenizer(br.readLine());
			
			key = st.nextToken().charAt(0);
			left = st.nextToken().charAt(0);
			right = st.nextToken().charAt(0);
			
			tree.put(key, left, right);
		}
		tree.preorder();
		tree.inorder();
		tree.postorder();
		br.close();
	}

}

class Tree {
	
	private class Node {
		
		char key;
		Node left, right;
		
		public Node(char key) {
			super();
			this.key = key;
			this.left = this.right = null;
		}
	}
	private Node root;
	
	public Tree() {
		this('@');
	}
	
	public Tree(char key) {
		
		this.root = new Node(key);
	}
	
	public void put(char key, char left, char right) {
		
		put(this.root, key, left, right);
	}
	
	private void put(Node node, char key, char left, char right) {
		
		if(node == null)
			return;
		if(node.key == key) {
			
			if(left != '.')
				node.left = new Node(left);
			if(right != '.')
				node.right = new Node(right);
			return;
		}
		put(node.left, key, left, right);
		put(node.right, key, left, right);
	}
	
	public void preorder() {
		
		preorder(this.root);
		System.out.println();
	}
	
	private void preorder(Node node) {
		
		if(node == null)
			return;
		
		System.out.print(node.key);
		preorder(node.left);
		preorder(node.right);
	}
	
	public void inorder() {
		
		inorder(this.root);
		System.out.println();
	}
	
	private void inorder(Node node) {
		
		if(node == null)
			return;
		
		inorder(node.left);
		System.out.print(node.key);
		inorder(node.right);
	}
	
	public void postorder() {
		
		postorder(this.root);
		System.out.println();
	}
	
	private void postorder(Node node) {
		
		if(node == null)
			return;
		
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.key);
	}
}
