package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1303. 전쟁 - 전투
public class BOJ_1303 {
    static int N, M;
    static char[][] map;
    static boolean[][] visit;
    static int w, b;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void play() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 'W') w += getCount(i, j, 'W');
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 'B') b += getCount(i, j, 'B');
            }
        }
    }

    private static int getCount(int x, int y, char ch) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visit[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 범위 체크
                if (visit[nx][ny] || map[nx][ny] != ch) continue; // 방문 여부 및 캐릭터 체크
                cnt++;
                visit[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
        return cnt * cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }
        play();
        System.out.println(w + " " + b);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
