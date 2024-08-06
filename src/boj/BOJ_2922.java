package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2922번: 즐거운 단어
public class BOJ_2922 {

    static String vowel = "AEIOU";
    static int blankCnt;
    static char[] arr;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        for (int i = 0; i < arr.length; i++) if (arr[i] == '_') blankCnt++;
        findBlank(0, 0);
        System.out.println(answer);
    }

    public static void findBlank(int idx, int cnt) {
        if (cnt >= blankCnt) {
            calculate();
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            if (arr[i] == '_') {
                // 자음
                arr[i] = '1';
                findBlank(i + 1, cnt + 1);
                // 모음
                arr[i] = '2';
                findBlank(i + 1, cnt + 1);
                // L
                arr[i] = 'L';
                findBlank(i + 1, cnt + 1);
                // 원상 복구
                arr[i] = '_';
            }
        }
    }

    private static void calculate() {
        if (check()) {
            long total = 1;
            for (int i = 0; i < arr.length; i++) total *= arr[i] == '1' ? 20 : arr[i] == '2' ? 5 : 1;
            answer += total;
        }
    }

    private static boolean check() {
        int st = 0;
        int ed = 2;
        boolean isL = false;
        while (ed < arr.length) {
            int v = 0; // 모음 갯수
            int c = 0; // 자음 갯수
            for(int i = st; i <= ed; i++) {
                if (arr[i] == 'L') isL = true;
                if (vowel.indexOf(arr[i]) >= 0 || arr[i] == '2') v++;
                else c++;
            }

            if(v >= 3 || c >= 3) return false;
            st++;
            ed++;
        }
        // L값이 존재하며 자음 모음에 연속성 없음
        return isL;
    }
}
