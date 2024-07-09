package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1283번: 단축키 지정
public class BOJ_1283 {

    static int N;
    static boolean[] isUsed = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        L:
        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            String[] arr = word.split(" ");

            // 각 단어의 첫글자 검사
            for (int j = 0; j < arr.length; j++) {
                int idx = Character.toLowerCase(arr[j].charAt(0)) - 'a';
                if (idx >= 0 && idx < 26 && !isUsed[idx]) { // 단축키로 지정된 적 없음
                    isUsed[idx] = true;
                    for (int k = 0; k < arr.length; k++) {
                        if (k == j) {
                            sb.append("[" + arr[k].charAt(0) + "]" + arr[k].substring(1) + " ");
                        } else {
                            sb.append(arr[k] + " ");
                        }
                    }
                    sb.append("\n");
                    continue L;
                }
            }

            // 앞에서부터 모든 글자 검사
            for (int j = 0; j < word.length(); j++) {
                int idx = Character.toLowerCase(word.charAt(j)) - 'a';
                if (idx >= 0 && idx < 26 && !isUsed[idx]) { // 단축키로 지정된 적 없음
                    isUsed[idx] = true;
                    sb.append(word.substring(0, j) + '[' + word.charAt(j) + ']' + word.substring(j + 1) + "\n");
                    continue L;
                }
            }
            sb.append(word).append("\n");
        }
        System.out.println(sb);
    }
}
