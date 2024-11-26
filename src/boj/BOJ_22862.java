package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22862 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int cnt = 0; // 현재 부분 수열에서의 홀수 개수
        int left = 0;
        int right = 0;
        int max = -1;
        if (arr[0] % 2 != 0) cnt++;
        while (right < N && left < N) {
            if (cnt > K) { // 해당 부분 수열에 홀수 K개 초과 => K개 이하 될 때까지 범위 줄여야 함 => left 이동
                if (arr[left] % 2 != 0) cnt--;
                left++;
            } else { // 범위 늘려도 됨 => right 이동
                max = Math.max(max, right - left + 1 - cnt);
                right++;
                if (right < N && arr[right] % 2 != 0) cnt++;
            }
        }
        System.out.println(max);
    }
}
