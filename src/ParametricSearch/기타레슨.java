package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2343번: 기타 레슨
public class 기타레슨 {

    static int n, m;
    static int[] arr;

    public static boolean check(long size) {
        long sum = 0;
        int cnt = 1;
        for (int i = 0; i < n; i++) {
            if (sum + arr[i] <= size) {
                sum += arr[i];
            } else {
                sum = arr[i];
                cnt++;
            }
        }
        return cnt <= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 기타 레슨의 수
        m = Integer.parseInt(st.nextToken()); // 블루레이의 수

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        long start = 0;
        long end = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, arr[i]);
            end += arr[i];
        }

        // 파라메트릭 서치 => 블루레이의 크기
        long answer = 0;
        while (start <= end) {
            long mid = (start + end) / 2;

            if (check(mid)) { // 해당 블루레이 크기 가능 => 줄여보자!
                end = mid - 1;
                answer = mid;
            } else { // 불가능 => 늘려야 함 ㅠ
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
