package 기출.카카오;

import java.util.*;

// 카카오: 메뉴 리뉴얼
public class 메뉴리뉴얼 {

    static int popularity;
    static List<String> combi;
    static String[] order;
    static List<Character> type = new ArrayList<>();

    public static String[] solution(String[] orders, int[] course) {
        order = orders;

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders[i].length(); j++) {
                set.add(orders[i].charAt(j));
            }
        }

        for (Character ch : set) type.add(ch);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            popularity = -1;
            combi = new ArrayList<>();
            dfs(0, course[i], "", 0);
            result.addAll(combi);
        }

        Collections.sort(result);
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    static void dfs(int depth, int size, String menu, int s) {
        if (depth == size) {
            int value = getPopularity(menu);
            if (value < 2) {
                return;
            } else if (popularity == value) {
                combi.add(menu);
            } else if (popularity < value) {
                popularity = value;
                combi.clear();
                combi.add(menu);
            }
            return;
        }
        for (int i = s; i < type.size(); i++) {
            dfs(depth + 1, size, menu + type.get(i), i + 1);
        }
    }

    static int getPopularity(String menu) {
        int cnt = 0;
        for (String o : order) {
            boolean flag = true;
            for (int i = 0; i < menu.length(); i++) {
                if (!o.contains(String.valueOf(menu.charAt(i)))) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
//        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};
        String [] ans = solution(orders, course);
        for (String a : ans) {
            System.out.println(a);
        }
    }
}
