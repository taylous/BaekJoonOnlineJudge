package 백준1967트리의지름;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static ArrayList<ArrayList<Location>> adjacent;
    static boolean[] visited;

    static Location MaxLocation;
    static int Max;
    static int N;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int x, y, w;

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(Max);
            return;
        }

        adjacent = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            adjacent.add(new ArrayList<>());
        visited = new boolean[N + 1];

        for (int i = 1; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            adjacent.get(x).add(new Location(y, w));
            adjacent.get(y).add(new Location(x, w));
        }

        for (Location location : adjacent.get(1)) {

            visited[1] = true;
            TheDiameterOfTheTree(location, location.weight);
            visited[1] = false;
        }
        TheDiameterOfTheTree(MaxLocation, 0);
        System.out.println(Max);
        br.close();
    }

    static void TheDiameterOfTheTree(Location location, int totalWeight) {

        visited[location.there] = true;

        for (Location t : adjacent.get(location.there)) {
            if (!visited[t.there]) {
                visited[t.there] = true;
                TheDiameterOfTheTree(t, totalWeight + t.weight);
                visited[t.there] = false;
            }
        }
        if (totalWeight > Max) {

            MaxLocation = location;
            Max = totalWeight;
        }
        visited[location.there] = false;
    }
}

class Location {

    int there;
    int weight;

    Location(int there, int weight) {

        this.there = there;
        this.weight = weight;
    }
}