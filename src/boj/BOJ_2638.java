package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2638번: 치즈
public class BOJ_2638 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (solution()) {
            result++;
        }
        System.out.println(result);
    }

    public static boolean solution() {
        Stack<int[]> stack = new Stack<>();

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        int[][] tmp = new int[N][M];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                } else {
                    tmp[nx][ny]++;
                    if (tmp[nx][ny] >= 2) stack.push(new int[]{nx, ny});
                }
            }
        }


        if (stack.empty()) return false;
        while (!stack.empty()) {
            int[] coordinate = stack.pop();
            map[coordinate[0]][coordinate[1]] = 0;
        }
        return true;
    }

    public static boolean isMelting(int x, int y) {
//        if (map[x][y] == 0) return false;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (map[x + dx[i]][y + dy[i]] == 0) cnt++;
        }
        return cnt >= 2;
    }

}
