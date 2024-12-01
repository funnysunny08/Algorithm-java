package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20207 {
    static boolean[][] calendar = new boolean[1000][366];
    static int minStart, maxEnd, maxRow;
    static boolean[][] visited = new boolean[1000][366];
    static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] schedule = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
            maxEnd = Math.max(maxEnd, schedule[i][1]);
        }
        // schedule[i][0] 기준으로 오름차순 정렬하고,
        // schedule[i][0] 같으면 schedule[i][1] - schedule[i][0] 기준으로 내림차순 정렬
        Arrays.sort(schedule, Comparator.comparingInt((int[] a) -> a[0])
            .thenComparing((int[] a, int[] b) -> Integer.compare(b[1] - b[0], a[1] - a[0])));
        minStart = schedule[0][0];

        for (int i = 0; i < n; i++) {
            for (int row = 0; row < 1000; row++) {
                if (canInsert(row, schedule[i][0], schedule[i][1])) {
                    insertSchedule(row, schedule[i][0], schedule[i][1]);
                    break;
                }
            }
        }

        int answer = 0;
        for (int row = 0; row <= maxRow; row++) {
            for (int col = minStart; col <= maxEnd; col++) {
                if (calendar[row][col] && !visited[row][col]) { // 일정이 있고, 방문한 적이 없다면
                    answer += bfs(row, col);
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = -1, maxY = -1;

        while (!q.isEmpty()) { // 현재 스케줄과 인접한 영역에 스케줄이 존재하는지 확인
            int[] now = q.poll();
            minX = Math.min(minX, now[0]);
            minY = Math.min(minY, now[1]);
            maxX = Math.max(maxX, now[0]);
            maxY = Math.max(maxY, now[1]);

            for (int i = 0; i < 8; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= 1000 || ny >= 366) continue;
                if (visited[nx][ny] || !calendar[nx][ny]) continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        for (int i = minX; i <= maxX; i++) { // 위에서 만들어진 영역 모두 visited true
            for (int j = minY; j <= maxY; j++) {
                q.offer(new int[]{i, j});
                visited[i][j] = true;
            }
        }

        while (!q.isEmpty()) { // 위에서 만들어진 영역과 인접한 스케줄도 포함시키기 위해 다시 탐색
            int[] now = q.poll();
            minX = Math.min(minX, now[0]);
            minY = Math.min(minY, now[1]);
            maxX = Math.max(maxX, now[0]);
            maxY = Math.max(maxY, now[1]);

            for (int i = 0; i < 8; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= 1000 || ny >= 366) continue;
                if (visited[nx][ny] || !calendar[nx][ny]) continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        return (maxX - minX + 1) * (maxY - minY + 1);
    }


    private static void insertSchedule(int row, int start, int end) {
        for (int i = start; i <= end; i++) calendar[row][i] = true;
        maxRow = Math.max(maxRow, row);
    }

    private static boolean canInsert(int row, int start, int end) {
        for (int i = start; i <= end; i++) if (calendar[row][i]) return false;
        return true;
    }
}
