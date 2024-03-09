package 프로그래머스;

// 프로그래머스: 3 x n 타일링
public class XN타일링 {

    static long[] dp;

    public static long solution(int n) {
        int mod = 1000000007;
        dp = new long[n + 1];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
            dp[i] = dp[i] % mod;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
