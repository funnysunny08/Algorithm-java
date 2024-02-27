package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

// 백준 2661번: 좋은 수열
public class BOJ_2661 {

    static int N;

    public static void backtracking(String number) {
        if (number.length() == N) {
            System.out.println(number);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (check(number + i)) {
                backtracking(number + i);
            }
        }
    }

    public static boolean check(String number) {
        for (int i = 1; i <= number.length() / 2; i++) {
            String num1 = number.substring(number.length() - i);
            String num2 = number.substring(number.length() - i * 2, number.length() - i);
            if (Objects.equals(num1, num2)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        backtracking("");
    }
}
