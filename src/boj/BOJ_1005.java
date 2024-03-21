package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1005번: ACM Craft
public class BOJ_1005 {

    static int N, K; // 건물수, 규칙수
    static int[] cost; // 건물 짓는 데 드는 비용
    static int[] in; // 진입차수 저장
    static List<Integer>[] orders;
    static int target;
    static int[] arr;
    static class Node {
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int getAnswer() {
        Queue<Node> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) q.offer(new Node(i, cost[i]));
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == target) return now.cost;

            for (int next : orders[now.x]) {
                in[next]--;
                arr[next] = Math.max(arr[next], now.cost);
                if (in[next] == 0) {
                    q.offer(new Node(next, arr[next] + cost[next]));
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new int[N + 1];
            in = new int[N + 1];
            orders = new List[N + 1];
            for (int j = 1; j <= N; j++) orders[j] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                orders[a].add(b);
                in[b]++;
            }

            target = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            // 입력받기 끝!
            sb.append(getAnswer()).append("\n");
        }
        System.out.println(sb);

    }
}
