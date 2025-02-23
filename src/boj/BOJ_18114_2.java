package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_18114_2 {
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == C) {
                System.out.println(1);
                System.exit(0);
            }
            hs.add(arr[i]);
        }

        // 2개 조합
        for (int i = 0; i < N; i++) {
            int remain = C - arr[i];
            if (remain == arr[i]) continue;
            if (hs.contains(remain)) {
                System.out.println(1);
                System.exit(0);
            }
        }

        // 3개 조합
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int remain = C - arr[i] - arr[j];
                if (remain == arr[i] || remain == arr[j]) continue;
                if (hs.contains(remain)) {
                    System.out.println(1);
                    System.exit(0);
                }
            }
        }
        System.out.println(0);
    }
}
