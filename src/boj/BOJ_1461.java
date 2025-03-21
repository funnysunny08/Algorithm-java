package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1461. 도서관
public class BOJ_1461 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> mq = new PriorityQueue<>(); // 음수가 담김 -> 작은 순대로 나와야 함
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 양수가 담김 -> 큰 순대로 나와야 함
        st = new StringTokenizer(br.readLine());
        int MAX_ABS = -1;
        int MAX = -1;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (MAX_ABS < Math.abs(n)) {
                MAX_ABS = Math.abs(n);
                MAX = n;
            }
            if (n < 0) mq.offer(n);
            else pq.offer(n);
        }

        int answer = 0;
        if (MAX < 0) {
            int size = Math.min(mq.size(), M);
            int max = Math.abs(mq.poll());
            for (int i = 0; i < size - 1; i++) mq.poll();
            answer += max;
        } else {
            int size = Math.min(pq.size(), M);
            int max = pq.poll();
            for (int i = 0; i < size - 1; i++) pq.poll();
            answer += max;
        }

        while (!mq.isEmpty()) {
            int size = Math.min(mq.size(), M);
            int max = Math.abs(mq.poll());
            for (int i = 0; i < size - 1; i++) mq.poll();
            answer += max * 2;
        }
        while (!pq.isEmpty()) {
            int size = Math.min(pq.size(), M);
            int max = pq.poll();
            for (int i = 0; i < size - 1; i++) pq.poll();
            answer += max * 2;
        }
        System.out.println(answer);
    }
}
