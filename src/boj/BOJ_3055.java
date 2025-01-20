package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 3055. 탈출
public class BOJ_3055 {
    static int MAX = Integer.MAX_VALUE;
    static int R, C;
    static int[][] waterMap;
    static Queue<Node> waterQ = new LinkedList<>();
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sx, sy; // 출발
    static int tx, ty; // 도착지
    static boolean[][] visit;

    private static void fillWaterMap() {
        while (!waterQ.isEmpty()) {
            Node now = waterQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isOut(nx, ny) || waterMap[nx][ny] != MAX) continue;
                waterMap[nx][ny] = now.time + 1;
                waterQ.offer(new Node(nx, ny, now.time + 1));
            }
        }
    }

    private static void play() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0));
        visit[sx][sy] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx == tx && ny == ty) {
                    System.out.println(now.time + 1);
                    System.exit(0);
                }
                if (isOut(nx, ny) || visit[nx][ny] || waterMap[nx][ny] <= now.time + 1) continue;
                q.offer(new Node(nx, ny, now.time + 1));
                visit[nx][ny] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        waterMap = new int[R][C];
        map = new char[R][C];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(waterMap[i], MAX);

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
                if (ch == 'X') { // 돌
                    waterMap[i][j] = -1;
                } else if (ch == '*') { // 물
                    waterQ.offer(new Node(i, j, 0));
                    waterMap[i][j] = 0;
                } else if (ch == 'D') {
                    waterMap[i][j] = -1;
                    tx = i;
                    ty = j;
                } else if (ch == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }
        fillWaterMap();
        play();
        System.out.println("KAKTUS");
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
