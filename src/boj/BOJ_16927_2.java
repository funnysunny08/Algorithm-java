package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16927. 배열 돌리기 2
public class BOJ_16927_2 {
    static int N, M, R;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static void rotate(int s, int len) {
        int time = R % len;

        while (time-- > 0) {
            int x = s, y = s;
            int start = map[s][s];
            for (int d = 0; d < 4; d++) {
                while (true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < s || ny < s || nx >= N - s || ny >= M - s) break;
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                }
            }
            map[s + 1][s] = start;
        }
    }

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

        int h = N, w = M;
        for (int i = 0; i < Math.min(N, M) / 2; i++) {
            rotate(i, h * 2 + w * 2 - 4);
            h -= 2;
            w -= 2;
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
