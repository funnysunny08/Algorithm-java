package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 백준 1174번: 줄어드는 수
public class BOJ_1174 {

    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dfs(0, 0);
        list.sort(null);
        if (list.size() < n) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n - 1));
        }
    }

    private static void dfs(long num, int index) {
        if (!list.contains(num)) list.add(num);

        if (index >= 10) return;

        dfs((num * 10) + arr[index], index + 1);
        dfs(num, index + 1);
    }
}
