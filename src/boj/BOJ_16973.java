package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 16973. 직사각형 탈출
public class BOJ_16973 {
    static int N, M, H, W, sx, sy, ex, ey;
    static int[][] map, sum;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0}; // 위, 오른쪽, 아래, 왼쪽
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;

    private static int BFS() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0));
        visit[sx][sy] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == ex && now.y == ey) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isOut(nx, ny) || visit[nx][ny]) continue;

                int s = sum[nx + H - 1][ny + W - 1] - sum[nx + H - 1][ny - 1] - sum[nx - 1][ny + W - 1] + sum[nx - 1][ny - 1];
                if (s == 0) {
                    visit[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.cnt + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isOut(int x, int y) {
        return x < 1 || y < 1 || x + H - 1 > N || y + W - 1 > M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        for (int i = 1 ; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = map[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        System.out.println(BFS());
    }

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
