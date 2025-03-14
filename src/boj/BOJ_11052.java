package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11052. 카드 구매하기
public class BOJ_11052 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        dp[1] = arr[1]; // 1개의 카드를 만들기 위해서는 1번 카드 1개 필요함
        for (int i = 2; i <= N; i++) {
            dp[i] = arr[i];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], arr[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);
    }

    /**
     * 1, 5, 6, 7
     * 4개를 선택해야 하는데 그 합이 최대가 되어야 함
     * 각 카드는 여러개 선택 가능
     * 1번 카드로 4개 => 4
     * 2번 카드로 2개 => 10
     * 3번 카드 1개, 1번 카드 1개 => 7
     * 4번 카드 1개 => 7
     * dp[i] =
     * i개의 카드는 (i - 1)개의 카드에다가 1개를 더하면 됨
     * 혹은 (i - 2)개의 카드에다가 2개를 더하면 됨
     *
     */
}
