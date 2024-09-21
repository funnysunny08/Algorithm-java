package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 1657. Determine if Two Strings Are Close
public class Determine_If_Two_Strings_Are_Close {

    public static boolean closeStrings(String word1, String word2) {
        // 길이가 다르면 close 할 수 없음
        if (word1.length() != word2.length()) return false;

        HashMap<Character, Integer> hm1 = new HashMap<>();
        HashMap<Character, Integer> hm2 = new HashMap<>();

        // 각 문자열의 문자 빈도 수를 계산
        for (int i = 0; i < word1.length(); i++) {
            hm1.put(word1.charAt(i), hm1.getOrDefault(word1.charAt(i), 0) + 1);
            hm2.put(word2.charAt(i), hm2.getOrDefault(word2.charAt(i), 0) + 1);
        }

        if (hm1.size() != hm2.size()) return false;

        // word1, word2가 가진 char가 동일한지 확인
        Set<Character> keySet = new HashSet<>();
        keySet.addAll(hm1.keySet());
        keySet.addAll(hm2.keySet());
        if (keySet.size() != hm1.size()) return false;

        // 빈도수 확인
        int[] value1 = hm1.values().stream().mapToInt(Integer::intValue).toArray();
        int[] value2 = hm2.values().stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(value1);
        Arrays.sort(value2);

        // 정렬된 빈도 수 배열이 동일한지 확인
        return Arrays.equals(value1, value2);
    }

    public static void main(String[] args) {

    }

    private static int N = 26;
    public static boolean closeStrings2(String word1, String word2) {
        // count the English letters
        int[] arr1 = new int[N], arr2 = new int[N];
        for (char ch : word1.toCharArray())
            arr1[ch - 'a']++;
        for (char ch : word2.toCharArray())
            arr2[ch - 'a']++;

        // if one has a letter which another one doesn't have, dont exist
        for (int i = 0; i < N; i++) { // a ~ z 까지 체크!
            if (arr1[i] == arr2[i]) {
                continue;
            }
            if (arr1[i] == 0 || arr2[i] == 0) { // 둘 중 하나만 있는 문자가 있으면 바로 false 반환!
                return false;
            }
        }
        // 빈도수 동일한지 체크!
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
