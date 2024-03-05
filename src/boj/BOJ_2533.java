package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2533번: 사회망 서비스 (SNS)
public class BOJ_2533 {

    static int N;
    static List<Integer>[] friends;
    static int[][] dp;
    static boolean[] visited;

    public static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0; // number 가 얼리어답터가 아닌 경우
        dp[number][1] = 1; // number 가 얼리어답터인 경우

        for (int i = 0; i < friends[number].size(); i++) {
            int child = friends[number].get(i);
            if (!visited[child]) {
                dfs(child);
                dp[number][0] += dp[child][1]; // 부모가 얼리어답터가 아닌 경우에 자식은 무조건 얼리어답터여야 함!
                dp[number][1] += Math.min(dp[child][0], dp[child][1]); // 부모가 얼리어답터이면 자식은 상관없음!
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        friends = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            friends[x].add(y);
            friends[y].add(x);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }
}
