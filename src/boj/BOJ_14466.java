package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14466번: 소가 길을 건너간 이유 6
public class BOJ_14466 {

    static int N, K, R;
    static int[][][][] map;
    static int[][] cows;
    static int[] select;
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void dfs(int depth, int start) {
        if (depth == 2) {
            if (!check(cows[select[0]][0], cows[select[0]][1], cows[select[1]][0], cows[select[1]][1])) answer++;
            return;
        }
        for (int i = start; i < K; i++) {
            select[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public static boolean check(int x1, int y1, int x2, int y2) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.add(new int[]{x1, y1});
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == x2 && now[1] == y2) return true;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > N || visited[nx][ny]) continue;

                if (map[now[0]][now[1]][nx][ny] == 1) continue;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1][N + 1][N + 1];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            map[r1][c1][r2][c2] = 1;
            map[r2][c2][r1][c1] = 1;
        }

        cows = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }

        select = new int[2];
        dfs(0, 0);
        System.out.println(answer);
    }
}
