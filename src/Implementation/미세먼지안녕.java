package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17144번: 미세먼지 안녕!
public class 미세먼지안녕 {

    static int R, C, T;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int x1, y1, x2, y2;

    public static void runAirPurifier() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] = map[i][j];
            }
        }
        //
        map[x1][y1] = map[x2][y2] = 0;
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                // 우로 이동
                if (x == x1 || x == x2) {
                    if (y + 1 < C) {
                        temp[x][y + 1] = map[x][y];
                    }
                }

                // 좌로 이동
                if (x == 0 || x == R - 1) {
                    if (y - 1 >= 0) {
                        temp[x][y - 1] = map[x][y];
                    }
                }

                // 위로 이동
                if ((x <= x1 && y == C - 1) || (x >= x2 && y == 0)) {
                    if (x - 1 >= 0) {
                        temp[x - 1][y] = map[x][y];
                    }
                }

                // 아래로 이동
                if ((x <= x1 && y == 0) || (x >= x2 && y == C - 1)) {
                    if (x + 1 < R) {
                        temp[x + 1][y] = map[x][y];
                    }
                }
            }
        }
        temp[x1][y1] = temp[x2][y2] = -1;
        map = temp.clone();
    }

    public static void spreadDust() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] <= 0) continue; // 해당 칸에 미세먼지 없거나, 공기청정기 있음.
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1) continue;
                    temp[nx][ny] += map[i][j] / 5;
                    temp[i][j] -= map[i][j] / 5;
                }
                if (temp[i][j] < 0) temp[i][j] = 0;
            }
        }
        map = temp.clone();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (cnt == 0) {
                        x1 = i;
                        y1 = j;
                        cnt++;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }

        //
        while (--T >= 0) { // T번 수행
            spreadDust();
            runAirPurifier();
        }
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) sum += map[i][j];
            }
        }
        System.out.println(sum);
    }
}
