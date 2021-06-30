#include <iostream>
#include <sstream>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	string str;
	int winner = 0, maxScore = 0;

	for (int id = 1; id <= 5; id++) {
		getline(cin, str);

		stringstream st(str);
		string token;
		int score = 0;

		while (getline(st, token, ' '))
			score += stoi(token);

		if (maxScore < score) {

			winner = id;
			maxScore = score;
		}
	}

	cout << winner << " " << maxScore << "\n";
	return 0;
}