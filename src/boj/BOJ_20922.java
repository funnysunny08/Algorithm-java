package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20922번: 겹치는 건 싫어
public class BOJ_20922 {

    static int N, K;
    static int[] arr;
    static int[] cnt = new int[100001];
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = 0;
        while (start < N && end < N) {
            if (cnt[arr[end]] < K) {
                cnt[arr[end]]++;
                end++;
                answer = Math.max(answer, end - start);
            } else {
                cnt[arr[start]]--;
                start++;
            }
        }
        System.out.println(answer);
    }
}
