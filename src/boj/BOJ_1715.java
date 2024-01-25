package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 백준 1715번: 카드 정렬하기
public class BOJ_1715 {

    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(br.readLine()));
        }

        long answer = 0;
        while (pq.size() >= 2) {
            long first = pq.poll();
            long second = pq.poll();
            pq.offer(first + second);
            answer += first + second;
        }
        System.out.println(answer);
    }
}
