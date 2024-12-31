package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 12101. 1, 2, 3 더하기 2
public class BOJ_12101_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i <= 11; i++) dp.add(new ArrayList<>());

        dp.get(1).add("1");
        dp.get(2).add("1+1");
        dp.get(2).add("2");
        dp.get(3).add("1+1+1");
        dp.get(3).add("1+2");
        dp.get(3).add("2+1");
        dp.get(3).add("3");

        for (int i = 4; i<= n; i++) {
            for (int d = 1; d <= 3; d++) {
                for (int j = 0; j < dp.get(i - d).size(); j++) {
                    dp.get(i).add(d + "+" + dp.get(i - d).get(j));
                }
            }
        }
        if (dp.get(n).size() < k) {
            System.out.println(-1);
            return;
        }
//        Collections.sort(dp.get(n));
        System.out.println(dp.get(n).get(k - 1));
    }
}
