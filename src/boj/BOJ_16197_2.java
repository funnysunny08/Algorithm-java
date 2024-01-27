package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16197번: 두 동전
public class BOJ_16197_2 {

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map; // 빈칸: 0, 벽: 1
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static int bfs(Point coin1, Point coin2) {
        Queue<Point[]> q = new LinkedList<>();
        q.add(new Point[]{coin1, coin2});

        int depth = 1;

        while (!q.isEmpty()) {
            if (depth > 10) return -1;

            Point[] coins = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx1 = coins[0].x + dx[i];
                int ny1 = coins[0].y + dy[i];
                int nx2 = coins[1].x + dx[i];
                int ny2 = coins[1].y + dy[i];

                if (isOut(nx1, ny1) != isOut(nx2, ny2)) return depth;

                if (isOut(nx1, ny1) && isOut(nx2, ny2)) continue; // 둘 다 다음칸이 벽

                if (map[nx1][ny1] == 1 && map[nx2][ny2] == 0) { // coin1의 다음칸만 벽.
                    nx1 = coins[0].x;
                    ny1 = coins[0].y;
                } else if (map[nx1][ny1] == 0 && map[nx2][ny2] == 1) { // coin2의 다음칸만 벽.
                    nx2 = coins[1].x;
                    ny2 = coins[1].y;
                }
                q.add(new Point[]{new Point(nx1, ny1), new Point(nx2, ny2)});
                depth++;
            }
        }
        return -1;
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
        List<Point> coins = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if (ch == '#') {
                    map[i][j] = 1;
                } else if (ch == 'o') {
                    coins.add(new Point(i, j));
                }
            }
        }
        System.out.println(bfs(coins.get(0), coins.get(1)));
    }
}
