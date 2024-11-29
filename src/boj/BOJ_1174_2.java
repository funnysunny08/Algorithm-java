package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 틀림
public class BOJ_1174_2 {

    private static long getNext(long now) {
        if (now == 9876543210L) return -1;
        String str = String.valueOf(now);
        if (now <= 9) return now + 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            // 앞에 있는 수가 나보다 2개 이상 크다면,
            if (i - 1 >= 0) {
                if (Integer.parseInt(String.valueOf(str.charAt(i - 1))) > Integer.parseInt(String.valueOf(str.charAt(i))) + 1) {
                    return now + power(str.length() - i - 1);
                }
            } else {
                if (str.charAt(i) == '9') {
                    return makeNewNumber(str.length(), str.length() + 1);
                } else {
                    return makeNewNumber(str.charAt(i) - '0' + 1, str.length());
                }
            }
            // 앞에 수가 더 이상 없다면, => 현재의 값을 늘리거나, 자릿수를 늘려야 함
        }
        return  -1;
    }

    private static long power(int times) {
        if (times == 0) return 1;
        long result = 1;
        for (int i = 0; i < times; i++) result *= 10;
        return result;
    }

    private static long makeNewNumber(int num, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            sb.append(i);
        }
        return Long.parseLong(String.valueOf(num) + sb.reverse().toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long ans = 0;
        for (int i = 1; i < N; i++) {
            if (ans == -1) {
                System.out.println(-1);
                return;
            }
            ans = getNext(ans);
        }
        // 마지막에서 -1 나온 겨우는 최대값임!
        System.out.println(ans == -1 ? 9876543210L : ans);
    }
}
