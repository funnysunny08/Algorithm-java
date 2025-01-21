package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1759. 암호 만들기
public class BOJ_1759 {
    static int L, C;
    static List<Character> jaeum = new ArrayList<>();
    static List<Character> moeum = new ArrayList<>();
    static int maxMoeum;
    static List<String> jaeumStr;
    static List<String> moeumStr;
    static StringBuilder sb = new StringBuilder();
    static List<String> answer = new ArrayList<>();

    private static void play(int moeumCnt) {
        jaeumStr = new ArrayList<>();
        moeumStr = new ArrayList<>();
        selectJaeum(0, L - moeumCnt, "");
        selectMoeum(0, moeumCnt, "");
        for (String j : jaeumStr) {
            for (String m : moeumStr) {
                char[] charArray = (j + m).toCharArray();
                Arrays.sort(charArray);
                String sortedStr = new String(charArray);
                answer.add(sortedStr);
            }
        }
    }

    private static void selectJaeum(int idx, int max, String str) {
        if (str.length() == max) {
            jaeumStr.add(str);
            return;
        }
        if (idx >= jaeum.size()) return;
        selectJaeum(idx + 1, max, str);
        selectJaeum(idx + 1, max, str + jaeum.get(idx));
    }

    private static void selectMoeum(int idx, int max, String str) {
        if (str.length() == max) {
            moeumStr.add(str);
            return;
        }
        if (idx >= moeum.size()) return;
        selectMoeum(idx + 1, max, str);
        selectMoeum(idx + 1, max, str + moeum.get(idx));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < C; i++) {
            char ch = str[i].charAt(0);
            if (isVowel(ch)) moeum.add(ch);
            else jaeum.add(ch);
        }

        maxMoeum = L - moeum.size() >= 2 ? moeum.size() : L - 2;
        for (int i = 1; i <= maxMoeum; i++) {
            play(i);
        }
        Collections.sort(answer);
        for (String a : answer) sb.append(a).append("\n");
        System.out.println(sb);
    }

    public static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1; // 모음 체크
    }
}
