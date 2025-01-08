package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// 12933. 오리
public class BOJ_12933 {
    static HashMap<Character, Integer> hm = new HashMap<>();
    static int cnt = -1;
    static char[] arr = {'q', 'u', 'a', 'c', 'k'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (char c : arr) hm.put(c, 0);

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hm.put(ch, hm.get(ch) + 1);
            if (!check()) {
                System.out.println(-1);
                return;
            }
            if (ch == 'k') remove();
        }
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int sum = 0;
        for (char c : arr) sum += hm.get(c);
        if (sum != 0) return -1;
        else return cnt;
    }

    private static void remove() {
        for (char c : arr) hm.put(c, hm.get(c) - 1);
    }

    private static boolean check() {
        int prev = hm.get(arr[0]);
        cnt = Math.max(prev, cnt);
        for (int i = 1; i < arr.length; i++) {
            int now = hm.get(arr[i]);
            if (prev < now) return false;
            prev = now;
        }

        return true;
    }
}
