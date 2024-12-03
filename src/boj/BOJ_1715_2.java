package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 1715. 카드 정렬하기
public class BOJ_1715_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) pq.offer(Integer.parseInt(br.readLine()));

        int sum = 0;
        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            sum += a + b;
            pq.offer(a + b);
        }
        System.out.println(sum);
    }
}
