package 기출.카카오;

import java.util.ArrayList;
import java.util.List;

// 카카오: 단체 사진 찍기
public class 단체사진찍기 {

    static String[] names = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static int answer;
    static boolean[] visited;
    static List<String> sequence = new ArrayList<>();
    static String[] condition;

    public static void dfs(int depth) {
        if (depth == names.length) {
            if (chk()) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < names.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence.add(names[i]);
                dfs(depth + 1);
                visited[i] = false;
                sequence.remove(sequence.size() - 1);
            }
        }
    }

    public static boolean chk() {
        for (int i = 0; i < condition.length; i++) {
            String x = String.valueOf(condition[i].charAt(0));
            String y = String.valueOf(condition[i].charAt(2));
            char cond = condition[i].charAt(3); // =, <, >
            int standard = Integer.parseInt(condition[i].substring(4));

            int diff = Math.abs(sequence.indexOf(x) - sequence.indexOf(y)) - 1;
            if (cond == '=') {
                if (diff != standard) return false;
            } else if (cond == '<') {
                if (!(diff < standard))return false;
            } else if (cond == '>') {
                if (!(diff > standard)) return false;
            }
        }
        return true;
    }

    public static int solution(int n, String[] data) {
        visited = new boolean[names.length];
        condition = data;

        dfs(0);
        return answer;
    }

    public static void main(String[] args) {
        String[] data = {"N~F=0", "R~T>2"};
        System.out.println(solution(2, data));
    }
}
