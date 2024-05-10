package 기출.카카오;

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
            boolean chk = true;
            L:
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (place[x].charAt(y) == 'P') {
                        if (!bfs(x, y)) {
                            chk = false;
                            break L;
                        }
                    }
                }
            }
            answer[i] = chk ? 1 : 0;
        }

        return answer;
    }

    public static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || (x == nx && y == ny)) continue;
                int dist = Math.abs(x - nx) + Math.abs(y - ny);

                if (place[nx].charAt(ny) == 'P' && dist <= 2) return false;
                // 거리가 1이면서 P인 애들 false, 거리가 2이면서 P인 애들은 칸막이가 없어서 넘어와진 애들이기 때문에 false
                else if (place[nx].charAt(ny) == 'O' && dist < 2) q.offer(new int[]{nx, ny});
                // 큐에 다시 담아주는 건, 거리 1인 애들만 함! && 해당 자리가 O인 애들 -> P 인 애들은 위에서 걸러졌고, X인 애들은 가봤자, P 있어도 칸막이 때문에 무효!
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
