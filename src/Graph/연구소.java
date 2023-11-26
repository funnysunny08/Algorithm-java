package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14502번: 연구소
public class 연구소 {

    static int N;
    static int M;
    static int[][] map;
    static List<int[]> virus;
    static int answer = Integer.MIN_VALUE;

    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우

    public static void dfs(int L) {
        if (L == 3) { // 벽 다 세움
            int count = checkSafeArea();
            answer = Math.max(answer, count);
            return;
        }
        for (int i = 0; i < N; i++) { // 0인 곳에 벽 세우기
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽 세우기
                    dfs(L + 1);
                    map[i][j] = 0; // 벽 다시 없애기
                }
            }
        }
    }

    public static int checkSafeArea() {
        Queue<int[]> q = new LinkedList<>();
        for (int[] v : virus) {
            q.add(new int[]{v[0], v[1]});
        }

        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }
        dfs(0);
        System.out.println(answer);
    }
}
