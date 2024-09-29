package leet;

import java.util.Arrays;

// 2300. Successful Pairs of Spells and Potions
public class Successful_Pairs_of_Spells_and_Potions {

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] answer = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = m - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                long product = (long) spells[i] * potions[mid];
                if (product >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            answer[i] = m - left;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] spells = {5,1,3};
        int[] potions = {1,2,3,4,5};
        long success = 7;
        int[] answer = successfulPairs(spells, potions, success);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}
