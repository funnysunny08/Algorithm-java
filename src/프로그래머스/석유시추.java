package 프로그래머스;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// 프로그래머스: 석유 시추
public class 석유시추 {

    static int N, M; // 세로, 가로
    static int[][] map;
    static int[][] oilMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Integer> sizeList = new ArrayList<>();

    public static void bfs(int x, int y, int oilId) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        oilMap[x][y] = oilId;
        int size = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || oilMap[nx][ny] != 0 || map[nx][ny] == 0) continue;
                oilMap[nx][ny] = oilId;
                size++;
                q.offer(new int[]{nx, ny});
            }
        }
        sizeList.add(oilId, size);
    }

    public static int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        map = land;
        oilMap = new int[N][M];
        sizeList.add(0);

        int oilId = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && oilMap[i][j] == 0) {
                    bfs(i, j, oilId++);
                }
            }
        }

        int answer = -1;
        for (int j = 0; j < M; j++) {
            Set<Integer> oilSpots = new HashSet<>();
            for (int i = 0; i < N; i++) {
                oilSpots.add(oilMap[i][j]);
            }
            int total = 0;
            for (int id : oilSpots) {
                total += sizeList.get(id);
            }
            answer = Math.max(answer, total);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(solution(land));
    }
}
