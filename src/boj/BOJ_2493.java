package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    static class Top {
        int i, h;
        public Top(int i, int h) {
            this.i = i;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int h = Integer.parseInt(st.nextToken()); // 현재 탑의 높이
            boolean find = false;
            while (!stack.isEmpty()) { // 앞에 있던 탑들을 탐색
                Top top = stack.pop();
                if (top.h > h) { // 현재 탑보다 높은 탑 발견
                    sb.append(top.i).append(" ");
                    find = true;
                    stack.push(top); // 현재 탑보다 낮은 탑들은 버리고, 꺼낸 높은 탑만 다시 스택에 push
                    break;
                }
            }
            if (!find) sb.append("0 ");
            stack.push(new Top(i, h)); // 현재 탑 스택에 push
        }
        System.out.println(sb.toString());
    }
}
