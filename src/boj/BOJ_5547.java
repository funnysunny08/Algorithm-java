package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 5547. 일루미네이션
public class BOJ_5547 {
    static int W, H;
    static int[][] map;
    static int[] oddDx = {-1, 0, 1, 1, 0, -1}; // 홀수 행
    static int[] oddDy = {1, 1, 1, 0, -1, 0};
    static int[] evenDx = {-1, 0, 1, 1, 0, -1}; // 짝수 행
    static int[] evenDy = {0, 1, 0, -1, -1, -1};

    static boolean[][][][] visit;
    static int[][] cnt;

    private static int BFS() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = now.x + (now.x % 2 == 0 ? evenDx[i] : oddDx[i]);
                int ny = now.y + (now.x % 2 == 0 ? evenDy[i] : oddDy[i]);
                if (isOut(nx, ny) || visit[nx][ny][now.x][now.y]) continue;
                visit[nx][ny][now.x][now.y] = true;
                if (map[nx][ny] == 1) cnt[nx][ny]++;
                else q.offer(new Node(nx, ny));
            }
        }

        int sum = 0;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) sum += cnt[i][j];
        }
        return sum;
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= H + 2 || y >= W + 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H + 2][W + 2];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[H + 2][W + 2][H + 2][W + 2];
        cnt = new int[H + 2][W + 2];
        System.out.println(BFS());
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
