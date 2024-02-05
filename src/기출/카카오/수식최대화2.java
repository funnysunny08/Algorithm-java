package 기출.카카오;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class 수식최대화2 {

    static String[] priority = new String[3];
    static String[] type = {"+", "-", "*"};
    static boolean[] visited = new boolean[3];
    static long answer = Long.MIN_VALUE;
    static List<String> operators;
    static List<Long> operands;

    public static void dfs(int depth, String expression) { // 연산자에 우선순위 부여 -> 0, 1, 2
        if (depth == 3) {
            parseExpression(expression);
            long value = Math.abs(calculate());
            answer = Math.max(answer, value);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                priority[depth] = type[i];
                dfs(depth + 1, expression);
                visited[i] = false;
            }
        }
    }

    public static long calculate() {
        for (int i = 0; i < 3; i++) {
            String now = priority[i]; // 현재 계산해야 할 연산자!
            for (int j = 0; j < operators.size(); j++) {
                if (Objects.equals(operators.get(j), now)) {
                    long newNum = calculateOne(operands.get(j), operands.get(j + 1), now);
                    operators.remove(j);
                    operands.remove(j);
                    operands.remove(j);
                    operands.add(j, newNum);
                    j--;
                }
            }
        }
        return operands.get(0);
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

    public static void parseExpression(String expression) {
        operators = new LinkedList<>();
        operands = new LinkedList<>();
        String[] ex = expression.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        for (int i = 0; i < ex.length; i++) {
            if (i % 2 == 0) {
                operands.add(Long.parseLong(ex[i]));
            } else {
                operators.add(ex[i]);
            }
        }
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
