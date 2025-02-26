package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14391. 종이 조각
public class BOJ_14391 {
    static int N, M;
    static int[][] arr;
    static boolean[][] visit;
    static int answer = Integer.MIN_VALUE;

    private static void dfs(int x, int y, int sum) {
        if (x == N) {
            answer = Math.max(answer, sum);
            return;
        }

        int nx = y == M - 1 ? x + 1 : x;
        int ny = y == M - 1 ? 0 : y + 1;

        if (visit[x][y] || arr[x][y] == 0) dfs(nx, ny, sum);

        // 1. 왼쪽으로 이동
        if (!visit[x][y] && arr[x][y] != 0) {
            int newSum = 0;
            int j = y;
            for (; j < M; j++) {
                if (visit[x][j]) break;
                visit[x][j] = true;
                newSum = newSum * 10 + arr[x][j];
                dfs(nx, ny, sum + newSum);

            }
            j--;
            for (; j >= y; j--) visit[x][j] = false;
        }

        // 2. 오른쪽으로 이동
        if (!visit[x][y] && arr[x][y] != 0) {
            int newSum = 0;
            int i = x;
            for (; i < N; i++) {
                if (visit[i][y]) break;
                newSum = newSum * 10 + arr[i][y];
                visit[i][y] = true;
                dfs(nx, ny, sum + newSum);
            }
            i--;
            for (; i >= x; i--) visit[i][y] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }
}
