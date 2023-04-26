package Programmers;
// 프로그래머스 - 더 맵게 (힙)
import java.util.*;
public class Solution_025 {
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            // 1. 우선순위 큐에 넣음
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < scoville.length; i++) {
                pq.add(scoville[i]);
            }

            // 2.
            while(pq.peek() < K) {
                if (pq.size() < 2) return -1;
                int first = pq.poll();
                int second = pq.poll();
                pq.add(first + second * 2);
                answer++;
            }
            return answer;
        }
    }
}
