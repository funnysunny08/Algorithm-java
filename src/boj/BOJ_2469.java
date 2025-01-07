package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2469. 사다리 타기
public class BOJ_2469 {
    static int k;
    static int n;
    static boolean[][] ladder;
    static char[] result;
    static int line;
    static char[] up;
    static char[] down;

    private static void dfs(int col) {
        if (col >= k) {
            if (check()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < k; i++) {
                    if (ladder[line][i]) sb.append('-');
                    else sb.append('*');
                }
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }
        dfs(col + 1);
        ladder[line][col] = true;
        dfs(col + 1);
        ladder[line][col] = false;
    }

    private static boolean check() {
        for (int i = 1; i <= k; i++) {
            if (ladder[line][i]) {
                if (up[i] != down[i + 1]) return false;
            } else if (ladder[line][i - 1]) {
                if (up[i] != down[i - 1]) return false;
            } else {
                if (up[i] != down[i]) return false;
            }
        }
        return true;
    }

    private static void getUp() {
        for (int i = 1; i <= k; i++) {
            int col = i;
            for (int row = 0; row < line; row++) {
                if (ladder[row][col]) col++;
                else if (ladder[row][col - 1]) col--;
            }
            up[col] = (char) ('A' + (i - 1));
        }
    }

    private static void getDown() {
        for (int i = 1; i <= k; i++) {
            int col = i;
            for (int row = n - 1; row > line; row--) {
                if (ladder[row][col]) col++;
                else if (ladder[row][col - 1]) col--;
            }
            down[col] = result[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        String ans = br.readLine();
        result = new char[k + 1];
        for (int i = 1; i <= k; i++) result[i] = ans.charAt(i - 1);

        ladder = new boolean[n][k + 2];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < k - 1; j++) {
                if (str.charAt(j) == '-') {
                    ladder[i][j + 1] = true;
                } else if (str.charAt(j) == '?') {
                    line = i;
                    break;
                }
            }
        }

        up = new char[k + 1];
        down = new char[k + 1];
        getUp();
        getDown();


        dfs(1);
        System.out.println("x".repeat(k - 1));
    }
}
