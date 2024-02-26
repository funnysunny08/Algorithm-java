package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 10836번: 여왕벌
public class BOJ_10836 {

    static int M, N;
    static int[][] map;
    static int[] dx = {0, -1, -1}; // 왼쪽, 왼쪽 위, 위쪽
    static int[] dy = {-1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            for (int j = M - 1; j > 0; j--) {
                if (zero > 0) {
                    zero--;
                } else if (one > 0) {
                    one--;
                    map[j][0] += 1;
                } else if (two > 0) {
                    two--;
                    map[j][0] += 2;
                }
            }
            for (int j = 0; j < M; j++) {
                if (zero > 0) {
                    zero--;
                } else if (one > 0) {
                    one--;
                    map[0][j] += 1;
                } else if (two > 0) {
                    two--;
                    map[0][j] += 2;
                }
            }
        }
        // 이제 내부 벌들 성장! => 테부리 벌이 모두 성장한 다음에 계산해도 괜찮다!
        for (int r = 1; r < M; r++) {
            for (int c = 1; c < M; c++) {
                map[r][c] = Math.max(map[r][c - 1], Math.max(map[r - 1][c - 1], map[r - 1][c]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                 sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
