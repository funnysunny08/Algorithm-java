package 오답.DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 프로그래머스: N으로 표현
public class N으로표현 {

    /*
    * DP => N을 사용한 횟수
    * */

    public static int solution(int N, int number) {
        if (N == number) return 1;

        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        dp.get(1).add(N);

        StringBuilder sb;

        for (int idx = 2; idx <= 8; idx++) {
            sb = new StringBuilder();
            for (int i = 0; i < idx; i++) {
                sb.append(N);
            }
            dp.get(idx).add(Integer.parseInt(sb.toString()));
            for (int i = 1; i < idx; i++) {
                // i, idx - i
                for (int x : dp.get(i)) {
                    for (int y : dp.get(idx - i)) {
                        dp.get(idx).add(x + y);
                        dp.get(idx).add(x - y);
                        dp.get(idx).add(x * y);
                        if (y != 0) {
                            dp.get(idx).add(x / y);
                        }
                    }
                }
            }
            if (dp.get(idx).contains(number)) return idx;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 12));
    }
}
