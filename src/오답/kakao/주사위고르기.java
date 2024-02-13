package 오답.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 카카오: 주사위 고르기
public class 주사위고르기 {

    static int n; // 주사위 총 개수
    static List<Integer> selected = new ArrayList<>(); // A가 고른 주사위 번호 저장
    static int[] answer; // 주사위의 최강 조합 저장
    static int max = Integer.MIN_VALUE; // 승
    static List<Integer> aList;
    static List<Integer> bList;
    static int[][] Dice;

    public static void dfs(int depth, int start) {
        if (depth == n / 2) {
            int winning = getWinningRate();
            if (winning > max) {
                for (int i = 0; i < n / 2; i++) {
                    answer[i] = selected.get(i) + 1;
                }
                max = winning;
            }
            return;
        }
        for (int i = start; i < n; i++) {
            selected.add(i);
            dfs(depth + 1, i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static void makeList(int depth, int[] diceNumber, int sum, List<Integer> list) {
        if (depth == n / 2) {
            list.add(sum);
            return;
        }
        for (int x : Dice[diceNumber[depth]]) {
            makeList(depth + 1, diceNumber, sum + x, list);
        }
    }

    public static int getWinningRate() {
        aList = new ArrayList<>();
        bList = new ArrayList<>();
        int[] selectedA = new int[n / 2];
        int[] selectedB = new int[n / 2];

        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (selected.contains(i)) {
                selectedA[a] = i;
                a++;
            } else {
                selectedB[b] = i;
                b++;
            }
        }

        makeList(0, selectedA, 0, aList);
        makeList(0, selectedB, 0, bList);

        int count = 0;
        Collections.sort(bList);
        for (int i = 0; i < aList.size(); i++) {

            int left = 0;
            int right = bList.size() - 1;
            int index = Integer.MIN_VALUE;

            while (left <= right) {
                int middle = (left + right) / 2;

                if (bList.get(middle) < aList.get(i)) {
                    index = Math.max(index, middle);
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            if (index != Integer.MIN_VALUE) {
                count += index + 1;
            }
        }
        return count;
    }

    public static int[] solution(int[][] dice) {
        n = dice.length;
        Dice = dice;
        answer = new int[n /2];
        dfs(0, 0);
        return answer;
    }

    public static void main(String[] args) {
//        int[][] d = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
//        int[][] d = {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}};
        int [][] d = {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}};
        int[] a = solution(d);
        for (int x : a) {
            System.out.println(x);
        }
    }
}
