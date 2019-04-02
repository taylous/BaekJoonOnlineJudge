import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final long NUMBER_OVERFLOW = 1000000000;
	
	static ILinkedList linkedList = new ILinkedList();
	static IStack stack = new IStack();
	
	static boolean ERROR;
	static int Answer;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command;
	
		while(true) {

			while(true) {
				
				command = br.readLine();
				if(command.equals("END"))
					break;
				linkedList.add(command);
			}
			
			N = Integer.parseInt(br.readLine());
			while(N-- > 0) {
				
				stack.clear();
				int tmp = parseInt(br.readLine());
				
				stack.push(tmp);
				
				for(int i = 0; i < linkedList.size(); i++)
					goStack(linkedList.get(i));
				
				if(ERROR || stack.size() != 1)
					System.out.println("ERROR");
				else
					System.out.println(stack.peek());
				ERROR = false;
			}
			br.readLine(); //whitespace
			command = br.readLine();
			linkedList.clear();
			
			if(command.equals("QUIT"))
				break;
			else
				linkedList.add(command);
			System.out.println();
		}
		br.close();
	}
	
	static int parseInt(String str) {
		
		int offset = 1;
		int ret = 0;
		
		for(int i = str.length() - 1; i >= 0; i--) {
			ret += (str.charAt(i) - '0') * offset;
			offset *= 10;
		}
		return ret;
	}

	static boolean overflowCheck(long value) {
		
		return (value < 0 ? value * -1 : value) > NUMBER_OVERFLOW ? true : false;
	}
	
	static void goStack(String command) {
		
		boolean minus = false;
		long temp, temp2, temp3;
		
		switch(command) {
		
		case "POP":
			
			if(stack.pop() == NUMBER_OVERFLOW + 1)
				ERROR = true;
			break;
			
		case "INV":
			
			temp = stack.pop();
			
			if(temp == NUMBER_OVERFLOW + 1) {
				ERROR = true;
				return;
			}
			if(!stack.push(temp * -1))
				ERROR = true;
			break;
			
		case "DUP":
			
			temp = stack.peek();
			if(temp == NUMBER_OVERFLOW + 1) {
				ERROR = true;
				return;
			}
			if(!stack.push(temp))
				ERROR = true;
			break;
			
		case "SWP":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();
			
			if(!stack.push(temp))
				ERROR = true;
			if(!stack.push(temp2))
				ERROR = true;
			break;
			
		case "ADD":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();
			
			if(overflowCheck(temp + temp2)) {
				
				ERROR = true;
				return;
			}
			if(!stack.push(temp + temp2))
				ERROR = true;
			break;
			
		case "SUB":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();
			
			if(overflowCheck((long)(temp2 - temp))) {
				
				ERROR = true;
				return;
			}
			if(!stack.push(temp2 - temp))
				ERROR = true;
			break;
			
		case "MUL":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();
			
			if(overflowCheck((long)(temp * temp2))) {
				
				ERROR = true;
				return;
			}
			if(!stack.push(temp2 * temp))
				ERROR = true;
			break;
			
		case "DIV":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();
			
			if(temp == 0) {
			
				ERROR = true;
				return;
			}
			
			if(temp2 < 0) {
				
				minus = true;
				temp *= -1;
			}
			else if(temp2 < 0) {

				minus = true;
				temp2 *= -1;
			}
			temp3 = temp2 / temp;
			temp3 *= minus ? -1 : 1;
			
			if(!stack.push(temp3))
				ERROR = true;
			break;
			
		case "MOD":
			
			if(stack.size() < 2) {
				
				ERROR = true;
				return;
			}
			temp = stack.pop();
			temp2 = stack.pop();

			if(temp == 0) {
			
				ERROR = true;
				return;
			}
			
			if(temp < 0) {
				
				temp *= -1;
			}
			else if(temp2 < 0) {

				minus = true;
				temp2 *= -1;
			}
			temp3 = temp2 % temp;
			temp3 *= minus ? -1 : 1;
			
			if(!stack.push(temp3))
				ERROR = true;
			break;
			
		default:
			
			int var = 0;
			int offset = 1;
			
			for(int i = command.length() - 1; i >= 4; i--) {
				
				var += (command.charAt(i) - '0') * offset;
				offset *= 10;
			}
			if(!stack.push(var))
				ERROR = true;
			break;
		}
	}
}

class IStack {

	private long[] container;
	private int MAX_SIZE;
	private int top;

	public IStack() {

		this(1000);
	}

	public IStack(int MAX_SIZE) {

		this.MAX_SIZE = MAX_SIZE;
		container = new long[MAX_SIZE + 1];
		top = 0;
	}

	public int size() {
		
		return this.top;
	}
	
	public boolean isEmpty() {

		return (top == 0);
	}

	public boolean isFull() {

		return (top == MAX_SIZE);
	}
	
	public void clear() {
		
		this.top = 0;
	}

	public boolean push(long item) {

		if (isFull())
			return false;
		container[top++] = item;
		return true;
	}

	public long pop() {

		if (isEmpty())
			return 1000000000 + 1;
		return container[--top];
	}
	
	public long peek() {
		
		if(isEmpty())
			return 1000000000 + 1;
		return container[top - 1];
	}
}

class ILinkedList {

	static class Node {

		String value;
		Node left;
		Node right;

		Node(String value) {

			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	private Node head;
	private Node tail;
	private int size;

	public ILinkedList() {

		this.size = 0;
	}
	
	public void clear() {
		
		head = tail = null;
		size = 0;
	}

	public int size() {

		return this.size;
	}

	public boolean isEmpty() {

		return size == 0;
	}
	public void add(String value) {
		
		add(true, value);
	}

	public void addLast(String value) {

		add(true, value);
	}

	public void addFirst(String value) {

		add(false, value);
	}

	private void add(boolean location, String value) {

		if (head == null) {

			head = new Node(value);
			tail = head;
			size = 1;
			return;
		}

		Node node = new Node(value);
		if (location) {
			
			tail.right = node;
			node.left = tail;
			tail = node;
		} else {

			head.left = node;
			node.right = head;
			head = node;
		}
		size++;
	}

	public String get(int idx) {
		
		int ptr = 0;
		
		for(Node search = head; search != null; search = search.right) {
		
			if(ptr++ == idx)
				return search.value;
		}
		return null;
	}
}