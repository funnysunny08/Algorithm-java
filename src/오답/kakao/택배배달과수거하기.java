package 오답.kakao;

import java.util.ArrayList;
import java.util.List;

// 카카오: 택배 배달과 수거하기
public class 택배배달과수거하기 {

    static List<Integer> d = new ArrayList<>();
    static List<Integer> p = new ArrayList<>();

    public static void removeZero(List<Integer> list) { // 뒤에서부터 무의미한 0을 제거한다.
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != 0) {
                break;
            }
            list.remove(i);
        }
    }

    public static void removeByCap(List<Integer> list, int cap) {
        for (int i = list.size() - 1; i >= 0 && cap > 0; i--) {
            if (list.get(i) < cap) {
                cap -= list.get(i);
                list.set(i, 0);
            } else {
                list.set(i, list.get(i) - cap);
                break;
            }
        }
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        for (int delivery : deliveries) {
            d.add(delivery);
        }
        for (int pickup : pickups) {
            p.add(pickup);
        }

        removeZero(d);
        removeZero(p);

        long answer = 0;
        long dist = Math.max(d.size(), p.size());

        while (dist > 0) {
            answer += dist * 2;

            removeByCap(d, cap);
            removeByCap(p, cap);

            removeZero(d);
            removeZero(p);

            dist = Math.max(d.size(), p.size());
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] d = {1, 0, 3, 1, 2};
        int[] p = {0, 3, 0, 4, 0};
        System.out.println(solution(4, 5, d, p));

    }
}
