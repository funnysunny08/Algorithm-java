package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2469. 사다리 타기
public class BOJ_2469_2 {
    static int k, n; // k 세로 줄 개수, n 가로 줄 개수
    static boolean[][] ladder;
    static char[] up;
    static char[] down;
    static int line;
    static String result;

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < k; i++) {
            if (ladder[line][i]) sb.append('-');
            else sb.append('*');
        }
        System.out.println(sb);
    }

    private static void dfs(int i) {
        if (i == k + 1) {
            if (check()) {
                print();
                System.exit(0);
            }
            return;
        }
        dfs(i + 1);
        ladder[line][i] = true;
        dfs(i + 1);
        ladder[line][i] = false;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        ladder = new boolean[n][k + 2];
        result = br.readLine();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (str.charAt(0) == '?') {
                line = i;
                continue;
            }
            for (int j = 1; j < k; j++) {
                if (str.charAt(j - 1) == '-') ladder[i][j] = true;
            }
        }

        up = new char[k + 1];
        down = new char[k + 1];
        findUp();
        findDown();

        dfs(1);
        System.out.println("x".repeat(k - 1));
    }

    private static void findUp() {
        for (int j = 1; j <= k; j++) {
            int idx = j;
            for (int i = 0; i < line; i++) {
                if (ladder[i][idx]) idx++;
                else if (ladder[i][idx - 1]) idx--;
            }
            up[idx] = (char) ('A' + j - 1);
        }
    }

    private static void findDown() {
        for (int j = 1; j <= k; j++) {
            int idx = j;
            for (int i = n - 1; i > line; i--) {
                if (ladder[i][idx]) idx++;
                else if (ladder[i][idx - 1]) idx--;
            }
            down[idx] = result.charAt(j - 1);
        }
    }
}
