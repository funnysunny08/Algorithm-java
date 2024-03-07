package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3109번: 빵집
public class BOJ_3109_2 {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int answer;
    static int[] dx = {-1, 0, 1};

    public static boolean dfs(int x, int y) {
        visited[x][y] = true;
        if (y == C - 1) {
            answer++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;
            if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny] || map[nx][ny] == 'x') continue;
            if (dfs(nx, ny)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (!visited[i][0]) dfs(i, 0);
        }
        System.out.println(answer);
    }
}
