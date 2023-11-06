package ParametricSearch;

import java.util.*;

public class 디펜스게임 {

    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int my = n; // 내 병력 수
        int card = k; // 무적권 수
        for (int i = 0; i < enemy.length; i++) { // 라운드
            my -= enemy[i];
            pq.add(enemy[i]);

            if (my < 0) { // 내 병력이 0 이하로 떨어짐 => 공격 못하는 상황
                if (card > 0 && !pq.isEmpty()) { // 무적권이 남아 있고 && 적이 남아 있음
                    my += pq.poll(); // 가장 강한 적을 무적권으로 제거 -> pq를 내림차순으로 정렬했으므로 가장 강한 적이 poll됨
                    card--; // 무적권 사용
                } else { // 게임 끝!
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }

}
