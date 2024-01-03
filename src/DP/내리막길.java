package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1520번: 내리막길
public class 내리막길 {

    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if (dp[x][y] != -1) { // 이미 탐색해서 경로의 개수가 파악된 지점을 또 탐색하게 되면, 저장된 경로의 개수를 반환
            return dp[x][y];
        }

        // 탐색한 적 없는 지점이라면 탐색 시작!
        dp[x][y] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            if (map[x][y] > map[nx][ny]) {
                dp[x][y] += dfs(nx, ny); // (nx, ny) ~ 목표 지점까지의 경우의 수
            }
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }
}
