package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3109번: 빵집
public class BOJ_3109 {

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int answer;

    public static boolean dfs(int x, int y) {
        if (y == C - 1) {
            answer++;
            return true; // 탐색을 멈추기 위한 true 반환!
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            if (!canGo(nx, ny)) continue;

            map[nx][ny] = 'x'; // 이미 지나갔기 때문에 + 만약에 실패했다 하더라도 이 길로 가면 어차피 실패하는 거기 때문에 되돌릴 필요 없다!

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
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }
        System.out.println(answer);
    }

    static boolean canGo(int x, int y) {
        return !(x < 0 || y < 0 || x >= R || y >= C || map[x][y] == 'x');
    }
}
