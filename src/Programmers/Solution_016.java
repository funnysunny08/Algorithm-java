package Programmers;
import java.util.*;
// 프로그래머스 - 타겟 넘버
public class Solution_016 {
    int answer = 0;

    public void dfs (int[] numbers, int depth, int target, int sum) {
        if (depth == numbers.length) { // 마지막 노드까지 탐색한 경우
            if (target == sum) answer++;
        } else {
            dfs(numbers, depth + 1, target, sum + numbers[depth]);
            dfs(numbers, depth + 1, target, sum - numbers[depth]);
        }
    }


    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);

        return answer;
    }


}
