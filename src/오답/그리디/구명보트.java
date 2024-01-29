package 오답.그리디;

import java.util.Arrays;

// 프로그래머스: 구명보트
public class 구명보트 {

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int min = 0; // 현재 가장 가벼운 사람을 가리키는 index
        int max = people.length - 1; // 현재 가장 무거운 사람을 가리키는 index

        int answer = 0;
        while (min <= max) {
            if (people[min] + people[max] <= limit) {
                min++; // min, max 둘 다 태움!
                max--;
            } else {
                max--; // max만 태움!
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
