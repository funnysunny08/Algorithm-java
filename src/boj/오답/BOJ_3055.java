package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 3055. 탈출 (시간 초과)
public class BOJ_3055 {
    static int R, C;
    static char[][] map;
    static int sx, sy;
    static List<Node> water = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int solve() {
        Queue<Node> q = new LinkedList<>();
        for (Node w : water) q.offer(w);

        int time = 0;
        int wSize = water.size();
        int gSize = 1;
        q.offer(new Node(sx, sy));

        while (!q.isEmpty()) {
            time++;
            // 물 먼저 이동!
            int newWaterSize = 0;
            for (int i = 0; i < wSize; i++) {
                Node now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || map[nx][ny] == '*') continue;
                    map[nx][ny] = '*';
                    newWaterSize++;
                    q.offer(new Node(nx, ny));
                }
            }
            wSize = newWaterSize;

            int newGSize = 0;
            for (int i = 0; i < gSize; i++) {
                Node now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (map[nx][ny] == 'D') return time;
                    if (map[nx][ny] != '.') continue;
                    newGSize++;
                    q.offer(new Node(nx, ny));
                }
            }
            gSize = newGSize;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == '*') water.add(new Node(i, j));
            }
        }
        int ans = solve();
        if (ans == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(ans);
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
