package Programmers;
// 프로그래머스 - 약수의 합
public class Solution_004 {
    public int solution(int n) {
        int answer = 0;
        int i = n;
        while(i > 0) {
            if (n % i == 0)
                answer += i;
            i--;
        }
        return answer;
    }
}
