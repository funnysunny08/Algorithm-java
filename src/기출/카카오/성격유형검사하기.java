package 기출.카카오;

import java.util.HashMap;

// 2022 KAKAO TECH INTERNSHIP: 성격 유형 검사하기
public class 성격유형검사하기 {

    public static String solution(String[] survey, int[] choices) {
        String answer = "";
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == 4) continue; // 모르겠음을 대답한 경우 넘어감
            if (choices[i] < 4) { // 비동의 쪽으로, survey의 첫번째 항목에 점수를 줌
                map.put(survey[i].charAt(0), map.getOrDefault(survey[i].charAt(0), 0) + 4 - choices[i]);
            } else { // 동의 쪽으로, survey의 두번째 항목에 점수를 줌. 점수는 choices[i] - 4하면 될 듯
                survey[i].charAt(1);
                map.put(survey[i].charAt(1), map.getOrDefault(survey[i].charAt(1), 0) + choices[i] - 4);
            }
        }

        if (map.getOrDefault('R', 0) >= map.getOrDefault('T', 0)) {
            answer += 'R';
        } else {
            answer += 'T';
        }

        if (map.getOrDefault('C', 0) >= map.getOrDefault('F', 0)) {
            answer += 'C';
        } else {
            answer += 'F';
        }

        if (map.getOrDefault('J', 0) >= map.getOrDefault('M', 0)) {
            answer += 'J';
        } else {
            answer += 'M';
        }

        if (map.getOrDefault('A', 0) >= map.getOrDefault('N', 0)) {
            answer += 'A';
        } else {
            answer += 'N';
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String answer = solution(survey, choices);
        System.out.println(answer);
    }
}
