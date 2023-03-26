package Programmers;
// 프로그래머스 - 완주하지 못한 선수
import java.util.Arrays;
import java.util.HashMap;

public class Solution_001 {
    public String solution(String[] participant, String[] completion) {
        // 1. 두 배열을 정렬한다.
        Arrays.sort(participant);
        Arrays.sort(completion);

        // 2. 두 배열이 다를 때까지 찾는다.
        int i = 0;
        for (; i < completion.length; i++) {
            if (!participant[i].equals(completion[i]))
                break;
        }
        // 3. 여기까지 왔다면, 마지막 주자가 완주하지 못한 선수다.
        return participant[i];
    }
    // Hash Solution
    public String solution2(String[] participant, String[] completion) {
        String answer = "";
        // 1. Hash map을 만든다 (participant)
        HashMap<String, Integer> map = new HashMap<>();
        for (String player : participant)
            map.put(player, map.getOrDefault(player, 0) + 1);

        // 2. Hash map을 뺀다 (completion)
        for (String player : completion)
            map.put(player, map.get(player) - 1);

        // 3. value가 0이 아닌 주자를 찾는다.
        for (String key : map.keySet()) {
            if (map.get(key) != 0)
                answer = key;
                break;
        }
        return answer;
    }
}
