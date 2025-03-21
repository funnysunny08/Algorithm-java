package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 25552. 잔디 예측하기
public class BOJ_25552 {
    static int N, M, D;
    static Queue<Node> q = new LinkedList<>();
    static boolean[][] original;
    static boolean[][] map;
    static boolean[][] visit;

    private static void solve() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = now.x - D; i <= now.x + D; i++) {
                for (int j = now.y - D; j <= now.y + D; j++) {
                    if (i < 0 || j < 0 || i >= N || j >= M || visit[i][j] || Math.abs(i - now.x) + Math.abs(j - now.y) > D) continue;
                    visit[i][j] = true;
                    if (map[i][j]) {
                        original[i][j] = true;
                        q.offer(new Node(i, j, 0));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        original = new boolean[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                original[i][j] = str.charAt(j) == 'O';
                if (original[i][j]) {
                    q.offer(new Node(i, j, 0));
                    visit[i][j] = true;
                }
            }
        }
        D = Integer.parseInt(br.readLine());
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) == 'O';
            }
        }

        solve();
        boolean chk = true;
        L:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                if (map[i][j] != original[i][j]) {
                    chk = false;
                    break L;
                }
        }

        System.out.println(chk ? "YES" : "NO");
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
