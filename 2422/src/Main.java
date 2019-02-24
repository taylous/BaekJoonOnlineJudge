import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static HashSet<String> usedCombinations = new HashSet<>();
    static LinkedList<LinkedList<Integer>> combinationBook = new LinkedList<>();

    static int N;
    static int M;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++)
            combinationBook.add(new LinkedList<>());

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            combinationBook.get(a).add(b);
            combinationBook.get(b).add(a);
        }
        for (int base = 0; base < N; base++) {

            int[] available = new int[N];

            available[base] = 1;
            for(int item : combinationBook.get(base))
                available[item] = -1;
            H_W_I_B_IceCream(available, 1);
        }
        System.out.println(usedCombinations.size());
        br.close();
    }

    static void H_W_I_B_IceCream(int[] available, int select) {

        if (select == 3) {

            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < N; i++) {

                if(available[i] == 1)
                    sb.append(i);
            }
            if(usedCombinations.contains(sb.toString()))
                return;
            usedCombinations.add(sb.toString());
            return;
        }

        for (int combination = 0; combination < N; combination++) {

            if (available[combination] != 0)
                continue;

            int[] copyAvailable = Arrays.copyOf(available, N);

            copyAvailable[combination] = 1;
            for(int item : combinationBook.get(combination))
                copyAvailable[item] = -1;
            H_W_I_B_IceCream(copyAvailable, select + 1);
        }
    }
}