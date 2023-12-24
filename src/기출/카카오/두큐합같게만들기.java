package 기출.카카오;

import java.util.LinkedList;
import java.util.Queue;

// 카카오 - 두 큐 합 같게 만들기
public class 두큐합같게만들기 {

    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        int idx = 0;
        while (sum1 != sum2) {
            if (idx > (queue1.length + queue2.length) * 2) {
                return -1;
            }
            if (sum1 > sum2) { // q1의 합이 더 크므로 q1의 원소를 우선 제거!
                if (q1.size() == 1) {
                    return -1;
                }
                int x = q1.poll();
                q2.add(x);
                sum1 -= x;
                sum2 += x;
            } else {
                if (q2.size() == 1) {
                    return -1;
                }
                int x = q2.poll();
                q1.add(x);
                sum1 += x;
                sum2 -= x;
            }
            idx++;
        }
        return idx;
    }

    public static void main(String[] args) {
        int[] queue1 = {1, 1};
        int[] queue2 = {1, 5};
        System.out.println(solution(queue1, queue2));
    }
}
