package DP;

// 프로그래머스: 정수 삼각형
public class 정수삼각형 {

    public static int solution(int[][] triangle) {
        int rowLength = triangle.length;
        int columnLength = triangle[rowLength - 1].length;
        int[][] dp = new int[rowLength][columnLength];

        dp[0][0] = triangle[0][0];
        for (int i = 1; i < rowLength; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < columnLength; i++) {
            max = Math.max(max, dp[rowLength - 1][i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int answer = solution(triangle);
        System.out.println(answer);
    }
}
