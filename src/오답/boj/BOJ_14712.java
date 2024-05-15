package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14712번: 넴모넴모 (Easy)
public class BOJ_14712 {

    static int N, M;
    static int answer;
    static int[][] map;

    public static void dfs(int x, int y) {
        if (x == N && y == M) {
            // 넴모를 배치하지 않음
            answer++;
            // 넴모를 배치할 수 있는지 확인
            map[x][y] = 1;
            if (!check(x, y)) answer++;
            map[x][y] = 0;
            return;
        }
        int nx, ny;
        if (y == M) {
            nx = x + 1;
            ny = 1;
        } else {
            nx = x;
            ny = y + 1;
        }

        // 넴모를 배치하지 않음
        dfs(nx, ny);

        // 넴모를 배치함
        map[x][y] = 1;
        if (!check(x, y)) dfs(nx, ny);
        map[x][y] = 0;
    }

    private static boolean check(int x, int y) { // (x, y)를 기준으로 2 x 2 사각형이 생기는지 체크
        if (map[x - 1][y - 1] + map[x - 1][y] + map[x][y - 1] + map[x][y] == 4 ||
            map[x - 1][y] + map[x - 1][y + 1] + map[x][y] + map[x][y + 1] == 4 ||
            map[x][y - 1] + map[x][y] + map[x + 1][y - 1] + map[x + 1][y] == 4 ||
            map[x][y] + map[x][y + 1] + map[x + 1][y] + map[x + 1][y + 1] == 4
        ) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];

        dfs(1, 1);
        System.out.println(answer);
    }
}
