package Programmers;
import java.sql.Array;
import java.util.*;
// 프로그래머스 - 평균 구하기
public class Solution_005 {
    public double solution(int[] arr) {
        double answer = Arrays.stream(arr).average().getAsDouble();
        return answer;
    }
}
