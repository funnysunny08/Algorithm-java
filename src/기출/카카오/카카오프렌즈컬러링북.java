package 기출.카카오;

import java.util.LinkedList;
import java.util.Queue;

// 카카오: 카카오프렌즈 컬러링북
public class 카카오프렌즈컬러링북 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int size = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != map[x][y] || visited[nx][ny]) continue;
                size++;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return size;
    }

    public static int[] solution(int m, int n, int[][] picture) {
        map = picture;
        visited = new boolean[m][n];
        N = m;
        M = n;

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                }
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] answer = solution(6, 4, picture);

        System.out.println(answer[0] + " " + answer[1]);
    }
}
