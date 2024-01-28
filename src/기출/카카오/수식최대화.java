package 기출.카카오;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

// 카카오: 수식 최대화
public class 수식최대화 {

    static String[] operator = new String[3];
    static String[] type = {"+", "-", "*"};
    static boolean[] visited = new boolean[3];
    static long answer = Long.MIN_VALUE;

    public static void dfs(int depth, String expression) { // 연산자에 우선순위 부여 -> 0, 1, 2
        if (depth == 3) {
            answer = Math.max(answer, calculate(expression));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                operator[depth] = type[i];
                dfs(depth + 1, expression);
                visited[i] = false;
            }
        }
    }

    public static long calculateOne(long num1, long num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                return 0;
        }
    }

    public static String[] getExpressionArray(String str) {
        String[] ex = str.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        // -로 시작하거나, 연산자 바로 뒤에 "-"가 붙은 경우 탐색
        List<String> result = new ArrayList<>();
        int index = 0;
        if (Objects.equals(ex[0], "-")) {
            result.add("-" + ex[1]);
            index = 2;
        }
        for (; index < ex.length; index++) {
            if (ex[index].length() == 2 && ex[index].charAt(1) == '-') {
                result.add(String.valueOf(ex[index].charAt(0)));
                result.add("-" + ex[++index]);
            } else {
                result.add(ex[index]);
            }
        }
        return result.toArray(new String[0]);
    }

    public static long calculate(String expression) {
        String[] ex = getExpressionArray(expression);
        Stack<String> stack;
        for (int i = 0; i < 3; i++) {
            String target = operator[i]; // target 연산자만 계산하겠다!

            stack = new Stack<>();

            for (int j = 0; j < ex.length; j++) {
                if (!Objects.equals(ex[j], target)) {
                    stack.push(ex[j]);
                } else {
                    long num1 = Integer.parseInt(stack.pop());
                    long result = calculateOne(num1, Long.parseLong(ex[j + 1]), target);
                    stack.push(String.valueOf(result));
                    j++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String s : stack) {
                sb.append(s);
            }
            ex = getExpressionArray(sb.toString());
        }
        return Math.abs(Long.parseLong(ex[0]));
    }


    public static long solution(String expression) {
        dfs(0, expression);
        return answer;
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }
}
