import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BST bst = new BST();
		String value;
		
		while((value = br.readLine()) != null && value.length() > 0)
			bst.put(Integer.parseInt(value));
		bst.postorder();
		br.close();
	}

}

class BST {
	
	private Node root;
	
	private class Node {
		
		int value;
		int size;
		Node left, right;
		
		public Node(int value) {
			super();
			this.value = value;
			this.size = 0;
			this.left = this.right = null;
		}
	}
	
	public BST() {}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		
		if(node == null)
			return 0;
		return node.size;
	}
	
	public void put(int value) {
		
//		this.root = put(this.root, value);
		
		if(this.root == null) {
			
			this.root = new Node(value);
			return;
		}
		Node node = this.root;
		
		while(true) {
			
			if(node.value > value) {
				
				if(node.left == null) {
					node.left = new Node(value);
					break;
				}
				else {
					node = node.left;
				}
			}
			else {

				if(node.right == null) {
					node.right = new Node(value);
					break;
				}
				else {
					node = node.right;
				}
			}
		}
	}
	
//	private Node put(Node node, int value) {
//		
//		if(node == null)
//			return new Node(value);
//		
//		if(value < node.value)
//			node.left = put(node.left, value);
//		else if(value > node.value)
//			node.right = put(node.right, value);
//		else
//			node.value = value;
//		node.size = 1 + size(node.left) + size(node.right);
//		return node;
//	}
	
	public void postorder() {
		
		postorder(this.root);
	}
	
	private void postorder(Node node) {
		
		if(node == null)
			return;
		
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.value);
	}
}
