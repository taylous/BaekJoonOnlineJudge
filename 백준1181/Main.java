package 백준1181단어정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();

        String[] words;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++)
            set.add(br.readLine());

        Iterator<String> it = set.iterator();
        words = new String[set.size()];

        for (int i = 0; i < words.length; i++)
            words[i] = it.next();
        sort(words, 0, words.length - 1);

        for (String word : words) {
            sb.append(word);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int less(String a, String b) {

        if (a.length() < b.length())
            return -1;
        else if (a.length() > b.length())
            return 1;

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) < b.charAt(i))
                return -1;
        }
        return -1;
    }

    private static void exch(String[] arr, int a, int b) {

        String t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    private static void sort(String[] words, int lo, int hi) {

        if (hi <= lo)
            return;

        int lt = lo, i = lo + 1, gt = hi;
        String v = words[lo];

        while (i <= gt) {

            if (less(words[i], v) == -1)
                exch(words, lt++, i++);
            else if (less(words[i], v) == 1)
                exch(words, i, gt--);
            else
                i++;
        }
        sort(words, lo, lt - 1);
        sort(words, gt + 1, hi);
    }
}
