package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// 7490. 0 만들기 (672ms)
public class BOJ_7490 {
    static int N;
    static String[] op = {"+", "-", " "};
    static List<String> answer;

    private static void dfs(int num, String str) {
        if (num > N) {
            if (calculate(str)) answer.add(str);
            return;
        }
        for (int i = 0; i < 3; i++) {
            dfs(num + 1, str + op[i] + num);
        }
    }

    private static boolean calculate(String str) {
        String[] output = str.split("(?<=[-+*/])|(?=[-+*/])|(?<=[0-9])(?=[-+*/])");
        if (output.length == 1) return parseInteger(output[0]) == 0;
        int a = parseInteger(output[0]);
        for (int i = 1; i < output.length- 1; i += 2) {
            String op = output[i];
            int b = parseInteger(output[i + 1]);
            if (Objects.equals(op, "+")) {
                a += b;
            } else {
                a -= b;
            }
        }
        return a == 0;
    }

    private static int parseInteger(String str) {
        return Integer.parseInt(str.replaceAll(" ", ""));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            answer = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            dfs(2, "1");
            Collections.sort(answer);
            for (String str : answer) sb.append(str).append("\n");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
