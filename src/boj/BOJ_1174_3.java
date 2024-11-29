package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1174_3 {
    static List<Long> list = new ArrayList<>(); // 줄어드는 수 저장
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    private static void dfs(long num, int idx) {
        if (idx >= 10) {
            list.add(num);
            return;
        }
        dfs(num, idx + 1);
        dfs((num * 10) + arr[idx], idx + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dfs(0L, 0);
        list.sort(null);

        if (n >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n));
        }
    }
}
