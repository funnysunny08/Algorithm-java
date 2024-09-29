package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 5549. 행성 탐사
public class BOJ_5549 {
    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        int[][] j_prefix = new int[M + 1][N + 1];
        int[][] o_prefix = new int[M + 1][N + 1];
        int[][] i_prefix = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                char ch = str.charAt(j - 1);
                j_prefix[i][j] = j_prefix[i - 1][j] + j_prefix[i][j - 1] - j_prefix[i - 1][j - 1];
                o_prefix[i][j] = o_prefix[i - 1][j] + o_prefix[i][j - 1] - o_prefix[i - 1][j - 1];
                i_prefix[i][j] = i_prefix[i - 1][j] + i_prefix[i][j - 1] - i_prefix[i - 1][j - 1];
                if (ch == 'J') {
                    j_prefix[i][j]++;
                } else if (ch == 'O') {
                    o_prefix[i][j]++;
                } else {
                    i_prefix[i][j]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(j_prefix[x2][y2] - j_prefix[x2][y1 - 1] - j_prefix[x1 - 1][y2] + j_prefix[x1 - 1][y1 - 1]).append(" ");
            sb.append(o_prefix[x2][y2] - o_prefix[x2][y1 - 1] - o_prefix[x1 - 1][y2] + o_prefix[x1 - 1][y1 - 1]).append(" ");
            sb.append(i_prefix[x2][y2] - i_prefix[x2][y1 - 1] - i_prefix[x1 - 1][y2] + i_prefix[x1 - 1][y1 - 1]).append("\n");
        }
        System.out.println(sb);
    }

}
