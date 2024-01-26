package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 16926번: 배열 돌리기 1
public class BOJ_16926 {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = Math.min(N, M) / 2; // 테두리 수
        for (int i = 0; i < R; i++) { // R번 rotate 실행
            for (int j = 0; j < count; j++) { // 테두리 별로 rotate
                int temp = map[j][j];

                for (int k = j + 1; k < M - j; k++) // 좌로 이동
                    map[j][k-1] = map[j][k];

                for (int k = j + 1; k < N - j; k++) // 위로 이동
                    map[k - 1][M - 1 - j] = map[k][M - 1 - j];

                for (int k = M - 2 - j; k >= j; k--) // 우로 이동
                    map[N - 1 - j][k + 1] = map[N - 1 - j][k];

                for (int k = N - 2 - j; k >= j; k--) // 아래로 이동
                    map[k + 1][j] = map[k][j];

                map[j+1][j] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}