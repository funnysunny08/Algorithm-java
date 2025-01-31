package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 21314. 민겸 수
public class BOJ_21314 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().replaceAll("K", "K,");
        String[] arr = input.split(",");

        StringBuilder min = new StringBuilder();
        StringBuilder max = new StringBuilder();

        for (String s : arr) {
            int len = s.length();
            if (!s.contains("K")) {
                min.append("1").append("0".repeat(Math.max(0, len - 1)));
                max.append("1".repeat(len));
                continue;
            }

            if (len == 1) {
                if (s.equals("K")) {
                    min.append("5");
                    max.append("5");
                } else {
                    min.append("1");
                    max.append("1");
                }
            } else {
                min.append("1");
                min.append("0".repeat(Math.max(0, len - 2)));
                min.append("5");

                max.append("5");
                max.append("0".repeat(Math.max(0, len - 1)));
            }
        }
        System.out.println(max);
        System.out.println(min);
    }
}
