package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// BOJ_2665
public class BOJ_2665 {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int solve() {
        int answer = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        int[][] visited = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        q.offer(new Node(0, 0, 0));
        visited[0][0] = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == N - 1 && now.y == N - 1) answer = Math.min(answer, now.cost);
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int newCost = now.cost + (map[nx][ny] == 1 ? 0 : 1);
                if (visited[nx][ny] > newCost) {
                    visited[nx][ny] = newCost;
                    q.offer(new Node(nx, ny, newCost));
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(solve());
    }

    static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
