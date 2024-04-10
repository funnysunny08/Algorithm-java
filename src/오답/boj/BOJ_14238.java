package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 14238번: 출근 기록
public class BOJ_14238 {

    static int A, B, C;
    static boolean[][][][][] dp = new boolean[51][51][51][4][4];
    static char[] answer;

    public static boolean dfs(int a, int b, int c, int now, int prev) {
        if (a == A && b == B && c == C)
            return true;

        if (dp[a][b][c][now][prev])
            return false;
        dp[a][b][c][now][prev] = true;

        if (a + 1 <= A) {
            answer[a + b + c] = 'A';
            if (dfs(a + 1, b, c, 1, now)) return true;
        }

        if (b + 1 <= B && now != 2) {
            answer[a + b + c] = 'B';
            if (dfs(a, b + 1, c, 2, now)) return true;
        }

        if (c + 1 <= C && now != 3 && prev != 3) {
            answer[a + b + c] = 'C';
            if (dfs(a, b, c + 1,3, now)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') A++;
            else if (str.charAt(i) == 'B') B++;
            else C++;
        }

        answer = new char[str.length()];
        StringBuilder sb = new StringBuilder();
        if (dfs(0, 0, 0, 0, 0)) {
            for (int i = 0; i < str.length(); i++) sb.append(answer[i]);
        } else sb.append("-1");
        System.out.println(sb);
    }
}
