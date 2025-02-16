package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2315. 가로등 끄기
public class BOJ_2315 {
    static int N, M;
    static int[] loc, sum;
    static Long[][][] dp;

    private static long getDp(int left, int right, int isLeft) {
        if (left == 1 && right == N) return 0;
        if (dp[left][right][isLeft] != null) return dp[left][right][isLeft];

        int nowLoc = isLeft == 0 ? left : right;
        long result = Long.MAX_VALUE;
        if (left > 1) { // 왼쪽으로 이동
            result = getDp(left - 1, right, 0) + (long) (loc[nowLoc] - loc[left - 1]) * (sum[N] - sum[right] + sum[left - 1]);
        }
        if (right < N) { // 오른쪽으로 이동
            result = Math.min(result,
                    getDp(left, right + 1, 1) + (long) (loc[right + 1] - loc[nowLoc]) * (sum[N] - sum[right] + sum[left - 1]));
        }
        return dp[left][right][isLeft] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        loc = new int[N + 1];
        sum = new int[N + 1];
        dp = new Long[N + 1][N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            loc[i] = Integer.parseInt(st.nextToken()); // 위치 저장
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken()); // 전력소비량의 누적합
        }

        System.out.println(getDp(M, M, 0));
    }
}
