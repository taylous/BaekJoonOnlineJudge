#include<iostream>
using namespace std;

class LinkedList {

private:
	class Node {

	public:
		int value;
		Node* prev;
		Node* next;

		Node(int value) {

			this->value = value;
			this->next = this->prev = NULL;
		}
	};
	Node* head;
	Node* tail;
	int len;

public:

	LinkedList() {
		this->head = NULL;
		this->tail = NULL;
		this->len = 0;
	}

	int size() {
		return this->len;
	}

	void insert(int value) {

		Node* node = new Node(value);
		this->len += 1;

		if (this->head == NULL) {

			this->head = this->tail = node;
			return;
		}
		this->tail->next = node;
		node->prev = this->tail;
		this->tail = node;
	}

	void remove_head() {

		Node* temp = this->head;

		this->head = this->head->next;

		if(this->head != NULL)
			this->head->prev = NULL;
		this->len -= 1;

		delete temp;
	}

	int find_index(int value) {

		Node* search = this->head;
		int index = 0;

		while (search->next != NULL && search->value != value) {

			search = search->next;
			index += 1;
		}

		if (search->value != value)
			return -1;
		return index;
	}

	void move_left() {

		Node* node = this->head;

		this->head = this->head->next;
		this->head->prev = NULL;

		this->tail->next = node;
		node->prev = this->tail;
		node->next = NULL;

		this->tail = node;
	}

	void move_right() {

		Node* node = this->tail;

		this->tail = this->tail->prev;
		this->tail->next = NULL;

		this->head->prev = node;
		node->next = this->head;
		node->prev = NULL;

		this->head = node;
	}
};
void create_list(LinkedList*, int);

int main() {

	LinkedList* list = new LinkedList;
	int n, m, value, count = 0;
	int left_count, right_count;

	cin >> n >> m;
	create_list(list, n);

	while (m-- > 0) {

		cin >> value;
		left_count = list->find_index(value);

		if (left_count >= 0) {

			right_count = list->size() - left_count;
			
			if (left_count <= right_count) {

				count += left_count;
				while (left_count-- > 0)
					list->move_left();
			}
			else {

				count += right_count;
				while (right_count-- > 0)
					list->move_right();
			}
			list->remove_head();
		}
	}
	cout << count << endl;
	delete list;
	return 0;
}

void create_list(LinkedList* list, int n) {

	for (int value = 1; value <= n; value += 1) {
		list->insert(value);
	}
}
