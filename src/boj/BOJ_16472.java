package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 16472번: 고냥이
public class BOJ_16472 {

    static int N;
    static char[] arr;
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        length = arr.length;

        int answer = -1;
        int left = 0, right = 0;
        int type = 0;
        int[] cnt = new int[27];
        boolean isLeftMoved = false;
        while (left < length && right < length) {
            if (isLeftMoved) {
                cnt[arr[left] - 97]--;
                if (cnt[arr[left] - 97] == 0) type--;
                left++;
                isLeftMoved = false;
            } else {
                if (cnt[arr[right] - 97] == 0) type++; // 새로운 타입 추가!
                cnt[arr[right] - 97]++;
            }

            if (type <= N) {
                right++;
                answer = Math.max(answer, right - left);
            } else {
                isLeftMoved = true;
            }
        }
        System.out.println(answer);
    }
}
