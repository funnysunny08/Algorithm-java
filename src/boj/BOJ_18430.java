package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 18430번: 무기 공학
public class BOJ_18430 {

    static int N, M;
    static int[][] map;
    static long answer = -1;
    static boolean[][] visited;
    static class Boomerang {
        int x, y;
        int x1, y1;
        int x2, y2;
        int sum;

        Boomerang(int x, int y, int x1, int y1, int x2, int y2) {
            this.x = x;
            this.y = y;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.sum = map[x][y] * 2 + map[x1][y1] + map[x2][y2];
        }

        public void makeVisitTrue() {
            visited[x][y] = true;
            visited[x1][y1] = true;
            visited[x2][y2] = true;
        }

        public void makeVisitFalse() {
            visited[x][y] = false;
            visited[x1][y1] = false;
            visited[x2][y2] = false;
        }
    }

    public static void dfs(int x, int y, long sum) {
        int[] next = getNext(x, y);
        if (next == null) { // 모든 노드를 다 탐색한 경우 종료!
            answer = Math.max(answer, sum);
            return;
        }
        if (!visited[x][y]) { // 현재의 노드에 부메랑이 놓여있지 않다.
            for (int i = 0; i < 4; i++) { // 4가지 모양의 부메랑
                Boomerang bm = getBoomerang(x, y, i); // i 모양의 부메랑 가져옴!
                if (bm == null) continue; // i 모양의 부메랑 생성 불가능하면 null 반환
                bm.makeVisitTrue(); // 부메랑 놓음
                dfs(next[0], next[1], sum + bm.sum);
                bm.makeVisitFalse(); // 부메랑 수거
            }
        }
        dfs(next[0], next[1], sum); // 아무것도 안 하고 넘어감
    }

    public static int[] getNext(int x, int y) {
        if (x == N) return null; // 마지막 요소까지 다 탐색한 상황
        else if (y == M - 1) return new int[]{x + 1, 0};
        else return new int[]{x, y + 1};
    }

    public static Boomerang getBoomerang(int x, int y, int idx) {
        int x1 = x, y1 = y;
        int x2 = x, y2 = y;
        switch (idx) {
            case 0:
                y1 -= 1;
                x2 += 1;
                break;
            case 1:
                x1 -= 1;
                y2 -= 1;
                break;
            case 2:
                x1 -= 1;
                y2 += 1;
                break;
            case 3:
                y1 += 1;
                x2 += 1;
                break;
        }
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 >= N || y1 >= M || x2 >= N || y2 >= M || visited[x1][y1] || visited[x2][y2]) {
            return null;
        }
        return new Boomerang(x, y, x1, y1, x2, y2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }
}
