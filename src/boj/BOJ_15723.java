package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 15723. n단 논법
public class BOJ_15723 {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();

    private static String check(int a, int b) {
        int start = a;
        while (true) {
            if (graph.get(start).isEmpty()) break;
            int next = graph.get(start).get(0);
            if (next == b) return "T";
            start = next;
        }
        return "F";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 26; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            char[] strArr = br.readLine().toCharArray();
            int a = strArr[0] - 'a' + 1;
            int b = strArr[strArr.length - 1] - 'a' + 1;
            graph.get(a).add(b);
        }

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            char[] strArr = br.readLine().toCharArray();
            int a = strArr[0] - 'a' + 1;
            int b = strArr[strArr.length - 1] - 'a' + 1;
            sb.append(check(a, b)).append("\n");
        }
        System.out.println(sb);
    }
}
