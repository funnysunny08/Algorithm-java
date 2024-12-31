package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 9095. 1, 2, 3 더하기
public class BOJ_9095 {
    static int answer;
    private static void func(int num) {
        if (num == 0) {
            answer++;
            return;
        }
        if (num >= 3) func(num - 3);
        if (num >= 2) func(num - 2);
        func(num - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int target = Integer.parseInt(br.readLine());
            answer = 0;
            func(target);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
