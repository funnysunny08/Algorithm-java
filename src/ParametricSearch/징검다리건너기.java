package ParametricSearch;

// 카카오: 징검다리 건너기
public class 징검다리건너기 {

    public static boolean check(int n, int k, int[] stones) {
        int[] temp = stones.clone();
        int cnt = 0;
        for (int i = 0; i < temp.length; i++) {
            temp[i] -= n;
            if (temp[i] <= 0) {
                cnt++;
                if (cnt >= k) return false;
            } else {
                cnt = 0;
            }
        }
        return true;
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = 200000000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid - 1, k, stones)) { // 건널 수 있다! => 인원 늘려보자!
                left = mid + 1;
                answer = mid;
            } else { // 건널 수 없다! => 인원 줄여보자!
                right = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        System.out.println(solution(stones, 3));
    }
}
