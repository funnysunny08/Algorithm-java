package Programmers;

import java.util.PriorityQueue;

// 가장 큰 수
public class P_42746 {

    // 1. 앞자리가 가장 큰 게 앞으로
    // 2. 여러 개라면, 그 다음 자리가 큰 게 앞으로
    public static String solution(int[] numbers) {
        PriorityQueue<String> pq = new PriorityQueue<>(
                (o1, o2) -> (o2 + o1).compareTo(o1 + o2)
        );

        for (int n : numbers) pq.offer(String.valueOf(n));
        if (pq.peek().equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(pq.poll());
        return sb.toString();
    }

    public static void main(String[] args) {
//        int[] arr = {6, 10, 2};
        int[] arr = {3, 30, 34, 5, 9};
        System.out.println(solution(arr));
    }
}
