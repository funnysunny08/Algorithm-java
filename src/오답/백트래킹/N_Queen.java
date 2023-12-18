package 오답.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9663번: N-Queen
public class N_Queen {

    /*
    * 퀸은 어차피 하나의 행(열)에 하나밖에 못 둠! => 1차원 배열로 표현
    * arr[] = {0, 3, 1, 2} => (0, 0), (1, 3), (2, 1), (3, 2)
     */

    static int N;
    static int answer = 0;
    static int[] chess;

    public static void dfs(int L) {
        if (L == N) {
            answer++;
        } else {
            for (int i = 0; i < N; i++) {
                chess[L] = i;
                boolean isValid = true;

                for (int j = 0; j < L; j++) {
                    if (i == chess[j] || Math.abs(i - chess[j]) == L - j) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) dfs(L + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N];
        dfs(0);
        System.out.println(answer);
    }
}
