package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 11725. 트리의 부모 찾기
public class BOj_11725 {
    static int N;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] parent;

    private static void dfs(int p) {
        for (int node : list.get(p)) {
            if (parent[node] == 0) {
                parent[node] = p;
                dfs(node);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) sb.append(parent[i]).append("\n");
        System.out.println(sb);
    }
}
