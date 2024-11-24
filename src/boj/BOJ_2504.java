package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class BOJ_2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if (str.length() % 2 != 0) { // 괄호가 짝이 맞기 위해서는 총 개수가 짝수여야 한다.
            System.out.println(0);
            return;
        }

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            String now = Character.toString(str.charAt(i));
            if ("(".equals(now) || "[".equals(now)) { // 여는 괄호 스택에 push 하고 다음 괄호 탐색
                stack.push(now);
                continue;
            }
            String target = getTarget(now); // 현재 괄호의 짝이 되는 괄호
            int temp = 0;
            boolean findTarget = false;

            while (!stack.isEmpty()) { // 짝을 찾을 때까지 pop
                String pop = stack.pop();
                if (pop.matches("\\d+")) { // 숫자가 나왔다면 더해줌
                    temp += Integer.parseInt(pop);
                } else if (Objects.equals(pop, target)) { // 짝을 찾았으면 계산
                    if (temp == 0) temp = 1;
                    if ("(".equals(target)) temp *= 2;
                    else temp *= 3;
                    findTarget = true;
                    break;
                }
            }
            if (!findTarget) { // 스택을 다 뒤졌는데 짝을 못 찾았으면 종료
                System.out.println(0);
                return;
            }
            stack.push(String.valueOf(temp)); // 현재 괄호 범위에서 나온 값 다시 스택에 넣어줌
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            if (!pop.matches("\\d+")) {
                System.out.println(0);
                return;
            }
            sum += Integer.parseInt(pop);
        }
        System.out.println(sum);
    }

    private static String getTarget(String ch) {
        if (Objects.equals(ch, ")")) return "(";
        else return "[";
    }
}
