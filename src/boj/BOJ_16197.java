package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 16197번: 두 동전
public class BOJ_16197 {

    static int N, M;
    static int[][] map; // 빈칸: 0, 벽: 1
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int x1, int y1, int x2, int y2, int depth) {
        if (depth == 10) return;

        for (int i = 0; i < 4; i++) {
            int nx1 = x1 + dx[i];
            int ny1 = y1 + dy[i];
            int nx2 = x2 + dx[i];
            int ny2 = y2 + dy[i];

            if (isOut(nx1, ny1) != isOut(nx2, ny2)) { // 둘 중 하나만 이탈함.
                answer = Math.min(answer, depth + 1);
                return;
            }
            if (isOut(nx1, ny1) && isOut(nx2, ny2)) continue; // 둘 다 일탈한 경우

            if (map[nx1][ny1] == 0 && map[nx2][ny2] == 0) { // 둘 다 다음칸이 빈캄임.
                dfs(nx1, ny1, nx2, ny2, depth + 1);
            } else if (map[nx1][ny1] == 1 && map[nx2][ny2] == 0) { // coin1의 다음칸만 벽.
                dfs(x1, y1, nx2, ny2, depth + 1);
            } else if (map[nx1][ny1] == 0 && map[nx2][ny2] == 1) { // coin2의 다음칸만 벽.
                dfs(nx1, ny1, x2, y2, depth + 1);
            }
        }
    }

    static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        List<int[]> coins = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if (ch == '#') {
                    map[i][j] = 1;
                } else if (ch == 'o') {
                    coins.add(new int[]{i, j});
                }
            }
        }

        int x1 = coins.get(0)[0], y1 = coins.get(0)[1];
        int x2 = coins.get(1)[0], y2 = coins.get(1)[1];
        dfs(x1, y1, x2, y2, 0);
        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }
}
