package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 5904번: Moo 게임
public class BOJ_5904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int k = 0;
        int size = 3;
        while (N > size) {
            k++;
            size = size * 2 + (k + 3);
        }
        findMoo(k, size, N);
    }

    public static void findMoo(int k, int size, int N) {
        int newMoo = k + 3;

        if (k == 0) {
            if (N == 1) {
                System.out.println("m");
            } else {
                System.out.println("o");
            }
            return;
        }

        if (N <= (size - newMoo) / 2) {
            findMoo(k - 1, (size - newMoo) / 2, N);
        } else if (N > (size - newMoo) / 2 && N <= (size - newMoo) / 2 + newMoo) {
            if (N == (size - newMoo) / 2 + 1) {
                System.out.println("m");
            } else {
                System.out.println("o");
            }
        } else {
            findMoo(k - 1, (size - newMoo) / 2, N - (size - newMoo) / 2 - newMoo);
        }
    }
}
