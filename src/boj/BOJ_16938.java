package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 16938. 캠프 준비
public class BOJ_16938 {
    static int N, L, R, X;
    static int[] arr;
    static int answer;

    private static void dfs(int idx, int sum, int diff, int min, int cnt) {
        if (sum > R) return;
        if (idx == N) {
            if (sum >= L && cnt >= 2 && diff >= X) {
                answer++;
            }
            return;
        }
        // 1. 포함 X
        dfs(idx + 1, sum, diff, min, cnt);
        // 2. 포함 O
        if (min == -1) dfs(idx + 1, sum + arr[idx], 0, arr[idx], cnt + 1);
        else dfs(idx + 1, sum + arr[idx], arr[idx] - min, min, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        dfs(0, 0, 0, -1, 0);
        System.out.println(answer);
    }
}
