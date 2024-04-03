package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 14238번: 출근 기록
public class BOJ_14238_2 {

    static int[] count = new int[4]; // 1 -> A, 2 -> B, 3 -> C
    static boolean[][][][][] dp = new boolean[51][51][51][4][4];
    static char[] answer;

    // a: 현재까지 사용된 A 개수, b: 현재까지 사용된 B 개수, c: 현재까지 사용된 C 개수
    // back1: 마지막으로 사용한 사람, back2: 마지막에서 두번째로 사용한 사람
    public static boolean dfs(int a, int b, int c, int back1, int back2) {
        if (a == count[1] && b == count[2] && c == count[3]) return true;
        if (dp[a][b][c][back1][back2]) return false;
        dp[a][b][c][back1][back2] = true;

        if (a + 1 <= count[1]) {
            answer[a + b + c + 1] = 'A';
            if (dfs(a + 1, b, c, 1, back1)) return true; // 더 이상 탐색 못하게!
        }

        if (b + 1 <= count[2] && back1 != 2) {
            answer[a + b + c + 1] = 'B';
            if (dfs(a, b + 1, c, 2, back1)) return true;
        }

        if (c + 1 <= count[3] && back1 != 3 && back2 != 3) {
            answer[a + b + c + 1] = 'C';
            if (dfs(a, b, c + 1, 3, back1)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'A') count[1]++;
            else if (ch == 'B') count[2]++;
            else count[3]++;
        }

        answer = new char[str.length() + 1];

        if (dfs(0, 0, 0, 0, 0)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < answer.length; i++) {
                sb.append(answer[i]);
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }
}
