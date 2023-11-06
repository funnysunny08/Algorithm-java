package ParametricSearch;

import java.util.*;

class 입국심사 {
    public static long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times); // 시간 순으로 정렬!

        long start = times[0];  // 최소 시간 => 가장 빠른 심사대에서 걸리는 시간
        long end = (long) times[times.length-1] * (long) n; // 최대시간 = 가장오래걸리는시간 * 인원수

        long mid;
        long sum = 0;
        while(start <= end) {
            mid = (start + end) / 2;
            sum = 0;
            for(int time : times) {
                sum += mid / time; // 총 소요 시간 / 각 심사대에서 걸리는 시간 => 해당 시간동안 심사대에서 처리할 수 있는 인원수
            }

            if(sum >= n) { // 해당 시간 동안 모든 인원이 심사받을 수 있음!
                answer = Math.min(answer, mid);
                end = mid - 1; // 시간을 줄여보자!!!
            }
            else {
                start = mid + 1; // 불가능해 -> 시간을 늘려보자!!!
            }
        }
        return answer;
    }
}
