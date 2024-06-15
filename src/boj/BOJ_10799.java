package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 10799번: 쇠막대기
public class BOJ_10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int answer = 0;
        int top = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' && str.charAt(i + 1) == ')') { // 레이저인 경우
                answer += top;
                i++;
            } else if (str.charAt(i) == '(') {
                top++;
            } else {
                top--;
                answer++;
            }
        }
        System.out.println(answer);
    }
}
