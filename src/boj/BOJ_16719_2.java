package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 16719. ZOAC
public class BOJ_16719_2 {
    static String str;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();

    private static void zoac(int left, int right) {
        if (left > right) return;
        int idx = left;
        for (int i = left; i <= right; i++) {
            if (str.charAt(idx) > str.charAt(i)) idx = i;
        }
        selected[idx] = true;
        print();
        zoac(idx + 1, right);
        zoac(left, idx - 1);
    }

    private static void print() {
        for (int i = 0; i < str.length(); i++) if (selected[i]) sb.append(str.charAt(i));
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        selected = new boolean[str.length()];
        zoac(0, str.length() - 1);
        System.out.println(sb);
    }
}
