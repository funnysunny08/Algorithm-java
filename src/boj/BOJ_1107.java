package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1107. 리모콘
public class BOJ_1107 {
    private static final int MAX = 999999;
    static Set<Integer> exclude = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        exclude = new HashSet<>();
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                exclude.add(Integer.parseInt(st.nextToken()));
            }
        }

        if (N == 100) {
            System.out.println(0);
            System.exit(0);
        }

        int answer = Math.abs(100 - N);

        for (int i = 0; i <= MAX; i++) {
            if (isValid(i)) {
                answer = Math.min(answer, Math.abs(i - N) + String.valueOf(i).length());
            }
        }
        System.out.println(answer);
    }

    private static boolean isValid(int num) {
        if (num == 0) return !exclude.contains(0);
        while (num > 0) {
            if (exclude.contains(num % 10)) return false;
            num /= 10;
        }
        return true;
    }
}
