package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 5875번: 오타
public class BOJ_5875 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) == '(' ? 1 : -1;
        }

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -arr[i];
            if (check()) cnt++;
            arr[i] = -arr[i];
        }
        System.out.println(cnt);
    }

    public static boolean check() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum < 0) return false;
        }
        return sum == 0;
    }
}
