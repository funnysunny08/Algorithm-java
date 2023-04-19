package Baekjoon;
// 백준 1966번 - 프린터 큐
import java.util.*;
public class Solution_1966 {
    static int T;
    static List<Integer> ans = new ArrayList<>();

    public static void solution(LinkedList<int[]> q, int target) {
        int order = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll(); // 가장 앞에 원소 뽑음, now[0] -> 중요도, now[1] -> 인덱스
            // 1. 현재의 중요도가 queue 내에서 제일 큰 중요도인지 확인! (공동 1순위여도 괜찮다)
            boolean isMax = true;
            for (int i = 0; i < q.size(); i++) {
                if (now[0] < q.get(i)[0]) { // 현재의 중요도가 최대값이 아닌 상황
                    isMax = false;
                }
            }
            if (isMax) { // 현재 값이 제일 큰 중요도 -> 출력하기
                order++;
                if (now[1] == target) {
                    ans.add(order);
                }
            } else { // 현재값보다 더 큰 중요도가 있기 때문에 뒤로 보내기
                q.add(now);
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt(); //
            int target = sc.nextInt();
            LinkedList<int[]> queue = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                int ele = sc.nextInt();
                int[] arr = {ele, j}; // arr[0] -> 중요도, arr[1] -> 인덱스
                queue.add(arr);
            }
            // -- i 번째 테스트 입력 받기 끝 --
            solution(queue, target);
        }

        for (Integer a: ans) {
            System.out.println(a);
        }
    }
}
