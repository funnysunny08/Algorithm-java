package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 백준 1038번: 감소하는
public class BOJ_1038 {

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n <= 10) {
            System.out.println(n);
        } else if (n > 1022) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < 10; i++) bp(i, 1);
            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    public static void bp(long num, int idx) { // 주어진 숫자, 자릿수
        if (idx > 10) return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) { // 주어진 num 에서 1의 자릿수로 올 수 있는 모든 조합을 해서 dfs 보냄
        }
    }
}
