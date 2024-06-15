package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 12764번: 싸지방에 간 준하
public class BOJ_12764_2 {

    static int N;
    static List<Info> infos = new ArrayList<>();
    static int top = 0;
    static int[] cnt = new int[100001];
    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }

    static class Node implements Comparable<Node> {
        int idx, endTime;

        public Node(int idx, int endTime) {
            this.idx = idx;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Node o) {
            return this.endTime - o.endTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            infos.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(infos);

        PriorityQueue<Node> using = new PriorityQueue<>(); // 현재 사용중인 방
        PriorityQueue<Integer> available = new PriorityQueue<>(); // 사용가능한 방

        for (Info now : infos) {
            while (!using.isEmpty() && using.peek().endTime < now.start) {
                available.offer(using.poll().idx);
            }

            if (!available.isEmpty()) {
                int idx = available.poll();
                using.offer(new Node(idx, now.end));
                cnt[idx]++;
            } else {
                top++;
                using.offer(new Node(top, now.end));
                cnt[top]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(top).append("\n");
        for (int i = 1; i <= top; i++) sb.append(cnt[i]).append(" ");
        System.out.println(sb);
    }
}
