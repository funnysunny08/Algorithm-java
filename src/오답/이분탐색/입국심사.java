package 오답.이분탐색;

import java.util.Arrays;

// 프로그래머스: 입국심사
public class 입국심사 {

    /*
    * 파라메트릭 서치 (이분 탐색)
    * mid => 총 소요시간
    * 최대 소요시간: 정렬된 times[0] * n
    * */

    public static long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times);

        long left = times[0];
        long right = (long) times[times.length - 1] * (long) n; // 최대 소요 시간

        long mid, sum;
        while (left <= right) {
            mid = (left + right) / 2;

            sum = 0;
            for (int time : times) {
                sum += mid / time; // 해당 입국 심사대에서는 mid 시간 동안 몇 명을 심사할 수 있는지!
            }

            if (sum >= n) { // 가능하니깐 시간 더 줄여보자!
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else { // 불가능하니깐 시간을 더 늘려야 한다!
                left = mid + 1;
            }
        }
        return answer;
    }


    public static void main(String[] args) {

    }
}
