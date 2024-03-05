package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2342번: Dance Dance Revolution
public class BOJ_2342 {

    static int[] move;
    static Integer[][][] dp; // left의 현재 위치, right의 현재 위치, cnt

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        move = new int[arr.length - 1];
        for (int i = 0; i < move.length; i++) {
            move[i] = Integer.parseInt(arr[i]);
        }

        dp = new Integer[5][5][move.length];
        System.out.println(solve(0, 0, 0));
    }

    private static int solve(int left, int right, int cnt) {
        if (cnt == move.length) return 0;
        if (dp[left][right][cnt] != null) return dp[left][right][cnt];

        dp[left][right][cnt] = Math.min(solve(move[cnt], right, cnt + 1) + getEnergy(left, move[cnt]),
            solve(left, move[cnt], cnt + 1) + getEnergy(right, move[cnt]));

        return dp[left][right][cnt];
    }

    private static int getEnergy(int prev, int next) {
        int diff = Math.abs(prev - next);
        if (prev == 0) return 2; // 중앙에서 이동
        else if (diff == 0) return 1; // 제자리
        else if (diff == 2) return 4; // 반대편으로 이동
        else return 3; // 인접영역 이동
    }
}
