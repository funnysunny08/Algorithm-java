package 기출.카카오;

import java.util.HashSet;
import java.util.Set;

// 카카오: 후보키
public class 후보키 {

    static int N;
    static boolean[] selected;
    static Set<String> keys = new HashSet<>();
    static String[][] info;

    public static int solution(String[][] relation) {
        N = relation[0].length;
        selected = new boolean[N];
        info = relation;

        for (int i = 1; i <= N; i++) dfs(0, i, 0);
        return keys.size();
    }

    public static void dfs(int depth, int targetDepth, int s) {
        if (depth == targetDepth) {
            if (exclusiveness()) {
                String key = getKey();
                for (String k : keys) {
                    String[] keyArr = k.split(" ");
                    int cnt = 0;
                    for (String str : keyArr) {
                        if (key.contains(str)) cnt++;
                    }
                    if (cnt == keyArr.length) return;
                }
                keys.add(getKey());
            }
            return;
        }
        for (int i = s; i < N; i++) {
            selected[i] = true;
            dfs(depth + 1, targetDepth, i + 1);
            selected[i] = false;
        }
    }

    public static boolean exclusiveness() {
        Set<String> set = new HashSet<>();
        String str;
        for (int i = 0; i < info.length; i++) {
            str = "";
            for (int j = 0; j < N; j++) {
                if (selected[j]) str += info[i][j] + " ";
            }
            set.add(str);
        }
        return set.size() == info.length;
    }

    public static String getKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (selected[i]) sb.append(i).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String[][] relations = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        String[][] relations = { {"a","1","aaa","c","ng"},
            {"a","1","bbb","e","g"},
            {"c","1","aaa","d","ng"},
            {"d","2","bbb","d","ng"}};
        System.out.println(solution(relations));
    }
}
