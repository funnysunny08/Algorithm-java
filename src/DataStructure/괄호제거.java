package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class 괄호제거 {

    static ArrayList<Bracket> brackets;
    static Set<String> result;
    static boolean[] check;

    static class Bracket {
        int left;
        int right;

        Bracket(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static void makeStr(int depth, char[] str) {
        if (depth == brackets.size()) {
            boolean chk = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                if (!check[i]) { // 제거하기로한 괄호 부분이 아니라면!!
                    sb.append(str[i]);
                } else { // 괄호가 한 번이라도 제거됐는지 체크하는 용도!
                    chk = true;
                }
            }
            if (chk) {
                result.add(sb.toString());
            }
            return;
        }

        makeStr(depth + 1, str); // 이번 라운드에서는 괄호 제거 안하고 넘어감!

        int left = brackets.get(depth).left;
        int right = brackets.get(depth).right;

        check[left] = true; // 괄호 제거하겠다!
        check[right] = true; // 괄호 제거하겠다!
        makeStr(depth + 1, str); // 괄호 제거한 버전으로 넘김!
        check[left] = false; // 원상복귀
        check[right] = false;
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 괄호 쌍 찾기
        brackets = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            }
            if (str.charAt(i) == ')') {
                brackets.add(new Bracket(stack.pop(), i));
            }
        }

        check = new boolean[str.length()];
        result = new TreeSet<>();
        makeStr(0, str.toCharArray());

        result.forEach(System.out::println);
    }
}
