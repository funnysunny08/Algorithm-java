package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1939. 중량제한
public class BOJ_1939 {
    static int N, M;
    static List<List<Node>> list = new ArrayList<>();
    static int start, end;
    static boolean[] visit;

    private static boolean dfs(int now, long limit) {
        if (now == end) return true;
        for (Node next : list.get(now)) {
            if (!visit[next.to] && next.cost >= limit) {
                visit[now] = true;
                if (dfs(next.to, limit)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long left = 0, right = 0;
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, v));
            list.get(b).add(new Node(a, v));
            right = Math.max(right, v);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        long answer = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            visit = new boolean[N + 1];
            if (dfs(start, mid)) { // 늘려야 해
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
