package 백준1043거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    private static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int representation, size, answer;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());

        if (size == 0) {
            answer = m;
        } else {

            HashSet<Integer> exposer = new HashSet<>();
            ArrayList<ArrayList<Integer>> parties = new ArrayList<>();
            parents = new int[n + 1];

            for (int i = 1; i <= n; i++)
                parents[i] = i;

            while (size-- > 0)
                exposer.add(Integer.parseInt(st.nextToken()));

            n = m;
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                size = Integer.parseInt(st.nextToken());

                if (size > 0) {

                    ArrayList<Integer> party = new ArrayList<>();
                    while (size-- > 0)
                        party.add(Integer.parseInt(st.nextToken()));
                    parties.add(party);
                }
            }
            for (ArrayList<Integer> party : parties) {

                representation = party.get(0);
                size = party.size();

                for (int i = 1; i < size; i++)
                    union(representation, party.get(i));
            }
            answer = 0;

            for (ArrayList<Integer> party : parties) {
                boolean isExposer = false;
                for (int id : exposer) {

                    if (getRoot(party.get(0)) == getRoot(id)) {

                        isExposer = true;
                        break;
                    }
                }
                if (!isExposer)
                    answer += 1;
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static int getRoot(int people) {

        if (people == parents[people])
            return people;
        return parents[people] = getRoot(parents[people]);
    }

    private static void union(int sV, int eV) {

        int sP = getRoot(sV);
        int eP = getRoot(eV);

        if (sP != eP)
            parents[eP] = sP;
    }
}
