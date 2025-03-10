package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 2877. 4와 7
public class BOJ_2877 {
    static int len;
    static List<String> list = new ArrayList<>();

    private static void dfs(int idx, String str) {
        if (idx == len) {
            list.add(str);
            return;
        }
        dfs(idx + 1, str + "4");
        dfs(idx + 1, str + "7");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        int two = 2;
        len = 1;
        while (true) {
            if (K <= two) break;
            K -= two;
            two *= 2;
            len++;
        }
        if (K == 0) {
            System.out.println(Integer.parseInt("7".repeat(len)));
            System.exit(0);
        }

        String binary = Integer.toBinaryString(K - 1);
        int diff = len - binary.length();
        StringBuilder sb = new StringBuilder();
        sb.append("4".repeat(diff));
        for (char ch : binary.toCharArray()) {
            if (ch == '0') sb.append('4');
            else sb.append('7');
        }
        System.out.println(sb);
    }
}
