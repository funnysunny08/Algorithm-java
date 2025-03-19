package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 16432. 떡장수와 호랑이
public class BOJ_16432 {
    static int N;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] selected;
    static Boolean[][] dp;

    private static boolean dfs(int day, int prev) {
        if (day == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) sb.append(selected[i]).append("\n");
            System.out.println(sb);
            return true;
        }

        if (dp[day][prev] != null) return dp[day][prev];
        for (int next : list.get(day)) {
            if (next != prev) {
                selected[day] = next;
                if (dfs(day + 1, next)) return true;
            }
        }
        return dp[day][prev] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        selected = new int[N];
        dp = new Boolean[N][10];
        for (int i = 0; i < N; i++) list.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) list.get(i).add(Integer.parseInt(st.nextToken()));
        }

        if (!dfs(0, 0)) System.out.println(-1);
    }
}
