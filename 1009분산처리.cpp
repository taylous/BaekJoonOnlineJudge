#include<iostream>
using namespace std;

int main() {

	int t, a, b, result;

	cin >> t;
	while (t-- > 0) {

		cin >> a >> b;

		result = a;
		for (int i = 2; i <= b; i += 1)
			result = (result * a) % 10;

		if (result % 10 == 0)
			cout << 10 << endl;
		else
			cout << result % 10 << endl;
	}
	return 0;
}