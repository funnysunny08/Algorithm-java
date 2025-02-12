package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 14567. 선수과목 (Prerequisite)
public class BOJ_14567 {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] in;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        in = new int[N + 1];
        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            in[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.offer(i);
                dist[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : list.get(now)) {
                dist[next] = Math.max(dist[next], dist[now] + 1); // 가장 마지막으로 나타난 값으로 업데이트 하기 위함
                if (--in[next] == 0) q.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(dist[i]).append(" ");
        System.out.println(sb);
    }
}
