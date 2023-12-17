package 오답.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2493번: 탑
public class 탑 {

    static class Top {
        int idx;
        int height;
        Top (int idx, int height) {
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
            int x = Integer.parseInt(st.nextToken());
            boolean chk = false;
            while (!stack.isEmpty()) {
                Top top = stack.pop();
                if (top.height > x) {
                    chk = true;
                    sb.append(top.idx + " ");
                    stack.push(top);
                    break;
                }
            }
            if (!chk) sb.append("0 "); // 수신하지 못한 경우
            stack.push(new Top(i, x));
        }
        System.out.println(sb);
    }
}
