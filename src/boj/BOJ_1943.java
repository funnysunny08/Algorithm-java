package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 백준 1943번: 동전 분배
public class BOJ_1943 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] info = new int[n][2]; // 동전, 개수
            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                info[i][0] = Integer.parseInt(st.nextToken()); // 동전
                info[i][1] = Integer.parseInt(st.nextToken()); // 동전의 개수
                total += info[i][0] * info[i][1];
            }
            Arrays.sort(info, Comparator.comparingInt(a -> a[0]));
            if (total % 2 != 0) {
                sb.append(0).append("\n");
                continue;
            }
            int target = total / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            int max = 0;
            for (int i = 0; i < n; i++) {
                int m = info[i][0]; // 동전
                max += m * info[i][1]; // 해당 동전으로 얼마까지 만들 수 있는지
                for (int j = 0; j <= target; j++) {
                    if (dp[j] && j + m <= max && j + m <= target) { // j원을 만들 수 있을 때, j + m원을 만들 수 있음!
                        dp[j + m] = true;
                    }
                }
            }

            if (dp[target]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb);
    }
}
