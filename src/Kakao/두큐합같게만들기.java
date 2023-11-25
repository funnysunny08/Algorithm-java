package Kakao;

import java.util.LinkedList;
import java.util.Queue;

// 2022 KAKAO TECH INTERNSHIP: 두 큐 합 같게 만들기
public class 두큐합같게만들기 {

    public static long getSum(int[] queue) {
        long sum = 0;
        for (int i = 0; i < queue.length; i++) {
            sum += queue[i];
        }
        return sum;
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long sum1 = getSum(queue1);
        long sum2 = getSum(queue2);

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        while (sum1 != sum2) {
            if (answer > (queue1.length + queue2.length) * 2) {
                return -1;
            }
            if (sum1 < sum2) { // 큰 데에서 작은 데에 넣어줌!
                int value = q2.poll();
                q1.add(value);
                sum1 += value;
                sum2 -= value;
            } else if (sum1 > sum2) {
                int value = q1.poll();
                q2.add(value);
                sum1 -= value;
                sum2 += value;
            } else {
                return answer;
            }
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};
        int answer = solution(queue1, queue2);
        System.out.println(answer);
    }
}
