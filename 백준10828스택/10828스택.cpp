#include<iostream>
#include<string>
#include<sstream>
using namespace std;

class Stack {

private:
	int* table;
	int index;

public:
	Stack() {
		this->table = new int[10000 + 1];
		this->index = -1;
	};
	void push(int);
	int pop();
	int size();
	bool empty();
	int top();
};

int main() {

	Stack stack = {};
	string command, answer = "";
	int n, offset, value;

	cin >> n;

	getchar();
	while (n-- > 0) {

		getline(cin, command);

		offset = command.find(" ");

		if (offset > -1) {

			stack.push(stoi(command.substr(offset + 1)));
		}
		else {

			if (command == "pop") {

				answer.append(to_string(stack.pop()));
			}
			else if (command == "size") {

				answer.append(to_string(stack.size()));
			}
			else if (command == "empty") {

				answer.append(stack.empty() ? "1" : "0");
			}
			else {

				answer.append(to_string(stack.top()));
			}
			answer.append("\n");
		}
	}
	cout << answer << endl;
	return 0;
}


void Stack::push(int value) {

	this->table[++this->index] = value;
}

int Stack::pop() {

	if (this->index == -1)
		return -1;
	return this->table[this->index--];
}

int Stack::size() {
	return this->index + 1;
}

bool Stack::empty() {
	return this->index == -1;
}

int Stack::top() {

	if (this->index == -1)
		return -1;
	return this->table[this->index];
}