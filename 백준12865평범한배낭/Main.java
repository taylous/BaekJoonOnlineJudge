package 백준12865평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static Item[] items;
    private static int[][] cache;

    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N];
        cache = new int[N][K + 1];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            items[i] = new Item(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        System.out.println(putItemsinBackPack(0, 0));
        br.close();
    }

    static int putItemsinBackPack(int idx, int capacity) {

        if (idx >= N)
            return 0;

        if (cache[idx][capacity] != 0)
            return cache[idx][capacity];

        cache[idx][capacity] = Math.max(cache[idx][capacity],
                putItemsinBackPack(idx + 1, capacity));
        if (capacity + items[idx].weight <= K)
            cache[idx][capacity] = Math.max(cache[idx][capacity],
                    putItemsinBackPack(idx + 1, capacity + items[idx].weight) + items[idx].value);
        return cache[idx][capacity];
    }
}

class Item {

    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}