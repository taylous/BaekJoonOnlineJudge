package 백준17825주사위윷놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static class Node {

        int score;
        int next, blueNext;

        public Node(int score, int next, int blueNext) {
            this.score = score;
            this.next = next;
            this.blueNext = blueNext;
        }
    }

    private static class Horse {

        int position;
        int score;

        public Horse(int position, int score) {
            this.position = position;
            this.score = score;
        }
    }
    private static ArrayList<Integer> dice;
    private static final Node[] game = new Node[33];
    private static int Answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Horse[] horses = new Horse[4];

        dice = new ArrayList<>();
        while (st.hasMoreTokens())
            dice.add(Integer.parseInt(st.nextToken()));
        for (int i = 0; i < 4; i++)
            horses[i] = new Horse(0, 0);

        initializeGame();
        playGame(horses, 0);
        System.out.println(Answer);
        br.close();
    }

    private static void playGame(Horse[] horses, int diceIndex) {

        if (diceIndex == 10) {

            int totalScore = 0;
            for (Horse horse : horses)
                totalScore += horse.score;

            Answer = Math.max(Answer, totalScore);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int move = dice.get(diceIndex);
            int tmpPos = horses[i].position;

            if (game[tmpPos].blueNext != -1) {
                move -= 1;
                tmpPos = game[tmpPos].blueNext;
            }

            while (move-- > 0) {

                tmpPos = game[tmpPos].next;
                if (tmpPos == 32)
                    break;
            }

            if (tmpPos != 32) {

                boolean flag = false;

                for (int j = 0; j < 4; j++) {
                    if (i != j && horses[j].position == tmpPos) {
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
            }
            Horse tmpHorse = horses[i];
            horses[i] = new Horse(tmpPos, tmpHorse.score + game[tmpPos].score);
            playGame(horses, diceIndex + 1);
            horses[i] = new Horse(tmpHorse.position, tmpHorse.score);
        }
    }

    private static void initializeGame() {

        game[0] = new Node(0, 1, -1);
        game[29] = new Node(25, 30, -1);
        game[30] = new Node(30, 31, -1);
        game[31] = new Node(35, 20, -1);
        game[32] = new Node(0, 32, -1);

        int score = 2;
        for (int i = 1; i <= 20; i++) {
            game[i] = new Node(score, i + 1, -1);
            score += 2;
        }
        game[20].next = 32;

        game[5].blueNext = 21;
        game[21] = new Node(13, 22, -1);
        game[22] = new Node(16, 23, -1);
        game[23] = new Node(19, 29, -1);

        game[10].blueNext = 24;
        game[24] = new Node(22, 25, -1);
        game[25] = new Node(24, 29, -1);

        game[15].blueNext = 26;
        game[26] = new Node(28, 27, -1);
        game[27] = new Node(27, 28, -1);
        game[28] = new Node(26, 29, -1);
    }
}
