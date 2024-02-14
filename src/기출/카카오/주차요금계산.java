package 기출.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// 카카오: 주차 요금 계산
public class 주차요금계산 {

    static Map<String, List<Record>> map = new TreeMap<>();

    static class Record implements Comparable<Record> {
        int time;
        String type;
        Record (int time, String type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Record o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static int[] solution(int[] fees, String[] records) {
        // 1. 기록 정보 HashMap에 담기
        for (int i = 0; i < records.length; i++) {
            String[] str = records[i].split(" ");
            String[] time = str[0].split(":");
            int totalTime = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

            if (map.containsKey(str[1])) {
                map.get(str[1]).add(new Record(totalTime, str[2]));
            } else {
                map.put(str[1], new ArrayList<>());
                map.get(str[1]).add(new Record(totalTime, str[2]));
            }
        }

        // 2.
        List<Integer> answer = new ArrayList<>();
        for (String key : map.keySet()) {
            List<Record> list = map.get(key);
            Collections.sort(list);

            if (list.size() % 2 != 0) {
                list.add(new Record(1439, "OUT"));
            }

            int time = 0;
            for (int i = 0; i < list.size(); i += 2) {
                time += list.get(i + 1).time - list.get(i).time;
            }

            if (time <= fees[0]) {
                answer.add(fees[1]);
            } else {
                int money = 0;
                if ((time - fees[0]) % fees[2] == 0) {
                    money = fees[1] + (time - fees[0]) / fees[2] * fees[3];
                } else {
                    money = fees[1] + ((time - fees[0]) / fees[2] + 1) * fees[3];
                }
                answer.add(money);
            }
        }
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] ans = solution(fees, records);
        for (int a : ans) {
            System.out.println(a);
        }
    }
}
