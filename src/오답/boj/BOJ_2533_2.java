package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2533번: 사회망 서비스 (SNS)
public class BOJ_2533_2 {

    static int N;
    static List<Integer>[] friends;
    static Integer[][] dp; // dp[x][0]: 얼리어답터 X, dp[x][1]: 얼리어답터 O
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        friends = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) friends[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            friends[u].add(v);
            friends[v].add(u);
        }

        dp = new Integer[N + 1][2];
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int child: friends[node]) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }

    public static int getDp(int x, int y) {
        if (dp[x][y] != null) return dp[x][y];

        if (y == 0) { // 부모가 얼리어답터가 아님.
            dp[x][y] = 0;
            for (int child : friends[x]) {
                dp[x][y] += getDp(child, 1);
            }
        } else { // 부모가 얼리어답터
            dp[x][y] = 1;
            for (int child : friends[x]) {
                dp[x][y] += Math.min(getDp(child, 0), getDp(child, 1));
            }
        }
        return dp[x][y];
    }
}
