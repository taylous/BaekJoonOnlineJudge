#include<iostream>
#include<stack>
using namespace std;

// stack의 최대 크기를 뜻함
const int MAX_SIZE = 10;

// 10개의 int type의 공간을 가진 배열 생성
int my_stack[10] = { 0, };
// 현재 stack의 element를 가르키는 변수
int top = -1;

// stack에 값을 주입하는 함수
void push(int);
// stack의 top 값을 반환하는 함수
int pop();


int main(void) {

	push(1);
	push(2);
	push(3);
	cout << pop() << endl;
	cout << pop() << endl;
	cout << pop() << endl;

	stack<int> s;

	s.push(10);
	s.push(11);
	s.push(12);

	cout << s.top() << endl;
	s.pop();
	cout << s.top() << endl;
	s.pop();
	cout << s.top() << endl;
	s.pop();

	return 0;
}
/*
* 아래 문제 두 개다 말할 수 있으면 커피 사줌
*/
void push(int value) {
	// 현재 이 함수는 불안정한 상태입니다.
	// 왜 일까요?
	my_stack[++top] = value;
}

int pop() {
	// 현재 이 함수는 불안정한 상태입니다.
	// 왜 일까요?
	return my_stack[top--];
}
