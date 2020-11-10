package 백준18436수열과쿼리37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        SegmentTree segmentTree = new SegmentTree(n);
        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        segmentTree.init(sequence);
        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {

            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int t = sequence[left - 1];
                sequence[left - 1] = right;

                if (t % 2 == 1 && right % 2 == 0) {
                    segmentTree.update(true, 1, left - 1);
                    segmentTree.update(false, -1, left - 1);
                } else if (t % 2 == 0 && right % 2 == 1) {
                    segmentTree.update(true, -1, left - 1);
                    segmentTree.update(false, 1, left - 1);
                }

            } else if (command == 2) {
                answer.append(segmentTree.travel(left, right, true)).append("\n");
            } else {
                answer.append(segmentTree.travel(left, right, false)).append("\n");
            }
        }
        System.out.println(answer.toString());
        br.close();
    }
}

class SegmentTree {

    private static class Node {

        int even;
        int odd;

        public Node(int even, int odd) {
            this.even = even;
            this.odd = odd;
        }
    }

    private final Node[] segmentArr;
    private final int n;

    SegmentTree(int n) {

        this.n = n;
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));
        int segmentSize = (int) Math.pow(2, x) * 2 - 1;

        segmentArr = new Node[segmentSize];
        for (int i = 0; i < segmentSize; i++)
            segmentArr[i] = new Node(0, 0);
    }

    public void init(int[] sequence) {

        init(sequence, 0, sequence.length - 1, 1, true);
        init(sequence, 0, sequence.length - 1, 1, false);
    }

    public int travel(int a, int b, boolean chk) {

        return travel(0, n - 1, 1, chk, a - 1, b - 1);
    }

    private int travel(int low, int high, int node, boolean chk, int left, int right) {

        if (left > high || right < low)
            return 0;

        if (left <= low && high <= right) {

            if (chk)
                return segmentArr[node].even;
            else
                return segmentArr[node].odd;
        }
        int mid = (low + high) / 2;

        if (chk)
            return travel(low, mid, node * 2, true, left, right) +
                    travel(mid + 1, high, (node * 2) + 1, true, left, right);
        else
            return travel(low, mid, node * 2, false, left, right) +
                    travel(mid + 1, high, (node * 2) + 1, false, left, right);
    }

    private int init(int[] sequence, int low, int high, int node, boolean chk) {

        if (low == high) {

            if (chk)
                return segmentArr[node].even = sequence[low] % 2 == 0 ? 1 : 0;
            else
                return segmentArr[node].odd = sequence[low] % 2 == 1 ? 1 : 0;
        }
        int mid = (low + high) / 2;

        if (chk)
            return segmentArr[node].even += init(sequence, low, mid, node * 2, true) +
                    init(sequence, mid + 1, high, (node * 2) + 1, true);
        else
            return segmentArr[node].odd += init(sequence, low, mid, node * 2, false) +
                    init(sequence, mid + 1, high, (node * 2) + 1, false);
    }

    public void update(boolean chk, int diff, int index) {

        update(1, 0, this.n - 1, index, diff, chk);
    }

    private void update(int node, int low, int high, int index, int diff, boolean chk) {
        if (index < low || index > high) return;

        if (chk)
            segmentArr[node].even = segmentArr[node].even + diff;
        else
            segmentArr[node].odd = segmentArr[node].odd + diff;

        if (low != high) {
            update(node * 2, low, (low + high) / 2, index, diff, chk);
            update(node * 2 + 1, (low + high) / 2 + 1, high, index, diff, chk);
        }
    }
}