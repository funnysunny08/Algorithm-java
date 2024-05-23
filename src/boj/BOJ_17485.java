package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17485번: 진우의 달 여행 (Large)
public class BOJ_17485 {

    static int N, M;
    static int[][] map;
    static Integer[][][] dp;
    static int[] dx = {-1, -1, -1};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new Integer[N][M][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < M; y++) {
            for (int d = 0; d < 3; d++) {
                dp[0][y][d] = map[0][y];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int y = 0; y < M; y++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, getDP(N - 1, y, d));
            }
        }
        System.out.println(answer);
    }

    private static int getDP(int x, int y, int dir) {
        if (dp[x][y][dir] != null) return dp[x][y][dir];

        dp[x][y][dir] = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (dir == i) continue;
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOut(nx, ny)) continue;
            dp[x][y][dir] = Math.min(dp[x][y][dir], map[x][y] + getDP(nx, ny, i));
        }
        return dp[x][y][dir];
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}
