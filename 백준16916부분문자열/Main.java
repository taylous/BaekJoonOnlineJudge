package 백준16916;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String txt = br.readLine();
        String pat = br.readLine();

        KMP kmp = new KMP(pat);

        if (kmp.search(txt) != -1)
            System.out.println("1");
        else
            System.out.println("0");
        br.close();
    }
}

class KMP {

    private final String pat;
    private final int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 26;

        dfa = new int[R][M];
        dfa[pat.charAt(0) - 'a'][0] = 1;

        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];

            int idx = pat.charAt(j) - 'a';
            dfa[idx][j] = j + 1;
            X = dfa[idx][X];
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i) - 'a'][j];
        if (j == M) return i - M;
        return -1;
    }
}