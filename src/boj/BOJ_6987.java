package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 6987. 월드컵
public class BOJ_6987 {
    static int[][] result;
    static int[][] combi = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5},
            {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
    static boolean possible;

    private static void solve(int idx, int[][] score) {
        if (idx == 15) {
            if (check(score)) possible = true;
            return;
        }
        int a = combi[idx][0];
        int b = combi[idx][1];
        // 1. a가 이김
        if (score[a][0] + 1 <= result[a][0] && score[b][2] + 1 <= result[b][2]) {
            score[a][0]++;
            score[b][2]++;
            solve(idx + 1, score);
            score[a][0]--;
            score[b][2]--;
        }
        // 2. 무승부
        if (score[a][1] + 1 <= result[a][1] && score[b][1] + 1 <= result[b][1]) {
            score[a][1]++;
            score[b][1]++;
            solve(idx + 1, score);
            score[a][1]--;
            score[b][1]--;
        }
        // 3. b가 이김
        if (score[a][2] + 1 <= result[a][2] && score[b][0] + 1 <= result[b][0]) {
            score[a][2]++;
            score[b][0]++;
            solve(idx + 1, score);
            score[a][2]--;
            score[b][0]--;
        }
    }

    private static boolean check(int[][] score) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (score[i][j] != result[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 4; t++) {
            result = new int[6][3];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            possible = false;
            int[][] score = new int[6][3];
            solve(0, score);
            sb.append(possible ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }
}
