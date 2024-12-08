package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1174_4 {
    static int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();

    private static void dfs(int idx, Long number) {
        if (idx >= 10) {
            list.add(number);
            return;
        }
        dfs(idx + 1, number);
        dfs(idx + 1, number * 10 + num[idx]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dfs(0, 0L);
        Collections.sort(list);
        System.out.println(list.size() <= n ? -1 : list.get(n));
    }
}
