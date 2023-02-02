#include<iostream>
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

	Stack* s = new Stack();
	s->push(10);
	int value = s->pop();

	cout << value << endl;

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