package boj;

import java.util.*;

// 프로그래머스: 입국심사
public class P_43238 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = times[0];
        long right = times[times.length-1] * (long)n;

        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                if (cnt >= n) break;
            }

            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }

}
