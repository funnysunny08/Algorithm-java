package boj;

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
    static List<List<Edge>> list = new ArrayList<>();
    static int[] out;
    static int[] in;
    static int[] answer;

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void topologicalSort() {
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(N, 1));
        answer[N] = 1;

        while (!q.isEmpty()) {
            Edge curr = q.poll();

            for (int i = 0; i < list.get(curr.to).size(); i++) {
                Edge next = list.get(curr.to).get(i);
                answer[next.to] += answer[curr.to] * next.weight;
                in[next.to]--;
                if (in[next.to] == 0) {
                    q.offer(new Edge(next.to, answer[next.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        out = new int[N + 1];
        in = new int[N + 1];
        answer = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x를 만드는 데 y가 k개 필요하다.
            int y = Integer.parseInt(st.nextToken()); // x -> y
            int k = Integer.parseInt(st.nextToken());
            list.get(x).add(new Edge(y, k));
            out[x]++;
            in[y]++;
        }

        topologicalSort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (out[i] == 0) sb.append(i).append(" ").append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}
