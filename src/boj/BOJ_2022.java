package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2022. 사다리
public class BOJ_2022 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double X = Double.parseDouble(st.nextToken());
        double Y = Double.parseDouble(st.nextToken());
        double C = Double.parseDouble(st.nextToken());

        double left = 0, right = Math.min(X, Y);

        while (right - left >= 0.001) {
            double width = (right + left) / 2;
            double h1 = Math.sqrt(Math.pow(X, 2) - Math.pow(width, 2));
            double h2 = Math.sqrt(Math.pow(Y, 2) - Math.pow(width, 2));
            double res = h1 * h2 / (h1 + h2);

            if (res >= C) { // C 줄여야함 => width 늘려야 함
                left = width;
            } else { // 늘려야 함
                right = width;
            }
        }
        System.out.println(String.format("%.3f", right));
    }
}
