package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2342번: Dance Dance Revolution
public class BOJ_2342 {

    static int[] move;
    static Integer[][][] dp;

    public static int getDp(int i, int L, int R) {
        if (i == move.length) return 0;
        if (dp[i][L][R] != null) return dp[i][L][R];
        int next = move[i];
        dp[i][L][R] = Math.min(getDp(i + 1, next, R) + getEnergy(L, next),
            getDp(i + 1, L, next) + getEnergy(R, next));
        return dp[i][L][R];
    }

    public static int getEnergy(int prev, int next) {
        if (prev == 0) return 2;
        else if (next == prev) return 1;
        else if (Math.abs(prev - next) == 2) return 4;
        else return 3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        move = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            move[i] = Integer.parseInt(arr[i]);
        }

        dp = new Integer[move.length][5][5];
        System.out.println(getDp(0, 0, 0));

    }
}
