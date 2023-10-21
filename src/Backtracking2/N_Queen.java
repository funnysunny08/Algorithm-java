package Backtracking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9663번: N-Queen
public class N_Queen {

    static int N;
    static int answer;
    static int[] chess; // chess[i] = x -> x행, i열

    static void backtracking(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            chess[depth] = i;
            boolean isValid = true;

            for (int j = 0; j < depth; j++) {
                if (chess[j] == i || Math.abs(i - chess[j]) == depth - j) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) { // 체스를 둘 수 있는 상황에만 재귀 호출!
                backtracking(depth + 1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N];

        backtracking(0);
        System.out.println(answer);
    }
}
