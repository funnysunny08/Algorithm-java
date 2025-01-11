package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16935. 배열 돌리기 3
public class BOJ_16935 {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < R; r++) {
            int play = Integer.parseInt(st.nextToken());
            switch (play) {
                case 1:
                    one();
                    break;
                case 2:
                    two();
                    break;
                case 3:
                    three();
                    break;
                case 4:
                    four();
                    break;
                case 5:
                    five();
                    break;
                case 6:
                    six();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void one() {
        int[][] original = copyMap();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = original[N - i - 1][j];
            }
        }
    }

    private static void two() {
        int[][] original = copyMap();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = original[i][M - j - 1];
            }
        }
    }

    private static void three() {
        int[][] newMap = new int[M][N];
        // (i, j)가 (M - j - 1, N - i - 1) 로 이동한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[j][N - i - 1] = map[i][j];
            }
        }
        map = newMap;
        N = map.length;
        M = map[0].length;
    }

    private static void four() {
        int[][] newMap = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[M - j - 1][i] = map[i][j];
            }
        }
        map = newMap;
        N = map.length;
        M = map[0].length;
    }

    private static void five() {
        int[][] original = copyMap();
        int nn = N / 2;
        int mm = M / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < M; j++) {
                if (i < nn && j < mm) {
                    map[i][mm + j] = original[i][j];
                } else if (i < nn) {
                    map[nn + i][j] = original[i][j];
                } else if (j < mm) {
                    map[i - nn][j] = original[i][j];
                } else {
                    map[i][j - mm] = original[i][j];
                }
            }
        }
    }

    private static void six() {
        int[][] original = copyMap();
        int nn = N / 2;
        int mm = M / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < nn && j < mm) {
                    map[i + nn][j] = original[i][j];
                } else if (i < nn) {
                    map[i][j - mm] = original[i][j];
                } else if (j < mm) {
                    map[i][j + mm] = original[i][j];
                } else {
                    map[i - nn][j] = original[i][j];
                }
            }
        }
    }

    private static int[][] copyMap() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
