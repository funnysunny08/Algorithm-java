package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 5427. 불
public class BOJ_5427 {
    static int N, M;
    static char[][] map;
    static Queue<Node> fireQ;
    static Queue<Node> personQ;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    private static int BFS() {

        int time = 0;
        while (!personQ.isEmpty()) {
            time++;

            int fSize = fireQ.size();
            for (int i = 0; i < fSize; i++) {
                Node now = fireQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (isOut(nx, ny) || map[nx][ny] == '#' || map[nx][ny] == '*') continue;
                    map[nx][ny] = '*';
                    fireQ.offer(new Node(nx, ny));
                }
            }

            int pSize = personQ.size();
            for (int i = 0; i < pSize; i++) {
                Node now = personQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (isOut(nx, ny)) return time;
                    if (map[nx][ny] == '#' || map[nx][ny] == '*' || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    personQ.offer(new Node(nx, ny));
                }
            }
        }
        return -1;
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            fireQ = new LinkedList<>();
            personQ = new LinkedList<>();
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '@') {
                        personQ.offer(new Node(i, j));
                        visited[i][j] = true;
                    }
                    else if (map[i][j] == '*') fireQ.offer(new Node(i, j));
                }
            }
            int ans = BFS();
            sb.append(ans == -1 ? "IMPOSSIBLE" : ans).append("\n");
        }
        System.out.println(sb);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
