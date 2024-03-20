package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15684번: 사다리 조작
public class BOJ_15684 {

    static int N, M, H; // 세로선, 가로선, 높이
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        if (isCorrect()) {
            System.out.println(0);
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            if (dfs(0, i, 0, 0)) {
                System.out.println(i);
                System.exit(0);
            }
        }
        System.out.println(-1);
    }

    public static boolean dfs(int depth, int target, int r, int c) {
        if (depth == target) {
            if (isCorrect()) return true;
            return false;
        }

        while (getNext(r, c) != null) {
            int[] next = getNext(r, c);
            r = next[0];
            c = next[1];
            if (canPut(r, c)) {
                ladder[r][c] = true;
                if (dfs(depth + 1, target, r, c)) return true;
                ladder[r][c] = false;
            }
        }
        return false;
    }

    public static boolean canPut(int r, int c) {
        if (ladder[r][c]) return false;
        if (c == 1) {
            return !ladder[r][c + 1];
        } else if (c == N) {
            return !ladder[r][c - 1];
        } else {
            return (!ladder[r][c - 1]) && (!ladder[r][c + 1]);
        }
    }

    public static int[] getNext(int r, int c) {
        if (r == 0 && c == 0) return new int[]{1, 1};
        else if (r == H && c == N) return null;
        else if (c == N) return new int[]{r + 1, 1};
        else return new int[]{r, c + 1};
    }

    public static boolean isCorrect() {
        for (int col = 1; col <= N; col++) {
            int c = col;
            for (int r = 1; r <= H; r++) {
                if (c < N && ladder[r][c]) {
                    c++;
                } else if (c > 1 && ladder[r][c - 1]) {
                    c--;
                }
            }
            if (c != col) return false;
        }
        return true;
    }
}
