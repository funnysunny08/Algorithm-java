package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2573. 빙산
public class BOJ_2573 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    private static void melt() {
        int[][] newMap = copyMap();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (isOut(nx, ny) || map[nx][ny] != 0) continue;
                    newMap[i][j]--;
                }
                if (newMap[i][j] < 0) newMap[i][j] = 0;
            }
        }
        map = newMap;
    }

    private static int[][] copyMap() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) newMap[i][j] = map[i][j];
        }
        return newMap;
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static int check() {
        visit = new boolean[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int sx, int sy) {
        Queue<Node> q = new LinkedList<>();
        visit[sx][sy] = true;
        q.offer(new Node(sx, sy));
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isOut(nx, ny) || visit[nx][ny] || map[nx][ny] == 0) continue;
                visit[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
    }

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

        int time = 0;
        while (true) {
            time++;
            melt();
            int chk = check();
            if (chk >= 2) {
                System.out.println(time);
                System.exit(0);
            } else if (chk <= 0) break;
        }
        System.out.println(0);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
