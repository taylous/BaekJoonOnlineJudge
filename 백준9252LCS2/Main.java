package 백준9252LCS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int n1 = str1.length, n2 = str2.length;
        int[][] cache = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1[i - 1] != str2[j - 1]) {
                    cache[i][j] = Math.max(cache[i][j - 1], cache[i - 1][j]);
                } else {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                }
            }
        }
        sb.append(cache[n1][n2]).append("\n");

        while (n1 > 0 && n2 > 0) {

            if (cache[n1][n2] == cache[n1][n2 - 1]) {
                n2 -= 1;
            } else if (cache[n1][n2] == cache[n1 - 1][n2]) {
                n1 -= 1;
            } else {
                n1 -= 1;
                n2 -= 1;
                list.addFirst(str1[n1]);
            }
        }
        for (Character path : list)
            sb.append(path);
        System.out.println(sb.toString());
        br.close();
    }
}