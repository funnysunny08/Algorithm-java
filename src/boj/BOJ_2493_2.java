package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 2493. 탑
public class BOJ_2493_2 {
    static class Top {
        int idx, height;

        public Top(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Top> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            Top newTop = new Top(i, Integer.parseInt(st.nextToken()));
            boolean find = false;
            while (!stack.isEmpty()) {
                Top now = stack.pop();
                if (now.height > newTop.height) {
                    find = true;
                    stack.push(now);
                    sb.append(now.idx).append(" ");
                    break;
                }
            }
            stack.push(newTop);
            if (!find) sb.append("0 ");
        }
        System.out.println(sb);
    }
}
