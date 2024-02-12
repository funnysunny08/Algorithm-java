package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 백준 18428번: 감시 피하기
public class BOJ_18428 {

    static int N;
    static String[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean answer = false;

    public static void dfs(int depth, int x, int y) {
        if (depth == 3) {
            if (chk()) answer = true;
            return;
        }

        int[] next = getNext(x, y);
        if (next == null) return;
        if (Objects.equals(map[x][y], "X")) {
            map[x][y] = "O";
            dfs(depth + 1, next[0], next[1]);
            map[x][y] = "X";
        }
        dfs(depth, next[0], next[1]);
    }

    public static int[] getNext(int x, int y) {
        if (y == N - 1 && x == N - 1) return null;
        if (y == N - 1) {
            return new int[]{x + 1, 0};
        } else {
            return new int[]{x, y + 1};
        }
    }

    public static boolean chk() {
        for (int[] teacher : teachers) {
            for (int i = 0; i < 4; i++) {
                int x = teacher[0];
                int y = teacher[1];
                while (true) {
                    x += dx[i];
                    y += dy[i];
                    if (x < 0 || y < 0 || x >= N || y >= N || Objects.equals(map[x][y], "O")) break;
                    if (Objects.equals(map[x][y], "S")) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.split(" ");
            for (int j = 0; j < N; j++) {
                if (Objects.equals(map[i][j], "T")) teachers.add(new int[]{i, j});
            }
        }

        dfs(0, 0, 0);
        System.out.println(answer ? "YES" : "NO");
    }
}
