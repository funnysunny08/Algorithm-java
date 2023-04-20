package Programmers;
// 프로그래머스 - 프린터
import java.util.*;
public class Solution_022 {
    class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;
            //
            LinkedList<int[]> list = new LinkedList<>();
            for (int i = 0; i < priorities.length; i++) {
                int[] tmp = {priorities[i], i};
                list.add(tmp);
            }
            //
            int print = 1;
            while(!list.isEmpty()) {
                int[] curr = list.pop(); // 현재 문서의 중요도
                boolean isMax = true;
                for (int[] l: list) {
                    if (l[0] > curr[0]) {
                        isMax = false;
                        break;
                    }
                }
                if (isMax) { // 출력
                    if (curr[1] == location) {
                        answer = print;
                        break;
                    } else {
                        print++;
                    }
                } else { // 뒤로 넘겨
                    list.add(curr);
                }

            }
            return answer;
        }
    }
}
