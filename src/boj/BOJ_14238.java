package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 14238번: 출근 기록
public class BOJ_14238 {

    static int[] cnt = new int[3]; // C: 0, B: 1, A: 2
    static Character[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'C') cnt[0]++;
            else if (ch == 'B') cnt[1]++;
            else cnt[2]++;
        }

        arr = new Character[str.length()];

        int idx = 1;
        if (str.length() == 2 * (cnt[0] - 1) + cnt[0]) idx = 0;
        if (str.length() < 2 * (cnt[0] - 1) + cnt[0]) {
            System.out.println(-1);
            System.exit(0);
        }
        for (int i = 0; i < cnt[0]; i++) {
            arr[idx] = 'C';
            idx += 3;
        }

        idx = 0;
        boolean chk = true;
        for (int i = 0; i < cnt[1]; i++) {
            while (true) {
                if (idx >= str.length()) {
                    chk = false;
                    break;
                }
                if (arr[idx] != null) {
                    idx++;
                    continue;
                }
                if (idx > 0 && arr[idx - 1] != null && arr[idx - 1] == 'B') {
                    idx++;
                    continue;
                }
                arr[idx] = 'B';
                idx++;
                break;
            }
        }
        if (!chk) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] == null ? 'A' : arr[i]);
            }
        }

    }
}
