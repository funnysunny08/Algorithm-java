package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9252번: LCS 2
public class LCS2_2 {

    static String str1;
    static String str2;
    static int n1, n2;
    static int[][] dp;

    public static String lcs2() {
        for (int i = 1; i <= n1; i++) { // str1
            for (int j = 1; j <= n2; j++) { // str2
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 해당 두 문자를 포함하지 않은 상태에 +1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 여기서 dp[n1][n2]가 lcs 길이!

        Stack<Character> stack = new Stack<>();

        int len1 = n1;
        int len2 = n2;

        while (len1 >= 1 && len2 >= 1) {
            // dp[len1][len2]를 어디서 가져온건지 찾는다!!
            if (dp[len1 - 1][len2] == dp[len1][len2]) { // 위에서 가져옴
                len1--;
            } else if (dp[len1][len2] == dp[len1][len2 - 1]) { // 옆에서 가져옴
                len2--;
            } else { // 여기서 갱신된 값임!
                stack.push(str1.charAt(len1 - 1));
                len1--;
                len2--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        n1 = str1.length();
        n2 = str2.length();
        dp = new int[n1 + 1][n2 + 1];
        String answer = lcs2();
        System.out.println(dp[n1][n2]);
        System.out.println(answer);
    }
}
