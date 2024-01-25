package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2590번: 색종이
public class BOJ_2590 {

    static int[] arr = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 6; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int answer = arr[6]; // 6번 색종이 부착
        while (arr[5] > 0) { // 5번 색종이 부착
            if (arr[1] > 0) {
                arr[1] -= 11;
            }
            arr[5]--;
            answer++;
        }

        while (arr[4] > 0) { // 4번 색종이 부착
            if (arr[2] > 0 || arr[1] > 0) {
                int blank = 20;
                if (arr[2] > 0) {
                    if (arr[2] >= 5) {
                        blank -= 4 * 5;
                    } else {
                        blank -= 4 * arr[2];
                    }
                    arr[2] -= 5;
                }
                if (arr[1] > 0) {
                    arr[1] -= blank;
                }
            }
            arr[4]--;
            answer++;
        }

        // 3번 색종이 부착
        answer += arr[3] / 4;
        arr[3] = arr[3] % 4;
        if (arr[3] > 0) {
            int blank = 36 - arr[3] * 9;
            if (arr[2] > 0 || arr[1] > 0) {
                if (arr[2] > 0) {
                    if (arr[3] == 1) { // 2번 색종이 5개 붙일 수 있음
                        if (arr[2] >= 5) {
                            blank -= 4 * 5;
                        } else {
                            blank -= 4 * arr[2];
                        }
                        arr[2] -= 5;
                    } else if (arr[3] == 2) { // 2번 색종이 3개 붙일 수 있음
                        if (arr[2] >= 3) {
                            blank -= 4 * 3;
                        } else {
                            blank -= 4 * arr[2];
                        }
                        arr[2] -= 3;
                    } else if (arr[3] == 3) { // 2번 색종이 1개 붙일 수 있음
                        arr[2]--;
                        blank -= 4;
                    }
                }
                if (arr[1] > 0) {
                    arr[1] -= blank;
                }
            }
            answer++;
        }

        // 2번 색종이 부착
        if (arr[2] > 0) {
            answer += arr[2] / 9;
            arr[2] %= 9;
            if (arr[2] > 0) {
                int blank = 36 - arr[2] * 4;
                if (arr[1] > 0) {
                    arr[1] -= blank;
                }
                answer++;
            }
        }

        // 1번 색종이 부착
        if (arr[1] > 0) {
            answer += arr[1] / 36;
            arr[1] %= 36;
            if (arr[1] > 0) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
