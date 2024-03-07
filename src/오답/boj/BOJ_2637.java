package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2637번: 장난감 조립
public class BOJ_2637 {

    static int N, M;
    static int[] cnt;
    static int[] in;
    static int[] out;
    static List<List<int[]>> info = new ArrayList<>();

    public static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        cnt[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int[] next : info.get(now)) {
                in[next[0]]--;
                cnt[next[0]] += cnt[now] * next[1];
                if (in[next[0]] == 0) q.offer(next[0]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (out[i] == 0) sb.append(i).append(" ").append(cnt[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cnt = new int[N + 1];
        in = new int[N + 1];
        out = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            info.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            out[X]++;
            in[Y]++;
            info.get(X).add(new int[]{Y, K});
        }

        topologicalSort();
    }
}
