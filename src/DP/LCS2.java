package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9252번: LSC 2
public class LCS2 {

    static String str1;
    static String str2;
    static int n1, n2;
    static int[][] dp;

    public static int lcs() {
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    public static String getLCS() {
        Stack<Character> stack = new Stack<>();

        int len1 = n1;
        int len2 = n2;

        while (len1 >= 1 && len2 >= 1) {
            // dp[len1][len2]를 어디서 가져온건지 찾는다!!
            if (dp[len1 - 1][len2] == dp[len1][len2]) { // 위에서 가져옴
                len1--;
            } else if (dp[len1][len2] == dp[len1][len2 - 1]) { // 아래에서 가져옴
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
        int lcs = lcs();
        System.out.println(lcs());
        if (lcs != 0) System.out.println(getLCS());
    }
}
