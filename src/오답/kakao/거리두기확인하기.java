package 오답.kakao;

import java.util.LinkedList;
import java.util.Queue;

// 카카오: 거리두기 확인하기
public class 거리두기확인하기 {

    static String[] place;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            place = places[i];
            answer[i] = check() ? 1 : 0;
        }
        return answer;
    }

    public static boolean check() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(i, j)) return false;
                }
            }
        }
        return true;
    }

    public static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int dist = Math.abs(x - nx) + Math.abs(y - ny);
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || dist > 2 || visited[nx][ny]) continue;

                if (place[nx].charAt(ny) == 'P') return false;
                if (dist <= 1 && place[nx].charAt(ny) == 'O') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] answer = solution(places);
        for (int a : answer) {
            System.out.println(a);
        }
    }
}
