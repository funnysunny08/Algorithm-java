package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1189번: 컴백홈
public class BOJ_1189 {

    static int R, C, K;
    static boolean[][] blocked;
    static boolean[][] visited;
    static int answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        blocked = new boolean[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                if (ch == 'T') blocked[i][j] = true; // 지나갈 수 없는 칸!
            }
        }
        visited[R - 1][0] = true;
        dfs(1, R - 1, 0);
        System.out.println(answer);
    }

    private static void dfs(int dist, int r, int c) {
        if (dist > K) return;
        if (r == 0 && c == C - 1) {
            if (dist == K) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || blocked[nr][nc] || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(dist + 1, nr, nc);
            visited[nr][nc] = false;
        }
    }
}
