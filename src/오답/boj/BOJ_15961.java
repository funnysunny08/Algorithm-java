package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15961번: 회전 초밥
public class BOJ_15961 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        int[] cnt = new int[d + 1];
        cnt[c] = 1;
        int num = 1;
        for (int i = 0; i < k; i++) {
            if (cnt[arr[i]] == 0) num++;
            cnt[arr[i]]++;
        }
        int max = num;

        for (int i = 1; i < N; i++) {
            // i - 1 번째 제거! i + k 번째 추가!
            cnt[arr[i - 1]]--;
            if (cnt[arr[i - 1]] == 0) num--;

            if (cnt[arr[(i + k - 1) % N]] == 0) num++;
            cnt[arr[(i + k - 1) % N]]++;
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}
