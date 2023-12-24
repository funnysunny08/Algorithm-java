package 오답.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 12865번: 평범한 배낭
public class 평범한배낭 {

    /*
    * 구하라 => 배낭에 넣을 수 있는 물건들의 가치의 최댓값
    * */

    static int N; // 물품의 수
    static int K; // 배낭의 최대 무게
    static int[] W;
    static int[] V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = new int[N];
        V = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        //
        int[][] dp = new int[N][K + 1];

        for (int j = 0; j <= K; j++) { // 무게
            for (int i = 0; i < N; i++) { // item index
                if (j == 0) { // 무게가 0일 때는 아무것도 넣지 못함!
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0) {
                    if (j >= W[i]) {
                        dp[i][j] = V[i];
                    }
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if (j >= W[i]) { // 해당 item 을 가방에 넣을 수 있는 상황
                    // 해당 item 을 안 넣었을 때의 가치 vs. 해당 item 을 넣었을 때의 가치
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
                }
            }
        }
        System.out.println(dp[N - 1][K]);
    }
}
