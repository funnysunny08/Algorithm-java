package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 13023. ABCDE
public class BOJ_13023 {

    static int N, M;
    static List<List<Integer>> friends = new ArrayList<>();
    static boolean[] visited;

    private static void dfs(int idx, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (int f: friends.get(idx)) {
            if (!visited[f]) {
                visited[f] = true;
                dfs(f, depth + 1);
                visited[f] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) friends.add(new ArrayList<>());
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }
        System.out.println(0);
    }
}
