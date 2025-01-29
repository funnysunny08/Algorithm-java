package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 18405. 경쟁점 전염
public class BOJ_18405 {
    static int N, K;
    static int[][] map;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void play(int time) {
        while (time-- > 0) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                move(q.poll());
            }
            if (q.isEmpty()) return;
        }
    }

    private static void move(Node node) {
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if (isOut(nx, ny) || map[nx][ny] != 0) continue;
            map[nx][ny] = node.num;
            q.offer(new Node(node.num, nx, ny));
        }
    }

    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) continue;
                list.add(new Node(map[i][j], i, j));
            }
        }
        list.sort((o1, o2) -> o1.num - o2.num);
        for (Node node : list) q.offer(node);
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        play(s);
        System.out.println(map[x - 1][y - 1]);
    }

    static class Node {
        int num, x, y;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}
