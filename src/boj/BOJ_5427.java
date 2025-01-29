package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 5427. 불
public class BOJ_5427 {
    static int H, W;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> fireQ;
    static Queue<Node> personQ;
    static boolean[][] visit;

    private static int play() {
        while (!personQ.isEmpty()) {
            int fSize = fireQ.size();
            for (int i = 0; i < fSize; i++) {
                setFire(fireQ.poll());
            }

            int pSize = personQ.size();
            for (int i = 0; i < pSize; i++) {
                int res = move(personQ.poll());
                if (res != -1) return res;
            }
        }
        return -1;
    }

    private static int move(Node node) {
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if (isOut(nx, ny)) return node.time + 1;
            if (visit[nx][ny] || map[nx][ny] != '.') continue;
            map[nx][ny] = '@';
            personQ.offer(new Node(nx, ny, node.time + 1));
        }
        return -1;
    }

    private static void setFire(Node fire) {
        for (int i = 0; i < 4; i++) {
            int nx = fire.x + dx[i];
            int ny = fire.y + dy[i];
            if (isOut(nx, ny) || map[nx][ny] == '#' || map[nx][ny] == '*') continue;
            map[nx][ny] = '*';
            fireQ.offer(new Node(nx, ny, fire.time + 1));
        }
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= H || y >= W;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            fireQ = new LinkedList<>();
            personQ = new LinkedList<>();
            visit = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '*') {
                        fireQ.offer(new Node(i, j, 0));
                    } else if (map[i][j] == '@') {
                        personQ.offer(new Node(i, j, 0));
                        visit[i][j] = true;
                    }
                }
            }
            int result = play();
            sb.append(result == -1 ? "IMPOSSIBLE" : result).append("\n");
        }
        System.out.println(sb);
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
