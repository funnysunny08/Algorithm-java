package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 16719. ZOAC
public class BOJ_16719 {
    static boolean[] visited;
    static String input;
    static StringBuilder sb = new StringBuilder();

    private static void zoac(int left, int right) {
        if (left > right) return;

        int index = left;
        for (int i = left; i <= right; i++) {
            if (input.charAt(i) < input.charAt(index)) index = i;
        }
        visited[index] = true;
        for (int i = 0; i < input.length(); i++) {
            if (visited[i]) sb.append(input.charAt(i));
        }
        sb.append("\n");

        zoac(index + 1, right); // 선택된 문자의 뒷부분부터 탐색해야 함
        zoac(left, index - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        visited = new boolean[input.length()];
        zoac(0, input.length() - 1);
        System.out.println(sb);
    }
}
