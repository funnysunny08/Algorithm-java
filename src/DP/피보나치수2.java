package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2748번: 피보나치 수 2
public class 피보나치수2 {

    static long[] d;

    public static long fibo(int n) {
        if (d[n] == -1) {
            d[n] = fibo(n - 1) + fibo(n - 2);
        }
        return d[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        d = new long[n + 1];
        Arrays.fill(d, -1);
        d[0] = 0;
        d[1] = 1;
        System.out.println(fibo(n));
    }
}
