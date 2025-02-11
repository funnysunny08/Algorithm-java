package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1660. 캡틴 이다솜
public class BOJ_1660 {
    static int N;
    static int SIZE = 121;
    static int[] dp1 = new int[SIZE + 1];
    static int[] dp2 = new int[SIZE + 1];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        makeTop();
        int[] res = new int[N + 1];
        Arrays.fill(res, 300000);
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= SIZE; j++) {
                if (dp2[j] > i) break;
                res[i] = Math.min(res[i], res[i - dp2[j]] + 1);
            }
        }
        System.out.println(res[N]);
    }

    private static void makeTop() {
        dp1[1] = 1;
        dp2[1] = 1;
        for (int i = 2; i <= SIZE; i++) {
            dp1[i] = dp1[i - 1] + i;
            dp2[i] = dp2[i - 1] + dp1[i];
        }
    }
}
