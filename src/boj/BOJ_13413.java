package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 13413번: 오셀로 재배치
public class BOJ_13413 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();
            String target = br.readLine();

            int w = 0;
            int b = 0;

            for (int i = 0; i < N; i++) {
                if (input.charAt(i) != target.charAt(i)) {
                    if (target.charAt(i) == 'W') w++;
                    else b++;
                }
            }

            sb.append(Math.max(w, b)).append("\n");
        }
        System.out.println(sb);
    }
}
