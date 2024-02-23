package 기출.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 카카오: n + 1 카드게임
public class Nplus1카드게임 {

    static int n;
    static List<Integer> a = new ArrayList<>(); // 손에 남은 카드 배열
    static List<Integer> b = new ArrayList<>(); // 현재 라운까지 뽑은 카드 중 아직 가져오지 않은 카드를 담은 배열

    public static boolean canMakeNPlusOne(List<Integer> list) {
        if (list.isEmpty()) return false;
        Collections.sort(list);
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int sum = list.get(left) + list.get(right);
            if (sum == n + 1) {
                list.remove(right);
                list.remove(left);
                return true;
            } else if (sum > n + 1) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public static int solution(int coin, int[] cards) {
        n = cards.length;
        for (int i = 0; i < n / 3; i++) {
            a.add(cards[i]);
        }

        int round = 0;
        L:
        while (true) {
            int index = n / 3 + round * 2;
            if (round > n / 3 + 1 || index >= n) {
                break;
            }

            b.add(cards[index]);
            b.add(cards[index + 1]);

            // 1. a에서 2개 사용
            if (canMakeNPlusOne(a)) {
                round++;
                continue;
            }

            // 2. a에서 1개, b에서 1개 사용
            if (coin > 0) {
                for (int i = 0; i < a.size(); i++) {
                    for (int j = 0; j < b.size(); j++) {
                        if (a.get(i) + b.get(j) == n + 1) {
                            coin -= 1;
                            a.remove(i);
                            b.remove(j);
                            round++;
                            continue L;
                        }
                    }
                }
            }

            // 3. b에서 2개 사용
            if (coin > 1) {
                if (canMakeNPlusOne(b)) {
                    coin -= 2;
                    round++;
                    continue;
                }
            }
            break;
        }
        return round + 1;
    }

    public static void main(String[] args) {
//        int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
        int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};
        System.out.println(solution(3, cards));

    }

}
