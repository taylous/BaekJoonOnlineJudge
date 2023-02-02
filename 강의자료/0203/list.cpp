#include<iostream>
using namespace std;

// Node라는 Class 생성
class Node {
public:
	Node* next;
	int value;
	Node(int value) {
		this->value = value;
		this->next = NULL;
	}
};
// Node의 가장 첫 번째 위치를 저장하는 변수
Node* head = NULL;

// list에 값을 저장하는 함수
void insert(int value);
// list의 가장 첫 번째 element를 제거하고
// 해당 Node가 가지고 있던 value를 반환하는 함수
int remove();

int main(void) {

	insert(1);
	insert(2);
	insert(3);

	cout << remove() << endl;
	cout << remove() << endl;
	cout << remove() << endl;

	return 0;
}

void insert(int value) {

	Node* node = new Node(value);

	if (head == NULL) {
		head = node;
		return;
	}
	// 자 여기를 같이 한 번 만들어 봅시다.
	Node* search = head;

	while (search->next != NULL) search = search->next;
	search->next = node;
}

int remove() {
	// 자 여기를 같이 한 번 만들어 봅시다.
	Node* node = head;
	head = node->next;
	return node->value;
}