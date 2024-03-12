package 기출.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// 카카오: 순위 검색 (효율성)
public class 순위검색2 {

    static List<Integer>[][][][] group;

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        group = new List[4][3][3][3];

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        group[a][b][c][d] = new ArrayList<>();
                    }
                }
            }
        }

        for (int i = 0; i < info.length; i++) {
            String[] one = info[i].split(" ");
            int lang = getLang(one[0]);
            int type = getType(one[1]);
            int exp = getExp(one[2]);
            int food = getFood(one[3]);
            int score = Integer.parseInt(one[4]);
            dfs(0, new boolean[4], lang, type, exp, food, score);
        }

        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            int lang = getLang(q[0]);
            int type = getType(q[2]);
            int exp = getExp(q[4]);
            int food = getFood(q[6]);
            int score = Integer.parseInt(q[7]);
            List<Integer> target = group[lang][type][exp][food];
            answer[i] = binarySearch(target, score);
        }
        return answer;
    }

    public static int binarySearch(List<Integer> list, int score) {
        Collections.sort(list);
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

    public static void dfs(int depth, boolean[] selected, int lang, int type, int exp, int food, int score) {
        // 어떤 항목이 "-"가 될지 선택한다!
        if (depth == 4) {
            if (!selected[0]) lang = 0;
            if (!selected[1]) type = 0;
            if (!selected[2]) exp = 0;
            if (!selected[3]) food = 0;
            group[lang][type][exp][food].add(score);
            return;
        }
        dfs(depth + 1, selected, lang, type, exp, food, score); // 선택하지 않은 경우
        selected[depth] = true;
        dfs(depth + 1, selected, lang, type, exp, food, score); // 선택한 경우
        selected[depth] = false;
    }

    public static int getLang(String lang) {
        if (Objects.equals(lang, "-")) return 0;
        else if (Objects.equals(lang, "cpp")) return 1;
        else if (Objects.equals(lang, "java")) return 2;
        else return 3;
    }

    public static int getType(String type) {
        if (Objects.equals(type, "-")) return 0;
        else if (Objects.equals(type, "backend")) return 1;
        else return 2;
    }

    public static int getExp(String exp) {
        if (Objects.equals(exp, "-")) return 0;
        else if (Objects.equals(exp, "junior")) return 1;
        else return 2;
    }

    public static int getFood(String food) {
        if (Objects.equals(food, "-")) return 0;
        else if (Objects.equals(food, "chicken")) return 1;
        else return 2;
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
