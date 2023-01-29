#include<iostream>
using namespace std;

// ==============================================
// =            function definition             =
// ==============================================
int** init(int, int);
bool seek_dirty_area(int**, int, int, int);
int reverse_direction(int);\
void free(int***, int);
// ==============================================

// 문제에서 명시한 방향에 따른 조정값입니다.
// 0: 북, 1: 동, 2: 남, 3: 서
int loX[] = {-1, 0, 1, 0};
int loY[] = { 0, 1, 0, -1};

int main(void) {

	int rx, ry, d;
	int n, m;
	int clean = 0;
	// 4방향 체크 여부(seek), 검사할 방향(check_d)
	int seek, check_d;

	cin >> n >> m;
	cin >> rx >> ry >> d;

	// 방 상태 정보를 저장하는 2차원 포인터 배열 변수
	int** room = init(n, m);

	while (true) {

		if (room[rx][ry] == 0) {
			room[rx][ry] = -1;
			clean += 1;
		}
		seek = 0;
		check_d = d;

		while(seek != 15) {
			check_d = (check_d == 0 ? 3 : check_d - 1);
			// 청소할 공간이 있는지 확인합니다.
			if (seek_dirty_area(room, rx, ry, check_d)) {
				// 있다면 로봇의 방향을 돌립니다. (d -> check_d)
				d = check_d;
				break;
			}
			d = check_d;
			// 청소한 공간 체크
			seek |= (1 << check_d);
		}

		// seek 값이 15라는건 4방향 모두 탐색했다는 뜻
		if (seek == 15) {
			int reverse_d = reverse_direction(d);
			// 후진했을 때 벽이라면 더 이상 갈 곳이 없으므로 종료
			if (room[rx + loX[reverse_d]][ry + loY[reverse_d]] == 1) {
				break;
			}
			// 후진했을 때 벽이 아니라 공간(청소했든 안했든)이라면 이동
			rx += loX[reverse_d];
			ry += loY[reverse_d];
		}
		// seek 값이 15가 아니라는 뜻으로
		// 청소할 공간이 적어도 하나는 있다는 뜻
		else {
			rx += loX[d];
			ry += loY[d];
		}
	}
	// 청소한 공간 출력
	cout << clean << endl;
	// memory free
	free(&room, n);
	return 0;
}

// 초기화 함수입니다.
// 
// 배열을 N * M 크기만큼 생성하고,
// 값을 입력받아 할당한 2차원 배열을 반환합니다.
int** init(int n, int m) {

	int** matrix = nullptr;
	matrix = new int* [n];
	for (int i = 0; i < n; i += 1) {
		matrix[i] = new int[m];
	}

	for (int i = 0; i < n; i += 1) {
		for (int j = 0; j < m; j += 1)
			cin >> matrix[i][j];
	}
	return matrix;
}

// 해당 구역이 청소하지 않은 공간인지 확인하는 함수입니다.
bool seek_dirty_area(int** room, int rx, int ry, int check_d) {
	int x = rx + loX[check_d];
	int y = ry + loY[check_d];
	return room[x][y] == 0;
}

// 반대방향을 반환하는 함수입니다.
// ex: 0001 XOR 0010 = 0011
int reverse_direction(int current_d) {
	return current_d ^ (1 << 1);
}

// 할당한 memory를 다시 반환하는 함수입니다.
void free(int*** matrix, int n) {

	for (int i = 0; i < n; i += 1)
		delete[] (*matrix)[i];
	delete *matrix;
}
