package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 1107. 리모콘
public class BOJ_1107 {
    static int MAX = 999999;
    static Set<Integer> exclude = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) exclude.add(Integer.parseInt(st.nextToken()));
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

    private static boolean isValid(int channel) {
        if (channel == 0) return !exclude.contains(0);
        while (channel > 0) {
            if (exclude.contains(channel % 10)) return false;
            channel /= 10;
        }
        return true;
    }
}
