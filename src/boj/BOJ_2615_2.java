package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2615. 오목
public class BOJ_2615_2 {
    static int[] dx = {-1, 0, 1, 1};
    static int[] dy = {1, 1, 1, 0};
    static int[][][] dp = new int[21][21][4];
    static int[][] board = new int[21][21];

    private static int getDp(int x, int y, int dir, int color) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (board[nx][ny] == color) {
            return dp[nx][ny][dir] = getDp(nx, ny, dir, color) + 1;
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 1; j <= 19; j++) {
            for (int i = 1; i <= 19; i++) {
                if (board[i][j] != 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        if (dp[i][j][dir] == 0 && getDp(i, j, dir, board[i][j]) == 5) {
                            System.out.println(board[i][j]);
                            System.out.println(i + " " + j);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }
}
