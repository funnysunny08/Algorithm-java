package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2252번: 줄 세우기
public class BOJ_2252 {

    static List<List<Integer>> ll = new ArrayList<>();
    static StringBuffer sb = new StringBuffer();
    static int N;
    static int[] count; // count[x] : x로 들어오는 노드의 개수

    public static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) q.offer(i);
        }

        for (int i = 0; i < N; i++) {
            if (!q.isEmpty()) {
                int num = q.poll();
                sb.append(num).append(" ");

                for (int j = 0; j < ll.get(num).size(); j++) {
                    count[ll.get(num).get(j)]--;

                    if (count[ll.get(num).get(j)] == 0) {
                        q.offer(ll.get(num).get(j));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        count = new int[N+1];
        for (int i = 0; i<= N; i++) {
            ll.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ll.get(a).add(b);
            count[b]++; // b로 들어오는 노드의 개수
        }

        topologicalSort();
        System.out.println(sb.toString());
    }
}
