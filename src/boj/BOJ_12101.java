package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 12101. 1, 2, 3 더하기 2
public class BOJ_12101 {
    static List<String> list = new ArrayList<>();

    private static void func(int num, String str) {
        if (num == 0) {
            list.add(str.substring(1));
            return;
        }
        func(num - 1, str + "+1");
        if (num >= 2) func(num - 2, str + "+2");
        if (num >= 3) func(num - 3, str + "+3");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        func(n, "");
        if (list.size() < k) {
            System.out.println(-1);
            return;
        }
        System.out.println(list.get(k - 1));
    }
}
