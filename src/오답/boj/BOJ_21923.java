package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 21923번: 곡예 비행
public class BOJ_21923 {

    static int N, M;
    static int[][] map;
    static int[][] up = {{1, 0}, {0, -1}}; // 상승해서 왔다면
    static int[][] down = {{-1, 0}, {0, -1}}; // 하강해서 왔다면
    static Integer[][][] dp; // type 0: 상승증, 1: 하강중

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

        dp = new Integer[N][M][2];
        dp[N - 1][0][0] = map[N - 1][0];
        dp[N - 1][0][1] = map[N - 1][0] * 2;
        if (N == 1 && M == 1) {
            System.out.println(map[0][0] * 2);
            return;
        }
        // type 0을 먼저 돌려야 함..
        System.out.println(Math.max(getDp(N-1, M-1, 0) + map[N-1][M-1], getDp(N-1, M-1, 1)));
    }

    private static int getDp(int x, int y, int type) {
        if (x == N - 1 && y == 0 && M == 1 && dp[x][y][type] == Integer.MIN_VALUE) {
            return type == 0 ? map[N - 1][0] : map[N - 1][0] * 2;
        }
        if (dp[x][y][type] != null && M != 1) return dp[x][y][type];

        dp[x][y][type] = Integer.MIN_VALUE;
        if (type == 0) { // 상승중 -> 옆에서 온 애들도 상승중인 애들
            for (int i = 0; i < 2; i++) {
                int nx = x + up[i][0];
                int ny = y + up[i][1];
                if (isOut(nx, ny)) continue;
                dp[x][y][type] = Math.max(dp[x][y][type], getDp(nx, ny, 0) + map[x][y]);
            }
        } else { // 하강중
            // 하강하다 온 경우
            for (int i = 0; i < 2; i++) {
                int nx = x + down[i][0];
                int ny = y + down[i][1];
                if (isOut(nx, ny)) continue;
                dp[x][y][type] = Math.max(dp[x][y][type], getDp(nx, ny, 1) + map[x][y]);
            }

            // 여기서 상승 -> 하강 변경!
            dp[x][y][type] = Math.max(dp[x][y][type], getDp(x, y, 0) + map[x][y]);
        }
        return dp[x][y][type];
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}
