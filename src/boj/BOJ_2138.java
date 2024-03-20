package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2138번: 전구와 스위치
public class BOJ_2138 {

    static int N;
    static int[] original, target;
    static int[] arrA, arrB;
    static int cntA = 1, cntB = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        original = new int[N];
        target = new int[N];
        String str = br.readLine();
        for (int i = 0; i < N; i++) original[i] = str.charAt(i) - '0';
        str = br.readLine();
        for (int i = 0; i < N; i++) target[i] = str.charAt(i) - '0';

        arrA = new int[N]; // 0번 스위치 누르고 시작
        arrB = new int[N]; // 0번 스위치 누르지 않고 시작
        for (int i = 0; i < N; i++) {
            if (i <= 1) {
                arrA[i] = original[i] == 1 ? 0 : 1;
                arrB[i] = original[i];
            } else {
                arrA[i] = original[i];
                arrB[i] = original[i];
            }
        }

        for (int i = 1; i < N; i++) {
            if (arrA[i - 1] != target[i - 1]) {
                for (int j = i - 1; j <= i + 1 && j < N; j++) {
                    arrA[j] = arrA[j] == 1 ? 0 : 1;
                }
                cntA++;
            }

            if (arrB[i - 1] != target[i - 1]) {
                for (int j = i - 1; j <= i + 1 && j < N; j++) {
                    arrB[j] = arrB[j] == 1 ? 0 : 1;
                }
                cntB++;
            }

            if (Arrays.equals(arrA, target)) {
                if (Arrays.equals(arrA, arrB)) {
                    System.out.println(Math.min(cntA, cntB));
                    System.exit(0);
                }
                System.out.println(cntA);
                System.exit(0);
            } else if (Arrays.equals(arrB, target)) {
                System.out.println(cntB);
                System.exit(0);
            }
        }

        System.out.println(-1);
    }
}
