package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 17836. 공주님을 구해라
public class BOJ_17836 {
    static int N, M, T;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static class Node {
        int x, y, t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == N - 1 && now.y == M - 1) {
                answer = Math.min(answer, now.t);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                if (map[nx][ny] == 2) { // 그람
                    answer = Math.min(answer, now.t + Math.abs(N - 1 - now.x) + Math.abs(M - 1 - now.y));
                    continue;
                }
                q.offer(new Node(nx, ny, now.t + 1));
            }
        }
        if (answer <= T) {
            System.out.println(answer);
        } else {
            System.out.println("Fail");
        }
    }
}
