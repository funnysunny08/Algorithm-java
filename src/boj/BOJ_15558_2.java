package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 15558번: 점프 게임
public class BOJ_15558_2 {

    static int N, K;
    static boolean[][] arr;
    static class Node {
        int x, y; // 현재 위치
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new boolean[2][N];
        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == '1') arr[i][j] = true;
            }
        }
        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.y < now.time) continue;
            if (now.y >= N - 1 || now.y + K >= N) return true; // 클리어 or 클리어 전 칸
            // 1. 한 칸 앞으로
            if (arr[now.x][now.y + 1]) {
                q.offer(new Node(now.x, now.y + 1, now.time + 1));
                arr[now.x][now.y + 1] = false;
            }
            // 2. 한 칸 뒤로
            if (now.y - 1 >= now.time && arr[now.x][now.y - 1]) {
                q.offer(new Node(now.x, now.y - 1, now.time + 1));
                arr[now.x][now.y - 1] = false;
            }
            // 3. 반대편의 i+K 번째 칸으로
            int next = (now.x + 1) % 2;
            if (arr[next][now.y + K]) {
                q.offer(new Node(next, now.y + K, now.time + 1));
                arr[next][now.y + K] = false;
            }
        }
        return false;
    }
}
