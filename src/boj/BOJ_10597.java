package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10597. 순열장난
public class BOJ_10597 {
    static int N;
    static String answer;
    static boolean[] visited;

    private static boolean backTracking(String newStr, String original) {
        if (original.length() == 0) {
            answer = newStr;
            return true;
        }

        if (original.charAt(0) == '0') return false;


        int one = Integer.parseInt(original.substring(0, 1));
        if (!visited[one]) {
            visited[one] = true;
            if (backTracking(newStr + original.charAt(0) + " ", original.substring(1))) return true;
            visited[one] = false;
        }

        if (original.length() >= 2 && Integer.parseInt(original.substring(0, 2)) <= N && !visited[Integer.parseInt(original.substring(0, 2))]) {
            int two = Integer.parseInt(original.substring(0, 2));
            visited[two] = true;
            if (backTracking(newStr + original.substring(0, 2) + " ", original.substring(2))) return true;
            visited[two] = false;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.length() < 10) {
            N = str.length();
        } else {
            N = (str.length() - 9) / 2 + 9;
        }
        visited = new boolean[N + 1];
        backTracking("", str);
        System.out.println(answer);
    }
}
