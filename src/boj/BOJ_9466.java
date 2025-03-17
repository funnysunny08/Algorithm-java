package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 9466. 텀 프로젝트
public class BOJ_9466 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static boolean[] isEnd;
    static int cnt;

    private static void dfs(int now) {
        if (visited[now]) return;
        visited[now] = true;
        int next = arr[now];
        if (!visited[next]) {
            dfs(next);
        } else {
            if (!isEnd[next]) {
                cnt++;
                for (int i = next; i != now; i = arr[i]) cnt++;
            }
        }
        isEnd[now] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            isEnd = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            cnt = 0;
            for (int i = 1; i <= N; i++) dfs(i);
            sb.append(N - cnt).append("\n");
        }
        System.out.println(sb);
    }
}
