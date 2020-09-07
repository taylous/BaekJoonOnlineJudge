package 백준2670연속부분최대곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        double[] realNumbers = new double[n];

        for(int i = 0; i < n; i++)
            realNumbers[i] = Double.parseDouble(br.readLine());

        double sum = realNumbers[0];
        double maxRealNum = realNumbers[0];
        double temp;
        int idx = 1;

        while (idx < n - 1) {

            temp = realNumbers[idx] * realNumbers[idx + 1];

            if ((sum * realNumbers[idx]) > temp) {
                sum *= realNumbers[idx];
            } else {
                sum = Math.max(realNumbers[idx], (sum * realNumbers[idx]));
            }
            maxRealNum = Math.max(maxRealNum, sum);
            idx += 1;
        }
        System.out.printf("%.3f%n", maxRealNum);
        br.close();
    }
}
