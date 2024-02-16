package 프로그래머스;

// 프로그래머스: 등굣길
public class 등굣길 {

    static Integer[][] dp;

    public static int run(int i, int j) {
        if (dp[i][j] != null) return dp[i][j];

        dp[i][j] = (run(i, j - 1) + run(i - 1, j)) % 1000000007;
        return dp[i][j];
    }

    public static int solution(int m, int n, int[][] puddles) {
        dp = new Integer[n + 1][m + 1];

        // 초기값
        dp[1][1] = 1;
        for (int i = 0; i <= m; i++) dp[0][i] = 0;
        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1]][puddles[i][0]] = 0;
        }
        return run(n, m);
    }

    public static void main(String[] args) {
        int[][] puddles = {{2, 2}};
        System.out.println(solution(4, 3, puddles));
    }
}
