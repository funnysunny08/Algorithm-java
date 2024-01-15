package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2281번: 데스노트
public class 데스노트 {

    static int n, m; // n: 사람 수, m: 노트의 가로 길이
    static int[] arr;
    static long[] dp; // dp[x] => arr[x] 를 새로운 줄에 썼을 때 m ~ 해당 줄까지의 빈칸 제곱 값

    public static long solution(int idx) {
        if (dp[idx] != -1) return dp[idx]; // 이미 dp[idx]를 계산한 적이 있음.

        long value = m - arr[idx]; // 값을 채워 넣을 수 있는 남은 빈칸 수
        long temp = value * value + solution(idx + 1);

        // 어디까지 더할 수 있는지
        for (int i = idx + 1; i < n; i++) {
            if (value - arr[i] - 1 < 0) break; // 해당값부터는 현재 줄에 포함시킬 수 없음.

            if (i == n - 1) { // 이때 포함 시키는 값에 n - 1(마지막 값)도 있다면 무조건 0으로..!
                temp = 0;
            } else {
                value = value - arr[i] - 1;
                temp = Math.min(temp, value * value + solution(i + 1));
            }
        }
        return dp[idx] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new long[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;

        System.out.println(solution(0));
    }
}
