package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20922번: 겹치는 건 싫어
public class BOJ_20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] cnt = new int[100001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = 0;
        int answer = -1;
        while (left < N && right < N) {
           if (cnt[arr[right]] < K) {
               cnt[arr[right]]++;
               right++;
               answer = Math.max(answer, right - left);
           } else {
               cnt[arr[left]]--;
               left++;
           }
        }
        System.out.println(answer);
    }
}
