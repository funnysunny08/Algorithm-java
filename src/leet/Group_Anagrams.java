package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 49. Group Anagrams
public class Group_Anagrams {

    /*
    strs를 사전순으로 정렬
    HashMap<String, List<String>>
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hm = new HashMap<>();
        for (String str : strs) {
            String key = getSortedString(str);
            if (!hm.containsKey(key)) hm.put(key, new ArrayList<>());
            hm.get(key).add(str);
        }
        return new ArrayList<>(hm.values());
    }

    private static String getSortedString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static void main(String[] args) {

    }
}
