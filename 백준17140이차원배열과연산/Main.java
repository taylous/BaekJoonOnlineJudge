package 백준17140이차원배열과연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Number implements Comparable<Number> {
        int value, count;

        public Number(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Number other) {
            if (this.count < other.count)
                return -1;
            else if (this.count > other.count)
                return 1;
            return this.value - other.value;
        }
    }
    private static int[][] matrix;
    private static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int time = 0;

        matrix = new int[3][3];
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        N = M = 3;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        while (time <= 100) {

            if (r < N && c < M && matrix[r][c] == k)
                break;

            rearrange(N >= M);
            time += 1;
        }
        System.out.println(time <= 100 ? time : -1);
        br.close();
    }

    private static int[] arrangeByLine(int row, int col, int rowOffset, int colOffset) {

        HashMap<Integer, Number> map = new HashMap<>();
        int[] arrangedArr;
        int nx = row, ny = col;
        int idx = 0;

        while(nx < N && ny < M) {

            int value = matrix[nx][ny];
            nx += rowOffset;
            ny += colOffset;

            if (value == 0)
                continue;

            if (map.containsKey(value)) {
                Number number = map.get(value);
                number.count += 1;
                map.replace(value, number);
            } else {
                map.put(value, new Number(value, 1));
            }
        }

        ArrayList<Number> result = new ArrayList<>(map.values());
        arrangedArr = new int[map.size() * 2];
        Collections.sort(result);

        for (Number number : result) {
            arrangedArr[idx++] = number.value;
            arrangedArr[idx++] = number.count;
        }
        return arrangedArr;
    }

    private static void rearrange(boolean isRow) {

        ArrayList<int[]> arrayList = new ArrayList<>();
        int[] temp;
        int maxLen = 0;

        int size = isRow ? N : M;
        int rowOffset = isRow ? 0 : 1;
        int colOffset = isRow ? 1 : 0;

        for (int i = 0; i < size; i++) {

            int row = isRow ? i : 0;
            int col = isRow ? 0 : i;

            temp = arrangeByLine(row, col, rowOffset, colOffset);
            maxLen = Math.max(maxLen, temp.length);
            arrayList.add(temp);
        }

        if (isRow)
            M = maxLen;
        else
            N = maxLen;
        matrix = new int[N][M];

        int row = 0, col = 0;
        for (int[] line : arrayList) {

            temp = line;

            if (isRow) {
                System.arraycopy(temp, 0, matrix[row++], 0, temp.length);
            } else {

                for (row = 0; row < temp.length; row++)
                    matrix[row][col] = temp[row];
                col += 1;
            }
        }
    }
}
