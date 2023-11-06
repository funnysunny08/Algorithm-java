package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2512번: 예산
public class 예산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int start = 0;
        int end = -1;
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, arr[i]); // end => 가장 큰 요청 금액
        }

        int m = Integer.parseInt(br.readLine());

        while(start <= end) {
            int mid = (start + end) / 2;
            long budget = 0; // 국가가 줘야 하는 총 금액
            for(int i = 0; i < n; i++) {
                if (arr[i] > mid) budget += mid; // 요청 금액 > 상한액 => 상한액만큼만 줌!
                else budget += arr[i]; // 요청 금액 <= 상한액 => 요청 금액만큼 줌!
            }

            if(budget <= m) { // 줄 수 있음!!!
                start = mid + 1; // 상한액을 늘려보자!
            } else {
                end = mid - 1; // 줄 수 없음 => 상한액 줄이자..
            }
        }
        System.out.println(end);
    }
}
