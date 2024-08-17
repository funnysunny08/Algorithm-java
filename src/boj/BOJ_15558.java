package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15558번: 점프 게임
public class BOJ_15558 {

    static int N, K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[2][N];
        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(dfs(0, 0, 0) ? 1 : 0);
    }

    private static boolean dfs(int x, int y, int time) {
        if (y < time) return false;
        if (y >= N - 1) return true; // 클리어 or 클리어 바로 직전 칸
        // 현재 갈 수 있는 칸 탐색
        // 1. 한 칸 앞으로
        if (arr[x][y + 1] == 1) {
            if (dfs(x, y + 1, time + 1)) return true;
        }
        // 2. 한 칸 뒤로 (현재 1초면, 0번째 칸 못 감.)
        if (y - 1 >= time && y > 0 && arr[x][y - 1] == 1) {
            if (dfs(x, y - 1, time + 1)) return true;
        }
        // 3. 반대편의 i+K 번째 칸으로
        if (y + K >= N) return true;
        int next = (x + 1) % 2;
        if (arr[next][y + K] == 1) {
            if (dfs(next, y + K, time + 1)) return true;
        }
        return false;
    }

}
