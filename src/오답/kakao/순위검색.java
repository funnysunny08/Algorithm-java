package 오답.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 카카오: 순위 검색
public class 순위검색 {

    Map<String, List<Integer>> group = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for (String str : info) {
            dfs(0, "", str.split(" "));
        }

        List<String> keys = new ArrayList<>(group.keySet());
        for (String key : keys) {
            Collections.sort(group.get(key));
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < answer.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] arr = query[i].split(" ");
            int score = Integer.parseInt(arr[1]);

            if (group.containsKey(arr[0])) {
                answer[i] = binarySearch(score, group.get(arr[0]));

            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    public int binarySearch(int score, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

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

    public void dfs(int depth, String key, String[] arr) {
        if (depth == 4) {
            if (!group.containsKey(key)) group.put(key, new ArrayList<>());
            group.get(key).add(Integer.parseInt(arr[4]));
            return;
        }
        dfs(depth + 1, key + arr[depth], arr);
        dfs(depth + 1, key + "-", arr);
    }

    public static void main(String[] args) {

    }
}
