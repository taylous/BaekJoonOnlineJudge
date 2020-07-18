package 백준11004K번째수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] src = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            src[i] = Integer.parseInt(st.nextToken());
        System.out.println(kthSmallest(src, 0, n - 1, k - 1));
        br.close();
    }

    public static int partition(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        swap(arr, left, mid);

        int pivot = arr[left];
        int i = left, j = right;

        while (i < j) {
            while (pivot < arr[j]) j--;
            while (i < j && pivot >= arr[i]) i++;
            swap(arr, i, j);
        }
        arr[left] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    public static int kthSmallest(int[] arr, int low, int high, int k) {

        int partition = partition(arr, low, high);

        if (partition == k)
            return arr[partition];
        else if (partition < k)
            return kthSmallest(arr, partition + 1, high, k);
        else
            return kthSmallest(arr, low, partition - 1, k);
    }
}
