package 오답.이분탐색;

import java.util.Collections;
import java.util.PriorityQueue;

// 프로그래머스: 디펜스 게임
public class 디펜스게임 {

    /*
    * 무적권을 적이 많은 경우에 사용하면 좋음!
    *
    * */

    public static int solution(int n, int k, int[] enemy) {
        // n: 병사의 수, k: 무적권의 수, enemy[]: 라운드 별 적의 수
        int answer = enemy.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int my = n;
        int card = k;
        for (int i = 0; i < enemy.length; i++) {
            my -= enemy[i]; // 일단 공격
            pq.add(enemy[i]); // 내가 공격한 적 우선순위 큐에 넣음! (빡센 라운드 순으로!)

            if (my < 0) { // f
                if (card > 0) {
                    my += pq.poll(); // 가장 빡센 라운드를 무적권 사용!
                    card--;
                } else {
                    answer = i;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        
    }
}
