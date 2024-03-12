package 기출.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 카카오: 순위 검색 (효율성 - 풀이참고)
public class 순위검색3 {

    static Map<String, List<Integer>> allInfo = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 1. info의 모든 경우의 수 map에 저장
        for (int i = 0; i < info.length; i++) {
            dfs("", 0, info[i].split(" "));
        }

        List<String> list = new ArrayList<>(allInfo.keySet());
        for (int i = 0; i < list.size(); i++) {
            List<Integer> scoreList = allInfo.get(list.get(i));
            Collections.sort(scoreList);
        }

        // 2.
        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] str = query[i].split(" ");
            int score = Integer.parseInt(str[1]);
            answer[i] = binarySearch(str[0], score);
        }

        return answer;
    }

    public static int binarySearch(String str, int score) {
        if(!allInfo.containsKey(str)) return 0;
        List<Integer> list = allInfo.get(str);
        int left = 0, right = list.size() - 1;
        if (list.isEmpty()) return 0; // 리스트가 비어 있을 경우 0 반환

        while (left <= right) {
            int middle = (left + right) / 2;
            if (list.get(middle) >= score) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return list.size() - left;
    }

    public static void dfs(String pos, int depth, String[] info) {
        if (depth == 4) {
            if (!allInfo.containsKey(pos)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(Integer.parseInt(info[4]));
                allInfo.put(pos, tmp);
            } else {
                allInfo.get(pos).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(pos + "-", depth + 1, info);
        dfs(pos + info[depth], depth + 1, info);
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] ans = solution(info, query);
        for (int a : ans) {
            System.out.println(a);
        }
    }
}
