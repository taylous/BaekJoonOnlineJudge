package 백준8980택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private static class Town implements Comparable<Town> {
        int from, to, box;

        public Town(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }

        @Override
        public int compareTo(Town other) {
            if (this.to < other.to)
                return -1;
            else if (this.to > other.to)
                return 1;
            return this.from - other.from;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Town> towns = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            towns.add(new Town(from, to, box));
        }
        Collections.sort(towns);
        System.out.println(deliveryBoxes(towns, n, m, c));
        br.close();
    }

    private static int deliveryBoxes(ArrayList<Town> towns, int n, int m, int c) {

        int[] freeSpaces = new int[n + 1];
        int deliveryBoxes = 0, box;

        Arrays.fill(freeSpaces, c);

        for (int index = 0; index < m; index++) {

            Town town = towns.get(index);
            int freeSpace = Integer.MAX_VALUE;

            for (int start = town.from; start < town.to; start++)
                freeSpace = Math.min(freeSpace, freeSpaces[start]);

            box = Math.min(freeSpace, town.box);
            deliveryBoxes += box;

            for (int start = town.from; start < town.to; start++)
                freeSpaces[start] -= box;
        }
        return deliveryBoxes;
    }
}