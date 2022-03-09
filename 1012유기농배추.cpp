#include<iostream>
#include<string.h>
using namespace std;


class LinkedList {

private:
	class Node {

	public:
		int x, y;
		Node* next;
		Node(int x, int y) {
			this->x = x;
			this->y = y;
			this->next = NULL;
		}
	};
	Node* head;

public:
	LinkedList() { this->head = NULL; }

	bool is_empty() { return this->head == NULL; }

	void insert(int x, int y) {

		Node* node = new Node(x, y);

		if (this->head == NULL) {

			this->head = node;
			return;
		}
		Node* search = this->head;

		while (search->next != NULL)
			search = search->next;
		search->next = node;
	}

	int* remove() {
		Node* node = this->head;
		this->head = this->head->next;

		int x = node->x;
		int y = node->y;
		delete node;
		return new int[] { x, y };
	}
};
int* lo_x = new int[] { -1, 0, 1, 0 };
int* lo_y = new int[] { 0, 1, 0, -1 };

void clear(int**, bool**, int);
void search_field(int**, bool**, int, int, int, int);


int main() {

	LinkedList* list = new LinkedList();

	bool** visited = new bool*;
	int** field = new int*;
	int t, n, m, k, x, y, answer;

	cin >> t;
	while (t-- > 0) {

		cin >> m >> n >> k;

		answer = 0;
		field = new int* [n];
		visited = new bool* [n];

		for (int row = 0; row < n; row += 1) {
			field[row] = new int[m];
			visited[row] = new bool[m];

			memset(field[row], 0, sizeof(int) * m);
			memset(visited[row], false, sizeof(bool) * m);
		}

		while (k-- > 0) {
			cin >> y >> x;

			field[x][y] = 1;
			list->insert(x, y);
		}

		while (!list->is_empty()) {

			int* location = list->remove();
			x = location[0];
			y = location[1];

			if (visited[x][y] == 0) {

				answer += 1;
				search_field(field, visited, x, y, n, m);
			}
		}
		cout << answer << endl;
	}
	delete list;
	return 0;
}

void clear(int** field, bool** visited, int n) {

	for (int i = 0; i < n; i += 1) {
		delete[] field[i];
		delete[] visited[i];
	}
	delete[] field;
	delete[] visited;
}

void search_field(int** field, bool** visited, int start_x, int start_y, int n, int m) {

	LinkedList* list = new LinkedList();

	visited[start_x][start_y] = true;
	list->insert(start_x, start_y);

	while (!list->is_empty()) {

		int* location = list->remove();
		int x = location[0];
		int y = location[1];

		for (int i = 0; i < 4; i += 1) {

			int nx = x + lo_x[i];
			int ny = y + lo_y[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m)
				continue;
			if (field[nx][ny] == 0 || visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			list->insert(nx, ny);
		}
	}
	delete list;
}
