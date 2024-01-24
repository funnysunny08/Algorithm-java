package Greedy2;

import java.util.Arrays;

// 프로그래머스: 구명 보트
public class 구명보트 {

    public static int solution(int[] people, int limit) {
        Arrays.sort(people); // 오름차순 정렬

        int answer = 0;
        int low = 0;
        for (int high = people.length - 1; high >= low; high--) {
            if (people[high] + people[low] <= limit) {
                low++;
            }
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        System.out.println(solution(people, 100));
    }
}
