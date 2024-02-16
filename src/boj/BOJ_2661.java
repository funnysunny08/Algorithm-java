package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

// 백준 2661번:  좋은 수열
public class BOJ_2661 {

    static int N;
    static char[] num = {'1', '2', '3'};

    public static void dfs(String number) {
        if (number.length() == N) {
            System.out.println(number);
            System.exit(0);
        }
        for (int i = 0; i < 3; i++) {
            if (isGood(number + num[i])) dfs(number + num[i]);
        }
    }

    public static boolean isGood(String number) {
        for (int i = 1; i <= number.length() / 2; i++) {
            // 뒤에서 i 만큼 2번 가져옴
            String str1 = number.substring(number.length() - i);
            String str2 = number.substring(number.length() - i * 2, number.length() - i);
            if (Objects.equals(str1, str2)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs( "");
    }
}
