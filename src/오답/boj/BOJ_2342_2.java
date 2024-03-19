package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2342번: Dance Dance Revolution
public class BOJ_2342_2 {

    static int[] move;
    static Integer[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        move = new int[str.length];
        for (int i = 1; i < move.length; i++) {
            move[i] = Integer.parseInt(str[i - 1]);
        }
        dp = new Integer[move.length + 1][5][5];

        System.out.println(getDp(1, 0, 0));
    }

    public static int getDp(int idx, int L, int R) {
        if (idx == move.length) return 0;
        if (dp[idx][L][R] != null) return dp[idx][L][R];

        int next = move[idx];
        dp[idx][L][R] = Math.min(getDp(idx + 1, next, R) + getCost(L, next), getDp(idx + 1, L, next) + getCost(R, next));
        return dp[idx][L][R];
    }

    public static int getCost(int prev, int next) {
        if (prev == 0) return 2;
        else if (prev == next) return 1;
        else if (Math.abs(prev - next) == 2) return 4;
        else return 3;
    }

}
