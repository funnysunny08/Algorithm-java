package Programmers;
// 프로그래머스 - 자릿수 더하기
import java.util.*;

public class Solution_002 {
    public int solution(int n) {
        int answer = 0;
        String str = Integer.toString(n);

        for (int i = 0; i < str.length(); i++) {
            answer += Character.getNumericValue(str.charAt(i));
        }

        return answer;
    }

    public int solution2(int n) {
        int answer = 0;

        while(true) {
            answer += n % 10;
            if (n < 10)
                break;

            n = n / 10;
        }
        return answer;
    }
}
