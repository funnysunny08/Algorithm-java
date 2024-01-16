package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 2240번: 자두나무
public class 자두나무 {

    static int T, W; // T초 동안, W번의 움직임
    static ArrayList<Integer> arr = new ArrayList<>();
    static int[] sum; // 누적합
    static int[][] dp; // dp[i][j] -> i 번 움직일 수 있고, j 초까지 자두가 떨어졌을 때의 경우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int x = -1;
        int cnt = 0;
        int start = -1;
        for (int i = 0; i < T; i++) {
            int v = Integer.parseInt(br.readLine());
            if (i == 0) start = v;
            if (x != v) {
                arr.add(cnt); // 지금까지 count 했던 거 넣어주고
                cnt = 0; // cnt 초기화
                x = v;
            }
            cnt++;
        }
        arr.add(cnt);

        // 홀수 index 끼리의 누적합, 짝수 index 끼리의 누적합
        sum = new int[arr.size()];
        sum[1] = arr.get(1);
        for (int i = 0; i < arr.size(); i++) {
            if (i + 2 >= arr.size()) continue;
            sum[i + 2] = sum[i] + arr.get(i + 2);
        }

        dp = new int[W + 1][arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            if (start == 1) { // 홀수번은 움직이지 않아도 자두 먹을 수 있음!
                if (i % 2 != 0) dp[0][i] = sum[i];
            } else { // 짝수번은 움직이지 않아도 자두 먹을 수 있음!
                if (i % 2 == 0) dp[0][i] = sum[i];
            }
        }

        for (int i = 1; i <= W; i++) { // i 번 움직일 수 있을 때
            for (int j = 1; j < arr.size(); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + arr.get(j));
            }
        }

        if (W == 0) {
            System.out.println(Math.max(sum[arr.size() - 1], sum[arr.size() - 2]));
        } else {
            System.out.println(dp[W][arr.size() - 1]);
        }
    }
}
