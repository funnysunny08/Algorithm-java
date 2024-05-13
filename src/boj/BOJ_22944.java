package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 22944번: 죽음의 비
public class BOJ_22944 {

    static int N, H, D;
    static char[][] map;
    static int answer = Integer.MAX_VALUE;
    static int[][] visited;
    static int[] dx = {-1, 1 ,0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, e, u, cnt;

        public Node(int x, int y, int e, int u, int cnt) {
            this.x = x;
            this.y = y;
            this.e = e;
            this.u = u;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int sx = 0, sy = 0;
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        visited = new int[N][N];
        BFS(sx, sy);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void BFS(int sx, int sy) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, H, 0, 0));
        visited[sx][sy] = H;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int ne = now.e, nu = now.u;

                if (map[nx][ny] == 'E') {
                    answer = Math.min(answer, now.cnt + 1);
                    continue;
                }

                if (map[nx][ny] == 'U') nu = D;

                if (nu > 0) nu--;
                else ne--;


                if (ne <= 0) continue;
                if (visited[nx][ny] < ne + nu) {
                    visited[nx][ny] = ne + nu;
                    q.offer(new Node(nx, ny, ne, nu, now.cnt + 1));
                }
            }
        }
    }
}
