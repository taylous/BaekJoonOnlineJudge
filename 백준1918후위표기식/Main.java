package 백준1918후위표기식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(createPostfix(br.readLine()));
        br.close();
    }

    private static void init(HashMap<Character, Integer> operators) {

        operators.put('(', 0);
        operators.put(')', 0);
        operators.put('+', 1);
        operators.put('-', 1);
        operators.put('*', 2);
        operators.put('/', 2);
    }

    private static String createPostfix(String str) {

        HashMap<Character, Integer> operators = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        char[] equations = str.toCharArray();
        StringBuilder postfix = new StringBuilder();

        init(operators);
        for (char equation : equations) {

            switch (equation) {
                case '(':
                    stack.push(equation);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':

                    while (!stack.isEmpty() && operators.get(stack.peek()) >= operators.get(equation)) {
                        postfix.append(stack.pop());
                    }
                    stack.push(equation);
                    break;
                case ')':

                    while (!stack.isEmpty()) {
                        char e = stack.pop();
                        if (e == '(')
                            break;
                        postfix.append(e);
                    }
                    break;
                default:
                    postfix.append(equation);
            }
        }
        while (!stack.isEmpty())
            postfix.append(stack.pop());
        return postfix.toString();
    }
}