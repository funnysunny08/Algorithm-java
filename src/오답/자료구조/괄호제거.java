package 오답.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// 백준 2800번: 괄호 제거
public class 괄호제거 {
    /*
    * 1. 괄호 쌍을 구한다. -> 스택 활용
    * 2. DFS를 이용하여 괄호를 제거하는 모든 경우 구하기
    * */
    static String str;
    static List<int[]> brackets = new ArrayList<>();
    static boolean[] selected;
    static List<String> answer = new ArrayList<>();

    public static void dfs(int L) {
        if (L == brackets.size()) {
            boolean check = false;
            StringBuilder copy = new StringBuilder(str);
            for (int i = 0; i < brackets.size(); i++) {
                if (!selected[i]) {
                    check = true;
                    copy.setCharAt(brackets.get(i)[0], ' ');
                    copy.setCharAt(brackets.get(i)[1], ' ');
                }
            }
            if (check) // 괄호가 한 번이라도 제거가 됐다면!
                answer.add(copy.toString().replaceAll(" ", ""));
        } else {
            dfs(L + 1);
            selected[L] = true;
            dfs(L + 1);
            selected[L] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        // 괄호쌍 찾기
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                st.push(i);
            } else if (str.charAt(i) == ')') {
                brackets.add(new int[]{st.pop(), i});
            }
        }

        selected = new boolean[brackets.size()];
        dfs(0);
        Collections.sort(answer);
        for (String a : answer) {
            System.out.println(a);
        }
    }
}
