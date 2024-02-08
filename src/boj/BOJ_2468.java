package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2468번: 안전 영역
public class BOJ_2468 {

    static int N;
    static int[][] map;
    static boolean[][] isFlooded;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int getSafeArea() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isFlooded[i][j]) { // 안 넘친 부분 발견!
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    isFlooded[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = curr[0] + dx[k];
                            int ny = curr[1] + dy[k];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N || isFlooded[nx][ny]) continue;
                            q.add(new int[]{nx, ny});
                            isFlooded[nx][ny] = true;
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    public static void afterRain(int height) {
        isFlooded = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= height) isFlooded[i][j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int height = 0; height < max; height++) {
            afterRain(height);
            answer = Math.max(answer, getSafeArea());
        }
        System.out.println(answer);
    }
}
