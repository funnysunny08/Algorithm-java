package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 13910번: 개업
public class BOJ_13910 {

    static final int MAX = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 짜장면의 수
        int m = Integer.parseInt(st.nextToken()); // 웍의 개수

        int[] wok = new int[MAX];
        wok[0] = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            wok[Integer.parseInt(st.nextToken())]++;
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i / 2; j++) {
                if (j == i - j && wok[j] >= 2) dp[i] = 1;
                else if (j != i - j && wok[j] > 0 && wok[i - j] > 0) dp[i] = 1;
                else if (dp[j] != MAX && dp[i - j] != MAX) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
        System.out.println(dp[n] == MAX ? -1 : dp[n]);
    }
}
