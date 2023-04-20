package Baekjoon;
// 백준 1966번 - 프린터 큐
import java.util.*;
public class Solution_1966 {
    static int T;
    static List<Integer> ans = new ArrayList<>();

    public static void solution(LinkedList<int[]> list, int location) {
        int print = 1;
        while(!list.isEmpty()) {
            int[] curr = list.pop(); // 현재 맨 앞에 있는 문서
            boolean isMax = true;
            for (int[] l: list) { // 현재 문서보다 중요도가 높은 문서가 있는지 체크
                if (l[0] > curr[0]) {
                    isMax = false;
                    break;
                }
            }
            if (isMax) { // 출력
                if (curr[1] == location) {
                    ans.add(print);
                    break;
                } else {
                    print++;
                }

            } else { // 뒤로 넘겨
                list.add(curr);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt(); //
            int target = sc.nextInt();
            LinkedList<int[]> list = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                int ele = sc.nextInt();
                int[] arr = {ele, j}; // arr[0] -> 중요도, arr[1] -> 인덱스
                list.add(arr);
            }
            // -- i 번째 테스트 입력 받기 끝 --
            solution(list, target);
        }

        for (Integer a: ans) {
            System.out.println(a);
        }
    }
}
