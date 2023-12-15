package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 15683번: 감시
public class 감시 {

    static int N;
    static int M;
    static int[][] map;
    static ArrayList<Camera> cameras;
    static int answer = Integer.MAX_VALUE;

    static class Camera {
        int x;
        int y;
        int value; // 카메라 종류 1 ~ 5번
        int direction; // 카메라 방향 -> 0, 1, 2, 3 (상, 하, 좌, 우)
        Camera(int x, int y, int value, int direction) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.direction = direction;
        }
    }

    static void dfs(int L) { // 모든 조합의 카메라 방향을 만든다.
        if (L == cameras.size()) {
            getBlindSpot();
        } else {
            Camera now = cameras.get(L);
            for (int i = 0; i < 4; i++) {
                now.direction = i;
                dfs(L + 1);
            }
        }
    }

    // 1번 카메라 : direction
    // 2번 카메라 : direction, (direction + 2) / 4
    // 3번 카메라 : direction, (direction + 1) / 4
    // 4번 카메라 : direction 빼고
    // 5번 카메라 : 모든 direction
    static void getBlindSpot() { // 현재의 카메라 방향으로 사각지대를 구한다.
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        for (int i = 0; i < cameras.size(); i++) { // 카메라가 비추는 공간 -1로!
            Camera camera = cameras.get(i);
            if (camera.value == 1) {
                if (camera.direction == 0) {
                    tempMap = up(tempMap, camera.x, camera.y);
                } else if (camera.direction == 1) {
                    tempMap = down(tempMap, camera.x, camera.y);
                } else if (camera.direction == 2) {
                    tempMap = left(tempMap, camera.x, camera.y);
                } else {
                    tempMap = right(tempMap, camera.x, camera.y);
                }
            } else if (camera.value == 2) {
                if (camera.direction == 0 || camera.direction == 1) { // 상하
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = down(tempMap, camera.x, camera.y);
                } else {
                    tempMap = left(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                }
            } else if (camera.value == 3) {
                if (camera.direction == 0) {
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = left(tempMap, camera.x, camera.y);
                } else if (camera.direction == 1) {
                    tempMap = down(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                } else if (camera.direction == 2) {
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                } else {
                    tempMap = down(tempMap, camera.x, camera.y);
                    tempMap = left(tempMap, camera.x, camera.y);
                }
            } else if (camera.value == 4) {
                if (camera.direction == 0) {
                    tempMap = down(tempMap, camera.x, camera.y);
                    tempMap = left(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                } else if (camera.direction == 1) {
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = left(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                } else if (camera.direction == 2) {
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = down(tempMap, camera.x, camera.y);
                    tempMap = right(tempMap, camera.x, camera.y);
                } else {
                    tempMap = up(tempMap, camera.x, camera.y);
                    tempMap = down(tempMap, camera.x, camera.y);
                    tempMap = left(tempMap, camera.x, camera.y);
                }
            } else {
                tempMap = up(tempMap, camera.x, camera.y);
                tempMap = down(tempMap, camera.x, camera.y);
                tempMap = left(tempMap, camera.x, camera.y);
                tempMap = right(tempMap, camera.x, camera.y);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) cnt++;
            }
        }
        answer = Math.min(answer, cnt);
    }

    static int[][] up(int[][] temp, int x, int y) { // 해당 좌표로부터 위를 찍는다.
        int nx = x - 1;
        while (nx >= 0 && temp[nx][y] != 6) {
            temp[nx][y] = -1;
            nx--;
        }
        return temp;
    }

    static int[][] down(int[][] temp, int x, int y) {
        int nx = x + 1;
        while (nx < N && temp[nx][y] != 6) {
            temp[nx][y] = -1;
            nx++;
        }
        return temp;
    }

    static int[][] left(int[][] temp, int x, int y) {
        int ny = y - 1;
        while (ny >= 0 && temp[x][ny] != 6) {
            temp[x][ny] = -1;
            ny--;
        }
        return temp;
    }

    static int[][] right(int[][] temp, int x, int y) {
        int ny = y + 1;
        while (ny < M && temp[x][ny] != 6) {
            temp[x][ny] = -1;
            ny++;
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cameras = new ArrayList<>();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cameras.add(new Camera(i, j, map[i][j], 0));
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }
}
