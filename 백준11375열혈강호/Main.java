package 백준11375열혈강호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static ArrayList<ArrayList<Integer>> employees;
    private static boolean[] visited;
    private static int[] match;

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        employees = new ArrayList<>();
        match = new int[M + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++)
            employees.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());

            while (c-- > 0)
                employees.get(i).add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(bipartiteMatching());
        br.close();
    }

    public static boolean dfs(int from) {

        if (visited[from])
            return false;
        visited[from] = true;

        for (int to : employees.get(from)) {

            if (match[to] == 0 || dfs(match[to])) {
                match[to] = from;
                return true;
            }
        }
        return false;
    }

    public static int bipartiteMatching() {

        int ret = 0;

        for (int i = 1; i <= N; i++) {

            if (dfs(i))
                ret += 1;

            Arrays.fill(visited, false);
        }
        return ret;
    }
}
