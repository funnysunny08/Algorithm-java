package 기출.카카오;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 프로그래머스: 개인정보 수집 유효기간
public class 개인정보수집유효기간 {

    public static int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> info = new HashMap<>();
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < terms.length; i++) { // 약관별 유효기간 담음!
            String[] ch = terms[i].split(" ");
            info.put(ch[0], Integer.parseInt(ch[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String[] ch = privacies[i].split(" ");
            int term = info.get(ch[1]);
            String[] date = ch[0].split("\\.");
            // date[0]: year, date[1]: month, date[2]: day
            int newYear = Integer.parseInt(date[0]);
            int newMonth = Integer.parseInt(date[1]) + term;
            int newDay = Integer.parseInt(date[2]);
            if (newMonth > 12) {
                if (newMonth % 12 == 0) { // 24, 36, ...
                    newYear += newMonth / 12 - 1;
                    newMonth = 12;
                } else {
                    newYear += newMonth / 12;
                    newMonth = newMonth % 12;
                }
            }

            StringBuilder sb = new StringBuilder().append(newYear);
            if (newMonth < 10) {
                sb.append(0).append(newMonth);
            } else {
                sb.append(newMonth);
            }
            if (newDay < 10) {
                sb.append(0).append(newDay);
            } else {
                sb.append(newDay);
            }

            if (Integer.parseInt(sb.toString()) <= Integer.parseInt(today.replace(".", ""))) {
                answer.add(i + 1);
            }

        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] ans = solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
