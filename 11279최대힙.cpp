#include <iostream>
#include <string>
using namespace std;

#define EMPTY -987654321

class MaxQueue {

private:
	int* table;
	int n;

	void swap(int, int);
	void swim(int);
	void sink(int);

public:
	MaxQueue(int size) {
		this->table = new int[size + 1];
		this->n = 0;
	}

	void insert(int);
	int delMax();
};

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	MaxQueue* mq = new MaxQueue(100000);
	string answers = "";

	int c, n;

	cin >> n;

	while (n-- > 0) {

		cin >> c;

		if (c) {
			mq->insert(c);
		}
		else {
			answers.append(to_string(mq->delMax()));
			answers.append("\n");
		}
	}
	cout << answers << endl;
	return 0;
}

void MaxQueue::insert(int x) {

	this->table[++this->n] = x;
	this->swim(this->n);
}

int MaxQueue::delMax() {

	if (this->n == 0)
		return 0;

	int max = this->table[1];
	this->swap(1, this->n--);

	this->sink(1);
	this->table[this->n + 1] = EMPTY;
	return max;
}

void MaxQueue::swim(int k) {
	while (k > 1 && this->table[k / 2] < this->table[k]) {
		this->swap(k, k / 2);
		k /= 2;
	}
}

void MaxQueue::sink(int k) {
	while (2 * k <= this->n) {
		int j = 2 * k;
		if (j < n && this->table[j] < this->table[j + 1])
			j += 1;
		if (this->table[k] > this->table[j])
			break;

		this->swap(k, j);
		k = j;
	}
}

void MaxQueue::swap(int x, int y) {

	int t = this->table[x];
	this->table[x] = this->table[y];
	this->table[y] = t;
}