import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        ArrayList<String> commands = new ArrayList<>();
        GoStack goStack;
        String input;
        int n;

        while (!(input = br.readLine()).equals("QUIT")) {

            commands.add(input);
            while (!input.equals("END")) {
                input = br.readLine();
                commands.add(input);
            }

            n = Integer.parseInt(br.readLine());

            for (int testCase = 0; testCase < n; testCase++) {

                boolean isError = false;

                goStack = new GoStack();
                goStack.num(Integer.parseInt(br.readLine()));

                for (String command : commands) {

                    if ("POP".equals(command)) {
                        if (goStack.pop() == Integer.MIN_VALUE)
                            isError = true;
                    } else if ("INV".equals(command)) {
                        if (!goStack.inv())
                            isError = true;
                    } else if ("DUP".equals(command)) {
                        if (!goStack.dup())
                            isError = true;
                    } else if ("SWP".equals(command)) {
                        if (!goStack.swp())
                            isError = true;
                    } else if ("ADD".equals(command)) {
                        if (!goStack.add())
                            isError = true;
                    } else if ("SUB".equals(command)) {
                        if (!goStack.sub())
                            isError = true;
                    } else if ("MUL".equals(command)) {
                        if (!goStack.mul())
                            isError = true;
                    } else if ("DIV".equals(command)) {
                        if (!goStack.div())
                            isError = true;
                    } else if ("MOD".equals(command)) {
                        if (!goStack.mod())
                            isError = true;
                    } else {

                        if (command.equals("END"))
                            break;

                        if (!goStack.num(Integer.parseInt(command.split(" ")[1])))
                            isError = true;
                    }

                    if (isError)
                        break;
                }

                if (isError || goStack.size() == 0) {
                    answer.append("ERROR");
                } else if (goStack.size() >= 2) {
                    answer.append("ERROR");
                } else {
                    answer.append(goStack.pop());
                }
                answer.append("\n");
            }
            answer.append("\n");
            br.readLine();

            commands.clear();
        }
        System.out.println(answer);
        br.close();
    }
}

class GoStack {

    private final long[] stack;
    private int topIdx;

    public GoStack() {

        this.stack = new long[1001];
        this.topIdx = 0;
    }

    public int size() {

        return this.topIdx;
    }

    public boolean num(int value) {

        this.stack[this.topIdx++] = value;

        return this.topIdx < 1001;
    }

    public long pop() {

        if (this.topIdx == 0)
            return Integer.MIN_VALUE;

        return this.stack[--this.topIdx];
    }

    public boolean inv() {

        if (this.topIdx == 0)
            return false;
        this.stack[this.topIdx - 1] *= -1;
        return true;
    }

    public boolean dup() {

        if (this.topIdx == 0)
            return false;

        long value = this.stack[this.topIdx - 1];
        this.stack[this.topIdx++] = value;
        return true;
    }

    public boolean swp() {

        if (this.topIdx < 2)
            return false;

        int idx = this.topIdx - 1;
        long temp = this.stack[idx];
        this.stack[idx] = this.stack[idx - 1];
        this.stack[idx - 1] = temp;
        return true;
    }

    public boolean add() {

        if (this.topIdx < 2)
            return false;

        this.topIdx -= 1;
        this.stack[topIdx - 1] += this.stack[this.topIdx];
        return Math.abs(this.stack[this.topIdx - 1]) <= 1000000000;
    }

    public boolean sub() {

        if (this.topIdx < 2)
            return false;

        this.topIdx -= 1;
        this.stack[topIdx - 1] -= this.stack[this.topIdx];
        return Math.abs(this.stack[this.topIdx - 1]) <= 1000000000;
    }

    public boolean mul() {

        if (this.topIdx < 2)
            return false;

        this.topIdx -= 1;
        this.stack[topIdx - 1] *= this.stack[this.topIdx];
        return Math.abs(this.stack[this.topIdx - 1]) <= 1000000000;
    }

    public boolean div() {

        if (this.topIdx < 2)
            return false;
        this.topIdx -= 1;

        boolean isNegative = true;
        long first = this.stack[this.topIdx];
        long second = this.stack[this.topIdx - 1];

        if (first == 0)
            return false;

        if ((first < 0 && second < 0) || (first > 0 && second > 0))
            isNegative = false;

        if (first < 0) {
            first *= -1;
        }
        if (second < 0) {
            second *= -1;
        }

        this.stack[this.topIdx - 1] = second / first;
        this.stack[this.topIdx - 1] *= isNegative ? -1 : 1;

        return Math.abs(this.stack[this.topIdx - 1]) <= 1000000000;
    }

    public boolean mod() {

        if (this.topIdx < 2)
            return false;
        this.topIdx -= 1;

        long first = this.stack[this.topIdx];
        long second = this.stack[this.topIdx - 1];

        if (first == 0)
            return false;

        this.stack[this.topIdx - 1] = second % first;
        return Math.abs(this.stack[this.topIdx - 1]) <= 1000000000;
    }
}