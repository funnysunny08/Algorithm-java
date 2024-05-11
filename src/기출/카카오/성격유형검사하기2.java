package 기출.카카오;

import java.util.HashMap;

// 카카오: 성격 유형 검사하기
public class 성격유형검사하기2 {

    public static String solution(String[] survey, int[] choices) {
        String[] types = {"RT", "CF", "JM", "AN"};
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < 2; j++) hm.put(types[i].charAt(j), 0);
        }

        for (int i = 0; i < survey.length; i++) {
            int value = choices[i] - 4;
            if (value > 0) {
                hm.put(survey[i].charAt(1), hm.get(survey[i].charAt(1)) + value);
            } else if (value < 0) {
                hm.put(survey[i].charAt(0), hm.get(survey[i].charAt(0)) + Math.abs(value));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            int v1 = hm.get(types[i].charAt(0));
            int v2 = hm.get(types[i].charAt(1));
            if (v1 >= v2) sb.append(types[i].charAt(0));
            else sb.append(types[i].charAt(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        System.out.println(solution(survey, choices));
    }
}
