package 백준16637괄호추가하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Equation {

    int operand;
    char operator;

    public Equation(int operand, char operator) {
        this.operand = operand;
        this.operator = operator;
    }
}

public class Main {

    private static ArrayList<boolean[]> combinations;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String equation = br.readLine();

        System.out.println(getMaxValue(equation));
        br.close();
    }

    public static void createCombinations(boolean[] check, int idx) {

        if (idx >= N / 2) {
            boolean[] copy = new boolean[N / 2];
            System.arraycopy(check, 0, copy, 0, N / 2);
            combinations.add(copy);
            return;
        }
        createCombinations(check, idx + 1);
        check[idx] = true;
        createCombinations(check, idx + 2);
        check[idx] = false;
    }

    public static int calculate(char[] equations, int start, int end) {

        int value = equations[start] - '0';

        for (int i = start + 1; i < end; i += 2) {

            if (equations[i] == '+') {
                value += equations[i + 1] - '0';
            } else if (equations[i] == '-') {
                value -= equations[i + 1] - '0';
            } else {
                value *= equations[i + 1] - '0';
            }
        }
        return value;
    }

    public static int interpretAnEquation(boolean[] combination, char[] equations) {

        ArrayList<Equation> equationList = new ArrayList<>();
        boolean[] check = new boolean[N];

        int value = 0;
        int idx = 1, size;

        for (boolean flag : combination) {
            if (flag)
                check[idx - 1] = check[idx] = check[idx + 1] = true;
            idx += 2;
        }

        for (int i = 0; i < N; i++) {
            if (check[i]) {

                int endIndex = i;
                while (endIndex != N && check[endIndex]) endIndex += 1;

                int result = calculate(equations, i, endIndex - 1);
                equationList.add(new Equation(result, '\u0000'));

                i = endIndex - 1;
            } else {

                if ('0' <= equations[i] && equations[i] <= '9')
                    equationList.add(new Equation(equations[i] - '0', '\u0000'));
                else
                    equationList.add(new Equation(0, equations[i]));
            }
        }
        size = equationList.size();
        value = equationList.get(0).operand;

        for (int i = 1; i < size; i += 2) {

            if (equationList.get(i).operator == '+') {
                value += equationList.get(i + 1).operand;
            } else if (equationList.get(i).operator == '-') {
                value -= equationList.get(i + 1).operand;
            } else {
                value *= equationList.get(i + 1).operand;
            }
        }
        return value;
    }

    public static int getMaxValue(String equation) {

        char[] equations = equation.toCharArray();
        combinations = new ArrayList<>();
        int maxValue = Integer.MIN_VALUE;

        createCombinations(new boolean[N / 2], 0);

        for (boolean[] combination : combinations)
            maxValue = Math.max(maxValue, interpretAnEquation(combination, equations));
        return maxValue;
    }
}
