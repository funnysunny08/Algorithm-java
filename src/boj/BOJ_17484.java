package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17484번: 진우의 달 여행 (Small)
public class BOJ_17484 {

    static int N, M;
    static int[][] map;
    static Integer[][][] dp; // dir: (x, y)에 어떠한 방향으로 도달했는지
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new Integer[N][M][3];
        for (int i = 0; i < M; i++) {
            for (int d = 0; d < 3; d++) {
                dp[0][i][d] = map[0][i];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, getDp(N - 1, i, d));
            }
        }
        System.out.println(answer);
    }

    private static int getDp(int x, int y, int dir) {
        if (dp[x][y][dir] != null) return dp[x][y][dir];
        dp[x][y][dir] = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (dir == i) continue;
            int nx = x - 1;
            int ny = y + dy[i];
            if (isOut(nx, ny)) continue;
            dp[x][y][dir] = Math.min(dp[x][y][dir], map[x][y] + getDp(nx, ny, i));
        }
        return dp[x][y][dir];
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}
