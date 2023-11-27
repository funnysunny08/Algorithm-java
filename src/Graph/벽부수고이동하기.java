package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2206번: 벽 부수고 이동하기
public class 벽부수고이동하기 {

    static int N;
    static int M;
    static int[][] map;
    static int[][] dist;
    static boolean[][][] visit;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        visit = new boolean[2][N][M];  // 벽을 부순 여부에 따라 방문 여부 기록

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0}); // 시작점 세팅 (x좌표, y좌표, crash 여부)
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (map[nx][ny] == 1) { // 벽이 있는 경우
                    if (cur[2] == 0 && !visit[1][nx][ny]) { // 벽을 부순 적 없음 && 벽을 부셨을 때 방문한 적 없음
                        visit[cur[2]][nx][ny] = true; // 벽을 부섰을 경우의 방문 처리!
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                        q.add(new int[]{nx, ny, 1});
                    }
                } else {
                    if (!visit[cur[2]][nx][ny]) {
                        visit[cur[2]][nx][ny] = true;
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                        q.add(new int[]{nx, ny, cur[2]});
                    }
                }
                if(nx == N - 1 && ny == M - 1){
                    System.out.print(dist[nx][ny] + 1);
                    System.exit(0);
                }
            }
        }
        System.out.println(-1);
    }
}
