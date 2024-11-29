package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3980_2 {
    static int[] selected = new int[11];
    static int[][] arr;
    static int max;
    private static void backtracking(int player, int score) {
        if (player >= 11) {
            max = Math.max(max, score);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (selected[i] == -1 && arr[player][i] != 0) {
                selected[i] = player;
                backtracking(player + 1, score + arr[player][i]);
                selected[i] = -1;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // 초기화
            max = Integer.MIN_VALUE;
            arr = new int[11][11];
            Arrays.fill(selected, -1);
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backtracking(0, 0);
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}
