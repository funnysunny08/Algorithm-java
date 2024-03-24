package 프로그래머스;

import java.util.*;

// 프로그래머스: 베스트 앨범
public class 베스트앨범 {

    static HashMap<String, Integer> total = new HashMap<>();
    static HashMap<String, HashMap<Integer, Integer>> hm = new HashMap<>();

    public static Integer[] solution(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
            hm.put(genres[i], hm.getOrDefault(genres[i], new HashMap<>()));
            hm.get(genres[i]).put(i, plays[i]);
        }

        // genres 순위 매기기
        List<String> totalKeySet = new ArrayList<>(total.keySet());
        totalKeySet.sort((o1, o2) -> {
            return total.get(o2).compareTo(total.get(o1));
        });

        List<Integer> result = new ArrayList<>();
        for (String key : totalKeySet) {
            HashMap<Integer, Integer> tmp = hm.get(key);

            List<Integer> tmpKeySet = new ArrayList<>(tmp.keySet());
            tmpKeySet.sort((o1, o2) -> tmp.get(o2).compareTo(tmp.get(o1)));

            for (int i = 0; i < 2; i++) {
                if (tmpKeySet.size() < i + 1) break;
                result.add(tmpKeySet.get(i));
            }
        }
        return result.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        Integer[] solution = solution(genres, plays);
        for (int a : solution) {
            System.out.println(a);
        }
    }

}
