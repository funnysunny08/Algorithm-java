package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9925번: 문자열 폭발
public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String explosionStr = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            if (stack.size() < explosionStr.length()) continue;

            boolean isSame = true;
            for (int j = 0; j < explosionStr.length(); j++) {
                if (stack.get(stack.size() - explosionStr.length() + j) != explosionStr.charAt(j)) {
                    isSame = false;
                    break;
                }
            }

            if (isSame) {
                for (int j = 0; j < explosionStr.length(); j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}
