package Backtracking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 14888번 - 연산자 끼워넣기
public class 연산자끼워넣기 {

    static int N;
    static int[] arr;
    static boolean[] visit;
    static ArrayList<Character> operator = new ArrayList<>();
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static char[] permutation;

    static void backtracking(int depth) {
        if (depth == N - 1) { // permutation 배열에 N-1개 연산자 다 배정!
            calculate();
            return;
        }
        for (int i = 0; i < N - 1; i++) {
            if (!visit[i]) { // 선택되지 않은 연산자만 고르게 함!
                visit[i] = true; // 방문처리
                permutation[depth] = operator.get(i);
                backtracking(depth + 1);
                visit[i] = false; // 방문처리 취소
            }
        }
    }

    static void calculate() {
        int result = arr[0];
        for (int i = 0; i < N - 1; i++) {
            if (permutation[i] == '+') {
                result += arr[i + 1];
            } else if (permutation[i] == '-') {
                result -= arr[i + 1];
            } else if (permutation[i] == '*') {
                result *= arr[i + 1];
            } else if (permutation[i] == '/') {
                result /= arr[i + 1];
            }
        }
        max = Math.max(max, result);
        min = Math.min(min, result);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int multiply = Integer.parseInt(st.nextToken());
        int divide = Integer.parseInt(st.nextToken());
        for (int i = 0; i < plus; i++)
            operator.add('+');

        for (int i = 0; i < minus; i++)
            operator.add('-');

        for (int i = 0; i < multiply; i++)
            operator.add('*');

        for (int i = 0; i < divide; i++)
            operator.add('/');

        permutation = new char[N - 1];
        visit = new boolean[N];
        backtracking(0);
        System.out.println(max);
        System.out.println(min);
    }

}
