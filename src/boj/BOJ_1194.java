package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1194. 달이 차오른다, 가자.
public class BOJ_1194 {
    static int N, M;
    static char[][] map;
    static int sx, sy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int solve() {
        boolean[][][] visit = new boolean[N][M][64];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0, 0));
        visit[sx][sy][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (map[now.x][now.y] == '1') return now.cost;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isOut(nx, ny) || map[nx][ny] == '#' || visit[nx][ny][now.key]) continue;

                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int nextKey = 1 << (map[nx][ny] - 'a');
                    nextKey = now.key | nextKey;
                    visit[nx][ny][nextKey] = true;
                    q.offer(new Node(nx, ny, now.cost + 1, nextKey));
                } else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if ((now.key & 1 << (map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
                        visit[nx][ny][now.key] = true;
                        q.offer(new Node(nx, ny, now.cost + 1, now.key));
                    }
                } else {
                    visit[nx][ny][now.key] = true;
                    q.offer(new Node(nx, ny, now.cost + 1, now.key));
                }
            }

        }
        return -1;
    }

    private static boolean[] getNewKeys(boolean[] keys, char ch) {
        boolean[] newArr = new boolean[6];
        for (int i = 0; i < 6; i++) {
            newArr[i]  = keys[i];
        }
        newArr[ch - 'a'] = true;
        return newArr;
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    sx = i;
                    sy = j;
                }
            }
        }

        System.out.println(solve());
    }

    static class Node {
        int x, y, cost, key;

        public Node(int x, int y, int cost, int key) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.key = key;
        }
    }
}
