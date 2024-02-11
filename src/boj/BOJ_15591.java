package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 15591번: MooTube (Silver)
public class BOJ_15591 {

    static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        List<int[]>[] list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list[p].add(new int[]{q, r});
            list[q].add(new int[]{p, r});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[N + 1];
            visited[v] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(v);

            int ans = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int[] a : list[cur]) {
                    if (!visited[a[0]] && a[1] >= k) {
                        q.add(a[0]);
                        visited[a[0]] = true;
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
