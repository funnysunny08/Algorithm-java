package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14500번: 테트로미노
public class 테트로미노 {

    static int N, M, answer;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int[] dy = {-1, 1, 0, 0};

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

        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j], visit);
                visit[i][j] = false;
                check(i, j);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt, int sum, boolean[][] visit) {
        if (cnt >= 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny]) {
                continue;
            }

            visit[nx][ny] = true;
            dfs(nx, ny, cnt + 1, sum + map[nx][ny], visit);
            visit[nx][ny] = false;
        }
    }

    static void check(int x, int y) {
        if (x < N - 2 && y < M -1) { // ㅏ
            answer = Math.max(answer, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y + 1]);
        }

        if (x < N - 2 && y > 0) { // ㅓ
            answer = Math.max(answer, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y - 1]);
        }


        if (x < N - 1 && y < M - 2) { // ㅜ
            answer = Math.max(answer, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1]);
        }

        if (x > 0 && y < M - 2) { // ㅗ
            answer = Math.max(answer, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1]);
        }
    }
}
