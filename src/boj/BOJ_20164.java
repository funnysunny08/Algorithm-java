package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20164 {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs(String.valueOf(N), 0);
        System.out.println(min + " " + max);
    }

    private static void dfs(String number, int cnt) {
        int newCnt = getCount(number) + cnt;

        if (number.length() <= 1) {
            min = Math.min(min, newCnt);
            max = Math.max(max, newCnt);
            return;
        }

        if (number.length() == 2) {
            for (int i = 1; i < number.length(); i++) {
                String newNumber = String.valueOf(Integer.parseInt(number.substring(0, i)) + Integer.parseInt(number.substring(i)));
                dfs(newNumber, newCnt);
            }
        } else {
            for (int i = 1; i < number.length(); i++) {
                for (int j = i + 1; j < number.length(); j++) {
                    String newNumber = String.valueOf(Integer.parseInt(number.substring(0, i)) + Integer.parseInt(number.substring(i, j)) + Integer.parseInt(number.substring(j)));
                    dfs(newNumber, newCnt);
                }
            }
        }
    }

    private static int getCount(String number) {
        int cnt = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) % 2 != 0) cnt++;
        }
        return cnt;
    }
}
