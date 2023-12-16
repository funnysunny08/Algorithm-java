package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

// 백준 3190번: 뱀
public class 뱀 {

    static int N, K, L;
    static int[][] map;
    static HashMap<Integer, Character> turns = new HashMap<>();
    static List<int[]> snake = new ArrayList<>();
    // L (왼쪽) -> + 1, D (오른쪽) -> - 1
    static int[] dx = {-1, 0, 1, 0}; // 상, 좌, 하, 우
    static int[] dy = {0, -1, 0, 1};

    public static int solve() {
        int time = 0;
        int x = 1;
        int y = 1;
        int dir = 3;
        snake.add(new int[]{x, y});

        while (true) {
            // 시간 증가
            time++;

            // 뱀의 다음 좌표 계산
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 종료 조건 체크
            if (isEnd(nx, ny)) break;

            // 사과 있는지 체크하고, 뱀 이동시킴
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0; // 사과 먹어서 없애고,
                snake.add(new int[]{nx, ny}); // 꼬리를 삭제하지 않음으로써 늘려줌
            } else {
                snake.add(new int[]{nx, ny});
                snake.remove(0); // List의 가장 앞에 있는 부분이 꼬리가 위치한 부분!
            }

            // 방향 처리
            if (turns.containsKey(time)) {
                if (turns.get(time) == 'L') {
                    dir = (dir + 1) % 4;
                } else {
                    dir = (dir + 3) % 4;
                }
            }

            //
            x = nx;
            y = ny;
        }

        return time;
    }

    public static boolean isEnd(int nx, int ny) {
        if (nx <= 0 || ny <= 0 || nx > N || ny > N) return true; // 벽과 부딪히는 경우
        for (int[] part : snake) {
            if (nx == part[0] && ny == part[1]) return true; // 자신의 몸과 부딪히는 경우
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1; // 사과가 있는 자리에 1을 배치
        }

        L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            turns.put(time, direction);
        }

        System.out.println(solve());
    }
}
