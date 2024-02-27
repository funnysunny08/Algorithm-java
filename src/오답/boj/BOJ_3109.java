package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3109번: 빵집
public class BOJ_3109 {

    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1}; // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int answer = 0;

    public static boolean dfs(int x, int y) {
        if (y == C - 1) {
            answer++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
             int nx = x + dx[i];
             int ny = y + 1;
             if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == 'x') continue;
             map[nx][ny] = 'x';
             if (dfs(nx, ny)) return true; // 만약 해당 루트가 성공했으면, 모든 재귀 종료!
             // 만약에 실패했다 하더라도 이 길로 가면 어차피 실패하는 거기 때문에 되돌릴 필요 없다!
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
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }

        System.out.println(answer);
    }
}
