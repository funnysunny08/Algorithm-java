package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9252번: LCS2
public class LCS2 {

    static String str1;
    static String str2;
    static int n1, n2;
    static int[][] dp;

    public static int getLCSLength() {
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
        Stack st = new Stack();

        int i = n1, j = n2;
        while (i > 0 && j > 0) {
            // dp[len1][len2]를 어디서 가져온건지 찾는다!!
            if (dp[i][j] == dp[i - 1][j]) { // 위에서 가져온 값
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) { // 옆에서 가져온 값
                j--;
            } else { // 여기서 갱신된 값
                st.push(str1.charAt(i - 1)); // 현재 위치한 문자 stack push
                i--;
                j--;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        n1 = str1.length();
        n2 = str2.length();
        dp = new int[n1 + 1][n2 + 1];

        System.out.println(getLCSLength());
        System.out.println(getLCS());
    }
}
