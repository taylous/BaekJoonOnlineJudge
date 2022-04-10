# include<iostream>
using namespace std;

template <typename T>
class Stack {

private:
	class Node {
		
	public:
		Node* next;
		T value;

		Node(T value) : next(NULL), value(value) {}
	};
	Node* head;
	int len;

public:
	Stack() : head(NULL), len(0) {}

	bool is_empty() {

		return this->len == 0;
	}

	bool size() {

		return this->len;
	}

	void push(T value) {

		Node* node = new Node(value);
		this->len += 1;

		if (this->head == NULL) {
			
			this->head = node;
			return;
		}
		node->next = this->head;
		this->head = node;
	}

	T pop() {

		Node* node = this->head;

		T value = node->value;
		this->head = this->head->next;

		delete node;
		this->len -= 1;
		return value;
	}

	void clear() {

		while (this->head != NULL) {

			Node* node = this->head;
			this->head = this->head->next;
			delete node;
		}
		this->len = 0;
	}

	~Stack() {
		delete this->head;
	}
};

int main(void) {

	Stack<char>* stack = new Stack<char>();
	string parenthesis;
	bool success;
	int t;

	cin >> t;

	while (t-- > 0) {

		cin >> parenthesis;
		success = true;

		for (int i = 0; i < parenthesis.length(); i += 1) {

			char bracket = parenthesis[i];

			if (bracket == '\0')
				break;

			if (bracket == '(') {

				stack->push(bracket);
			}
			else {

				if (stack->is_empty()) {

					success = false;
					break;
				}
				stack->pop();
			}
		}

		if(!success || !(stack->is_empty()))
			cout << "NO" << endl;
		else
			cout << "YES" << endl;
		stack->clear();
	}
	delete stack;
	return 0;
}