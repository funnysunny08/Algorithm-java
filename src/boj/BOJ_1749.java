package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1749번: 점수 따먹기
public class BOJ_1749 {

    static int N, M;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                sum[i][j] = Integer.parseInt(st.nextToken()) + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int r1 = 1; r1 <= N; r1++) {
            for (int r2 = r1; r2 <= N; r2++) {
                for (int c1 = 1; c1 <= M; c1++) {
                    for (int c2 = c1; c2 <= M; c2++) {
                        answer = Math.max(answer, sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1]);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
