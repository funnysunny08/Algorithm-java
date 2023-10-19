package Backtracking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1182번 - 부분수열의 합
public class 부분수열의합 {

    static int N, S, answer;
    static int[] arr;

    static void backtracking(int depth, int start, int sum) {
        if (depth > 0 && sum == S) {
            answer++;
        }
        if (start >= N) return;

        for (int i = start; i < N; i++) {
            sum += arr[i];
            backtracking(depth + 1, i + 1, sum);
            sum -= arr[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0, 0);
        System.out.println(answer);
    }

}
