package Baekjoon;
// 백준 2075번 - n번째 큰 수
import java.util.*;
public class Solution_2075 {
    static int N;
    public static void main(String[] args) {
        // 1. 입력받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = sc.nextInt();
                pq.add(x);
            }
        }
        // 2. n-1번까지 빼내기
        for (int i = 0; i < N - 1; i++) {
            pq.poll();
        }
        // 3. n번째
        System.out.println(pq.poll());
    }
}
