package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1106. 호텔
public class BOJ_1106_3 {
    static int C, N;
    static Promotion[] promotions;
    static int MAX = 9999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        promotions = new Promotion[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            promotions[i] = new Promotion(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] dp = new int[C + 101];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (Promotion promo : promotions) {
            for (int p = promo.customers; p < dp.length; p++) {
                dp[p] = Math.min(dp[p], dp[p - promo.customers] + promo.cost);
            }
        }

        int answer = dp[C];
        for (int i = C + 1; i < dp.length; i++) answer = Math.min(answer, dp[i]);
        System.out.println(answer);
    }

    static class Promotion {
        int cost, customers;

        public Promotion(int cost, int customers) {
            this.cost = cost;
            this.customers = customers;
        }
    }
}

/**
 * 적어도 12명 늘려야 함
 * 비용 고객수
 * 3   5
 * 1   1
 *
 * 2번 12개 => 12명 12원
 * 1번 2개, 2번 2개 => 12명 8원 (선택)
 * 1번 3개 => 15명 9원
 * 1번으로 만들 수 있는 것들 => 3원 5명, 6원 10명, 9명 15명
 * 2번으로 만들 수 있는 것들 => 1원 1명, 2원 2명, 3원 3명, 4원 4명, 5원 5명
 * dp[idx][people] : idx 번째 홍보까지 고려했을 때 people 을 얼마에 만들 수 있는지
 * people 이 홍보의 고객으로 나누어 떨어지는지?
 * people 이 나누어 떨어진다면 min(dp[idx][people], dp[idx - 1][people - 고객 * n] + 비용 * n) n은 최대로 반복??
 */