package Programmers;
// 프로그래머스 - x만큼 간격이 있는 n개의 숫자
import java.util.*;
public class Solution_006 {
    public List<Long> solution(int x, int n) {
        List<Long> answer = new ArrayList<>();
        for (long i = 0; i < n; i++) {
            answer.add(x * (i + 1));
        }
        return answer;
    }
}
