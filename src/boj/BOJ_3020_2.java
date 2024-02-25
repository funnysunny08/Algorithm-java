package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3020번: 개똥벌레 (누적합)
public class BOJ_3020_2 {

    static int N, H;
    static int[] bottom;
    static int[] top;
    static int[] sumB;
    static int[] sumT;
    static int min = Integer.MAX_VALUE;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bottom = new int[H + 1];
        top = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // bottom
                bottom[h]++;
            } else { // top
                top[H - h + 1]++;
            }
        }

        sumB = new int[H + 1];
        sumT = new int[H + 1];
        // bottom 은 뒤에서부터 누적합
        sumB[H] = bottom[H];
        for (int i = H - 1; i >= 1; i--) {
            sumB[i] = sumB[i + 1] + bottom[i];
        }
        // top 은 앞에서부터 누적합
        for (int i = 1; i <= H; i++) {
            sumT[i] = sumT[i - 1] + top[i];
        }

        for (int i = 1; i <= H; i++) {
            int sum = sumB[i] + sumT[i];
            if (sum < min) {
                min = sum;
                cnt = 1;
            } else if (sum == min) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }
}
