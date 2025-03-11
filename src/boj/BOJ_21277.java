package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21277. 짠돌이 호석
public class BOJ_21277 {
    static int N1, M1, N2, M2;
    static boolean[][] puzzle1, puzzle2;
    static int answer;

    private static void solve() {
        for (int i = -N2 + 1; i < N1; i++) {
            for (int j = -M2 + 1; j < M1; j++) {
                check(i, j);
            }
        }
    }

    private static void check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (canPutTogether(x, y)) {
                int n = Math.max(N1, x + puzzle2.length) - Math.min(0, x);
                int m = Math.max(M1, y + puzzle2[0].length) - Math.min(0, y);
                answer = Math.min(answer, n * m);
            }
            rotate();
        }
    }

    private static void rotate() {
        int n = puzzle2[0].length;
        int m = puzzle2.length;
        boolean[][] newPuzzle = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newPuzzle[i][j] = puzzle2[puzzle2.length - j - 1][i];
            }
        }
        puzzle2 =  newPuzzle;
    }

    private static boolean canPutTogether(int x, int y) {
        for (int i = 0; i < puzzle2.length; i++) {
            for (int j = 0; j < puzzle2[0].length; j++) {
                if (!puzzle2[i][j] || x + i >= N1 || y + j >= M1 || x + i < 0 || y + j < 0) continue;
                if (puzzle1[x + i][y + j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());
        puzzle1 = new boolean[N1][M1];
        for (int i = 0; i < N1; i++) {
            String str = br.readLine();
            for (int j = 0; j < M1; j++) {
                if (str.charAt(j) == '1') puzzle1[i][j] = true;
            }
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        puzzle2 = new boolean[N2][M2];
        for (int i = 0; i < N2; i++) {
            String str = br.readLine();
            for (int j = 0; j < M2; j++) {
                if (str.charAt(j) == '1') puzzle2[i][j] = true;
            }
        }

        answer = Math.min(Math.max(N1, N2) * (M1 + M2), (Math.max(N1, M2) * (M1 + N2)));
        solve();
        System.out.println(answer);
    }
}
