package 기출.카카오;

import java.util.Stack;

public class 괄호변환 {

    public static String solution(String p) {
        return makeCompleteString(p);
    }

    public static String makeCompleteString(String str) {
        if (str.length() == 0) return str; // 빈 문자열인 경우 그냥 반환!
        if (isGoodBracket(str)) return str; // 올바른 괄호 문자열인 경우 그냥 반환!

        int idx = slice(str); // u를 최소 단위의 균형잡인 괄호 문자열이 될 수 있도록 자른다!
        String u = str.substring(0, idx + 1);
        String v = str.substring(idx + 1);

        v = makeCompleteString(v); // v에 대해서는 전체 과정 반복!

        if (!isGoodBracket(u)) { // u가 올바른 괄호 문자열이 아니라면,
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(v).append(')');
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') sb.append(')');
                else sb.append('(');
            }
            return sb.toString();
        } else { // u가 올바른 괄호 문자열이라면 u + v(올바른 괄호 문자열 과정 끝마치고 옴!) 반환
            return u + v;
        }
    }

    public static boolean isGoodBracket(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (stack.empty()) return false;
                else stack.pop();
            }
        }
        return stack.empty();
    }

    public static int slice(String p) {
        int idx = 0;
        int left = 0, right = 0;
        for (; idx < p.length(); idx++) {
            if (p.charAt(idx) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) break;
        }
        return idx;
    }


    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(solution(p));
    }
}
