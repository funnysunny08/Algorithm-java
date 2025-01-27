package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2636. 치즈
public class BOJ_2636 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Integer> cnt = new ArrayList<>();

    private static void play() {
        Queue<Node> q;
        boolean[][] visited;
        while (true) {
            int count = count();
            cnt.add(count);
            if (count == 0) break;

            q = new LinkedList<>();
            visited = new boolean[N][M];
            q.offer(new Node(0, 0));
            visited[0][0] = true;
            while (!q.isEmpty()) {
                Node now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                    } else {
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        play();
        System.out.println(cnt.size() - 1);
        if (cnt.size() == 1) System.out.println(0);
        else System.out.println(cnt.get(cnt.size() - 2));
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
