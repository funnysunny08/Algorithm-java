package 오답.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11501번: 주식
public class 주식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        int n;
        int[] arr;
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            //
            long profit = 0;
            int max = arr[n - 1]; // 역방향 탐색
            for (int j = n - 2; j >= 0; j--) {
                if (arr[j] < max) { // 거래하는 게 이득인 상황
                    profit += max - arr[j];
                } else {
                    max = arr[j];
                }
            }

            sb.append(profit).append("\n");
        }
        System.out.println(sb);
    }
}
