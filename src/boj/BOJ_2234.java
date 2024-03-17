package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2234번: 성곽
public class BOJ_2234 {

    static int N, M;
    static int[][] wall;
    static Map<Integer, Integer> area = new HashMap<>();
    static int index = 1;
    static int maxArea = -1;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; // 남, 동, 북, 서
    static int[] dy = {0, 1, 0, -1};
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        wall = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) bfs(i, j);
            }
        }

        System.out.println(index - 1);
        System.out.println(maxArea);

        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j + 1 < M && map[i][j] != map[i][j + 1]) {
                    max = Math.max(max, area.get(map[i][j]) + area.get(map[i][j + 1]));
                }

                if (i + 1 < N && map[i][j] != map[i + 1][j]) {
                    max = Math.max(max, area.get(map[i][j]) + area.get(map[i + 1][j]));
                }
            }
        }
        System.out.println(max);
    }

    public static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        map[x][y] = index;
        int size = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            size++;

            for (int i = 0; i < 4; i++) {
                if (!canGo(now.x, now.y, i)) continue;
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                map[nx][ny] = index;
                q.offer(new Node(nx, ny));
            }
        }
        area.put(index++, size);
        maxArea = Math.max(maxArea, size);
    }

    public static boolean canGo(int x, int y, int idx) {
        if (x + dx[idx] < 0 || y + dy[idx] < 0 || x + dx[idx] >= N || y + dy[idx] >= M || map[x + dx[idx]][y + dy[idx]] != 0) return false;
        String binary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(wall[x][y])));
        return binary.charAt(idx) == '0';
    }
}
