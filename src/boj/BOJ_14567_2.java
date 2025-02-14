package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 14567. 선수과목 (Prerequisite)
public class BOJ_14567_2 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        int[] in = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            in[b]++;
        }

        int[] answer = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.offer(i);
                answer[i] = 1;
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : list.get(now)) {
                in[next]--;
                answer[next] = Math.max(answer[next], answer[now] + 1);
                if (in[next] == 0) q.offer(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }
}
