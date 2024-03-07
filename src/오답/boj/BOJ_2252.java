package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 2252번: 줄 세우기
public class BOJ_2252 {

    static int N, M;
    static List<Integer>[] info;
    static int[] in;
    static Set<Integer> member = new HashSet<>();

    public static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
            member.remove(now);

            for (int next : info[now]) {
                in[next]--;
                if (in[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int m : member) sb.append(m).append(" ");
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new List[N + 1];
        in = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            info[i] = new ArrayList<>();
            member.add(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            in[b]++;
            info[a].add(b);
        }
        topologicalSort();
    }
}
