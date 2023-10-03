package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 2493번: 탑
public class 탑 {

    static class Tower {

        int index;
        int height;

        Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        //
        Stack<Tower> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            boolean check = false;

            while (!stack.isEmpty()) {
                if (num <= stack.peek().height) {
                    sb.append(stack.peek().index).append(" ");
                    check = true;
                    break;
                }
                stack.pop();
            }
            if (!check) sb.append("0 ");
            stack.push(new Tower(i + 1, num));
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
