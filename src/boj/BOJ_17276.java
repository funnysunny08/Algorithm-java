package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17276. 배열 돌리기
public class BOJ_17276 {
    static int n, d;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    private static void rotate() {
        int time = Math.abs(d) / 45;
        for (int t = 0; t < time; t++) {
            if (d > 0) rotateRight();
            else rotateLeft();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    private static void rotateRight() { // 시계방향
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
//        X의 주 대각선을 ((1,1), (2,2), …, (n, n)) 가운데 열 ((n+1)/2 번째 열)로 옮긴다.
        for (int i = 0; i < n; i++) map[i][(n + 1) / 2 - 1] = copyMap[i][i];
//        X의 가운데 열을 X의 부 대각선으로 ((n, 1), (n-1, 2), …, (1, n)) 옮긴다.
        for (int i = 0; i < n; i++) map[i][n - i - 1] = copyMap[i][(n + 1) / 2 - 1];
//        X의 부 대각선을 X의 가운데 행 ((n+1)/2번째 행)으로 옮긴다.
        for (int i = 0; i < n; i++) map[(n + 1) / 2 - 1][i] = copyMap[n - i - 1][i];
//        X의 가운데 행을 X의 주 대각선으로 옮긴다.
        for (int i = 0; i < n; i++) map[i][i] = copyMap[(n + 1) / 2 - 1][i];
    }

    private static void rotateLeft() { // 반시계방향
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < n; i++) map[(n + 1) / 2 - 1][i] = copyMap[i][i];
        for (int i = 0; i < n; i++) map[i][i] = copyMap[i][(n + 1) / 2 - 1];
        for (int i = 0; i < n; i++) map[i][(n + 1) / 2 - 1] = copyMap[i][n - i - 1];
        for (int i = 0; i < n; i++) map[n - i - 1][i] = copyMap[(n + 1) / 2 - 1][i];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T ; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //
            rotate();
        }
        System.out.println(sb);
    }
}
