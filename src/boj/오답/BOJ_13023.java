package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 13023. ABCDE
public class BOJ_13023 {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visit;

    private static void dfs(int i, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (int next : list.get(i)) {
            if (visit[next]) continue;
            visit[next] = true;
            dfs(next, depth + 1);
            visit[next] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            visit[i] = true;
            dfs(i, 1);
            visit[i] = false;
        }
        System.out.println(0);
    }
}
