#include<iostream>
using namespace std;

int main() {

	int* cache = new int[41]{ 0, 1, 1 };
	int t, n;

	for (int i = 3; i <= 40; i += 1)
		cache[i] = cache[i - 1] + cache[i - 2];

	cin >> t;

	while (t-- > 0) {

		cin >> n;

		if (n == 0)
			cout << "1 0" << endl;
		else if (n == 1)
			cout << "0 1" << endl;
		else
			cout << cache[n - 1] << " " << cache[n] << endl;
	}
	return 0;
}