package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
    static int N;
    static int[] num;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    private static void backtracking(int value, int[] operation, int idx) {
        if (idx == N) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operation[i] > 0) { // 곱하기랑 나눗셈 연산은 바로 계산
                operation[i]--;
                switch (i) {
                    case 0: // +
                        backtracking(value + num[idx], operation, idx + 1);
                        break;
                    case 1: // -
                        backtracking(value - num[idx], operation, idx + 1);
                        break;
                    case 2: // *
                        backtracking(value * num[idx], operation, idx + 1);
                        break;
                    case 3: // /
                        backtracking(value / num[idx], operation, idx + 1);
                        break;
                }
                operation[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
        int[] op = new int[4]; // +, -, *, /
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)  op[i] = Integer.parseInt(st.nextToken());

        backtracking(num[0], op, 1);
        System.out.println(max);
        System.out.println(min);

    }
}
