package 기출.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// 카카오: 주차 요금 계산
public class 주차요금계산2 {

    static final int LAST_TIME = 1439;
    static HashMap<String, List<Integer>> hm = new HashMap<>();

    public static int[] solution(int[] fees, String[] records) {
        for (String record: records) {
            String[] info = record.split(" ");
            String[] timeStr = info[0].split(":");
            int time = Integer.parseInt(timeStr[0]) * 60 + Integer.parseInt(timeStr[1]);
            String key = info[1];
            if (!hm.containsKey(key)) hm.put(key, new ArrayList<>());
            hm.get(key).add(time);
        }

        int[] answer = new int[hm.keySet().size()];
        List<String> keySet = new ArrayList<>(hm.keySet());
        Collections.sort(keySet);
        int idx = 0;
        for (String key : keySet) {
            if (hm.get(key).size() % 2 != 0) hm.get(key).add(LAST_TIME);
            int time = 0;
            for (int i = 0; i < hm.get(key).size(); i += 2) {
                time += hm.get(key).get(i + 1) - hm.get(key).get(i);
            }

            int money = fees[1];
            if (time > fees[0]){ // 기본 요금 시간 초과
                time -= fees[0];
                int divide = time / fees[2];
                if (time % fees[2] != 0) divide++;
                money += divide * fees[3];
            }
            answer[idx] = money;
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] answer = solution(fees, records);
        for (int a : answer) {
            System.out.println(a);
        }
    }
}
