package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 3986번: 좋은 단어
public class 좋은단어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Stack<Character> stack;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            stack = new Stack<>();
            String str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                Character x = str.charAt(j);
                if (!stack.empty() && stack.peek() == x) {
                    stack.pop();
                    continue;
                }
                stack.push(x);
            }
            if (stack.empty()) cnt++;
        }
        System.out.println(cnt);
    }

}
