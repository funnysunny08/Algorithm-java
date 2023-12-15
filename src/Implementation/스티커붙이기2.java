package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 18808번: 스티커 붙이기
public class 스티커붙이기2 {

    static int N, M, K; // 세로, 가로, 스티커 개수
    static int[][] map;

    public static boolean putSticker(int n, int m, int[][] shape) {
        boolean chk = true; // 끝까지 true 면 스티커 못 붙인 거!
        for (int x = 0; x <= N - n && chk; x++) {
            for (int y = 0; y <= M - m && chk; y++) {
                // 스티커 붙여보기!
                boolean keepGo = true;
                for (int ax = 0; ax < n && keepGo; ax++) {
                    for (int ay = 0; ay < m && keepGo; ay++) {
                        int idxX = x + ax;
                        int idxY = y + ay;
                        if (shape[ax][ay] * map[idxX][idxY] == 1) { // 스티커 못 붙임!
                            keepGo = false;
                        }
                    }
                }
                if (keepGo) { // 여기서 keepGo가 true 면 스티커 붙일 수 있다는 뜻!
                    // 스티커 붙이기!
                    for (int ax = 0; ax < n; ax++) {
                        for (int ay = 0; ay < m; ay++) {
                            int idxX = x + ax;
                            int idxY = y + ay;
                            if (shape[ax][ay] == 1) {
                                map[idxX][idxY] = 1;
                            }
                        }
                    }
                    chk = false; // 해당 위치에서 스티커 붙일 수 있음!! 그러니깐 멈춰..
                }
            }
        }
        return !chk; // 스티커 붙였으면 true 반환
    }

    public static int[][] rotate(int n, int m, int[][] arr) {
        int[][] copy = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[j][n - 1 - i] = arr[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < K; i++) {
            // 입력 받기
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] shape = new int[n][m];
            for (int x = 0; x < n; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < m; y++) {
                    shape[x][y] = Integer.parseInt(st.nextToken());
                }
            }
            // 수행
            for (int k = 0; k < 4; k++) {
                if (k != 0) {
                    int[][] newShape = rotate(n, m, shape);
                    n = newShape.length;
                    m = newShape[0].length;
                    shape = new int[n][m];
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < m; y++) {
                            shape[x][y] = newShape[x][y];
                        }
                    }
                }
                boolean check = putSticker(n, m, shape);
                if (check) break; // 스티커 붙였으니깐 멈춤!
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}