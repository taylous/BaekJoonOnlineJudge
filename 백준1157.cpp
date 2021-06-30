#include <iostream>
#include <string>
#include <cctype>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	string str;
	int size;

	bool flag = false;
	int maxCount = 0;
	char maxAlphabet = '\u0000';

	int* alphabetCounts = new int[26]{ 0, };
	char* alphabets;

	cin >> str;

	size = str.length();
	alphabets = &str[0];
	
	for (int i = 0; i < size; i += 1) {

		int index = tolower(alphabets[i]) - 'a';
		alphabetCounts[index] += 1;

		if (maxCount < alphabetCounts[index]) {

			maxCount = alphabetCounts[index];
			maxAlphabet = toupper(alphabets[i]);
			flag = true;
		}
		else if (maxCount == alphabetCounts[index]) {

			flag = false;
		}
	}

	cout << (flag ? maxAlphabet : '?') << "\n";
	return 0;
}