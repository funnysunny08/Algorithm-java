package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 1941. 소문난 칠공주
public class BOJ_1941 {
    static int N = 5;
    static char[][] map = new char[N][N];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] selected = new int[7];
    static int answer;

    private static void comb(int cnt, int start, int s) {
        if (cnt - s > 3) return;
        if (cnt == 7) {
            check(selected[0] / 5, selected[0] % 5);
            return;
        }
        for (int i = start; i < 25; i++) {
            int row = i / 5, col = i % 5;
            selected[cnt] = i;
            comb(cnt + 1, i + 1, map[row][col] == 'S' ? s + 1 : s);
        }
    }

    private static void check(int x, int y) {
        boolean[] visited = new boolean[7];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[0] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int nxt = nx * 5 + ny;
                for (int k = 0; k < 7; k++) {
                    if (!visited[k] && selected[k] == nxt) {
                        visited[k] = true;
                        q.offer(new int[]{nx, ny});
                        cnt++;
                    }
                }
            }
        }
        if (cnt == 7) answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }
        comb(0, 0, 0);
        System.out.println(answer);
    }
}
