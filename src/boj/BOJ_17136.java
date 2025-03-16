package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17136. 색종이
public class BOJ_17136 {
    static int N = 10;
    static int[][] map = new int[N][N];
    static int[] cnt = new int[6];
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visited = new boolean[N][N];

    private static void dfs(int loc, int count) {
        if (answer <= count) return;
        if (loc == 100) {
            answer = count;
            return;
        }

        int x = loc / N;
        int y = loc % N;
        if (map[x][y] == 0 || visited[x][y]) dfs(loc + 1, count); // 바로 넘어감

        for (int size = 5; size >= 1; size--) { // 1 ~ 5 크기의 색종이
            if (cnt[size] >= 5) continue; // 해당 색종이 이미 다 씀
            // 해당 사이즈로 채울 수 있는지 확인
            if (check(x, y, size)) {
                fill(x, y, size, true);
                cnt[size]++;
                dfs(loc + 1, count + 1);
                fill(x, y, size, false);
                cnt[size]--;
            }
        }

    }

    private static void fill(int x, int y, int size, boolean value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = value;
            }
        }
    }

    private static boolean check(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= N || j >= N || map[i][j] == 0 || visited[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }
}
