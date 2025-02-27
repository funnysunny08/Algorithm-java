package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14391. 종이 조각
public class BOJ_14391 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int answer = -1;

    private static void dfs(int x, int y, int sum) {
        if (x == N) {
            answer = Math.max(answer, sum);
            return;
        }
        int nx = y == M - 1 ? x + 1 : x;
        int ny = y == M - 1 ? 0 : y + 1;

        if (visit[x][y] || map[x][y] == 0) dfs(nx, ny, sum);

        // 오른쪽으로 이동
        if (!visit[x][y]) {
            int yy = y;
            int newSum = 0;
            for (; yy < M; yy++) {
                if (visit[x][yy]) break;
                visit[x][yy] = true;
                newSum = newSum * 10 + map[x][yy];
                dfs(nx, ny, sum + newSum);
            }
            yy--;
            for (; yy >= y; yy--) visit[x][yy] = false;
        }

        // 아래쪽으로 이동
        if (!visit[x][y]) {
            int xx = x;
            int newSum = 0;
            for (; xx < N; xx++) {
                if (visit[xx][y]) break;
                visit[xx][y] = true;
                newSum = newSum * 10 + map[xx][y];
                dfs(nx, ny, sum + newSum);
            }
            xx--;
            for(; xx >= x; xx--) visit[xx][y] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }
}
