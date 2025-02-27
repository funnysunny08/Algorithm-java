package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 5549. 행성탐사
public class BOJ_5549_2 {
    static int N, M;
    static int[][] jungle;
    static int[][] ocean;
    static int[][] ice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jungle = new int[N + 1][M + 1];
        ocean = new int[N + 1][M + 1];
        ice = new int[N + 1][M + 1];

        int K = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                char ch = str.charAt(j - 1);
                jungle[i][j] = jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                ocean[i][j] = ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
                if (ch == 'J') jungle[i][j]++;
                else if (ch == 'O') ocean[i][j]++;
                else ice[i][j]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int j = jungle[c][d] + jungle[a - 1][b - 1] - jungle[c][b - 1] - jungle[a - 1][d];
            int o = ocean[c][d] + ocean[a - 1][b - 1] - ocean[c][b - 1] - ocean[a - 1][d];
            int ii = ice[c][d] + ice[a - 1][b - 1] - ice[c][b - 1] - ice[a - 1][d];
            sb.append(j + " " + o + " " + ii + "\n");
        }
        System.out.println(sb);
    }
}
