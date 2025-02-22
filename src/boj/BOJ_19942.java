package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 19942. 다이어트
public class BOJ_19942 {
    static int N;
    static int[] nutrients = new int[4];
    static int[][] cost;
    static int min = Integer.MAX_VALUE;
    static List<Integer> answer;

    private static boolean compare(List<Integer> list) {
        if (answer == null) return true;
        // list 가 answer 보다 사전 순으로 앞서면 true 반환
        int size = Math.min(answer.size(), list.size());
        for (int i = 0; i < size; i++) {
            if (answer.get(i) > list.get(i)) return true;
            if (answer.get(i) < list.get(i)) return false;
        }
        return list.size() < answer.size();
    }

    private static void dfs(int idx, int[] now, boolean[] selected) {
        if (check(now)) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) if (selected[i]) list.add((i + 1));
            if (now[4] < min || (now[4] == min && compare(list))) {
                min = now[4];
                answer = new ArrayList<>(list);
            }
        }
        if (idx == N) return;
        // 1. 선택하지 않음
        dfs(idx + 1, now, selected);

        // 2. 선택
        selected[idx] = true;
        for (int i = 0; i < 5; i++) {
            now[i] += cost[idx][i];
        }
        dfs(idx + 1, now, selected);
        selected[idx] = false;
        for (int i = 0; i < 5; i++) {
            now[i] -= cost[idx][i];
        }
    }

    private static boolean check(int[] now) {
        for (int i = 0; i < 4; i++) {
            if (now[i] < nutrients[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) nutrients[i] = Integer.parseInt(st.nextToken());
        cost = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] now = new int[5];
        boolean[] selected = new boolean[N];
        dfs(0, now, selected);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append("\n");
        for (int a : answer) sb.append(a).append(" ");
        System.out.println(sb);
    }
}
