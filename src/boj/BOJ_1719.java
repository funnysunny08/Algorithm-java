package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1719. 택배
public class BOJ_1719 {
    static int INF = 999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] next = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[x][y] = Math.min(map[x][y], cost);
            map[y][x] = Math.min(map[x][y], cost);
            next[x][y] = y;
            next[y][x] = x;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (map[i][k] == INF || map[k][j] == INF) continue;
                    int dist = map[i][k] + map[k][j];
                    if (map[i][j] > dist) {
                        map[i][j] = dist;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j  = 1; j <= N; j++) {
                if (i == j) sb.append("- ");
                else sb.append(next[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
