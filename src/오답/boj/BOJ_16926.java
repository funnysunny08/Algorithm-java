package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 16926번: 배열 돌리기 1
public class BOJ_16926 {

    static int N, M ,R;
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

        for (int r = 0; r < R; r++) { // R번 반복!
            for (int t = 0; t < Math.min(N, M) / 2; t++) { // 테두리 별로 반복!
                int startX = t, startY = t;
                int endX = N - t - 1, endY = M - t - 1;
                int temp = map[startX][startY];

                // 1. 좌로 이동
                for (int i = startY + 1; i <= endY; i++) {
                    map[startX][i - 1] = map[startX][i];
                }
                // 2. 위로 이동
                for (int i = startX + 1; i <= endX; i++) {
                    map[i - 1][endY] = map[i][endY];
                }
                // 3. 우로 이동
                for (int i = endY - 1; i >= startY; i--) {
                    map[endX][i + 1] = map[endX][i];
                }
                // 4. 아래로 이동
                for (int i = endX - 1; i >= startX; i--) {
                    map[i + 1][startY] = map[i][startY];
                }
                map[startX + 1][startY] = temp;
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
