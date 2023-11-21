package DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 프로그래머스: N으로 표현
public class N으로표현 {

    public static int solution(int N, int number) {
        if (N == number) {
            return 1;
        }
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) { // 1부터 8까지만 담을거임!
            dp.add(new HashSet<>());
        }

        dp.get(1).add(N); // 해당 숫자 한 번만 사용한 경우

        for (int i = 2; i <= 8; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < i; j++) { // N이 i개인 숫자
                sb.append(N);
            }
            dp.get(i).add(Integer.parseInt(sb.toString()));

            for (int j = 1; j < i; j++) {
                // j, i -j
                int idx1 = j;
                int idx2 = i - j;
                for (int num1 : dp.get(idx1)) {
                    for (int num2 : dp.get(idx2)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num2 - num1);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                        if (num1 != 0) {
                            dp.get(i).add(num2 / num1);
                        }
                    }
                }
            }
            if (dp.get(i).contains(number)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 11));
    }
}
