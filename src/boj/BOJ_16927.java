package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16927. 배열 돌리기 2
public class BOJ_16927 {
    static int N, M, R;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static void rotate(int s, int len) {
        int times = R % len;

        for (int t = 0; t < times; t++) {
            int dir = 0;
            int x = s, y = s;
            int start = map[s][s];
            while (dir < 4) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= s && ny >= s && nx < N - s && ny < M - s) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                } else dir++;
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

        int nn = N, mm = M;
        for (int s = 0; s < Math.min(N, M) / 2; s++) {
            rotate(s, nn * 2 + mm * 2 - 4);
            nn -= 2;
            mm -= 2;
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
