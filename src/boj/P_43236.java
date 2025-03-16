package boj;

import java.util.*;

// 프로그래머스: 징검다리
public class P_43236 {
    public static int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 0;
        int right = distance;

        int answer = -1;
        while (left <= right) {
            // 징검다리에서 거리 차의 최솟값
            int mid = (left + right) / 2;

            int cnt = 0; // mid 가 최솟값이 되기 위해 제거해야 하는 바위 수
            int prev = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    cnt++;
                } else {
                    prev = rocks[i];
                }
                if (cnt > n) break; // n개 넘게 제거해야만 mid 가 최솟값이 되기 때문에 탐색 종료
            }
            if (distance - prev < mid) cnt++;

            if (cnt > n) { // mid 는 바위 n 개를 제거해도 최솟값이 될 수 없음 => 최솟값을 내려야 함
                right = mid - 1;
            } else { // mid 는 최솟값 가능함 => 최솟값을 올려보자
                left = mid + 1;
                answer = mid;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] rocks = {2, 14, 11, 21, 17};
        System.out.println(solution(25, rocks, 2));
    }
}
