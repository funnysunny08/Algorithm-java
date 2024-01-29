package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9935번: 문자열 폭발
public class BOJ_9935 {

    static String str;
    static String bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        bomb = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            if (stack.size() >= bomb.length()) {
                boolean isSame = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size()- bomb.length() + j) != bomb.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    for (int j = 0; j < bomb.length(); j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        String answer = sb.toString();
        System.out.println(answer.length() != 0 ? answer : "FRULA");
    }
}
