package 기출.카카오;

import java.util.Arrays;
import java.util.HashMap;

// 카카: 가장 많이 받은 선물
public class 가장많이받은선물 {

    static HashMap<String, Integer> friendsMap = new HashMap<>();
    static int[][] history; // 선물 주고받은 내역 담음
    static int[] score; // 선물 지수
    static int[] result; // 선물 받는 횟수


    public static int solution(String[] friends, String[] gifts) {
        // friends 이름에 index 부여! => 이름으로 index 찾기 위함.
        for (int i = 0; i < friends.length; i++) {
            friendsMap.put(friends[i], i);
        }

        // 선물 내역과 선물 지수 업데이트
        history = new int[friends.length][friends.length];
        score = new int[friends.length];
        for (String gift : gifts) {
            String[] str = gift.split(" "); // str[0]: from, str[1]: to
            int from = friendsMap.get(str[0]); // 선물 준 사람
            int to = friendsMap.get(str[1]); // 선물 받은 사람
            history[from][to]++;
            score[from]++;
            score[to]--;
        }

        //
        result = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (history[i][j] > history[j][i]) { // 선물 내역 비교
                    result[i]++;
                } else if (history[i][j] < history[j][i]) { // 선물 내역 비교
                    result[j]++;
                } else { // 선물 지수 비교
                    if (score[i] > score[j]) result[i]++;
                    else if (score[i] < score[j]) result[j]++;
                }
            }
        }
        Arrays.sort(result);
        return result[friends.length - 1];
    }

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(solution(friends, gifts));
    }
}
