package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2571번: 색종이 - 3
public class BOJ_2571 {

    static int[][] map;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        map = new int[101][101];

        for (int[] arr : map)
            Arrays.fill(arr, -10001);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            for (int x = sx; x < sx + 10; x++) {
                for (int y = sy; y < sy + 10; y++) {
                    map[x][y] = 1;
                }
            }
        }

        // 2차원 누적합
        sum = new int[101][101];
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                sum[i][j] = map[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        // 최댓값 구하기 -> (x1, y1) (x2, y2) 선택
        int max = 100;
        for (int x1 = 1; x1 <= 100; x1++) {
            for (int x2 = x1 + 1; x2 <= 100; x2++) {
                for (int y1 = 1; y1 <= 100; y1++) {
                    for (int y2 = y1 + 1; y2 <= 100; y2++) {
                        int value = sum[x2][y2] + sum[x1 - 1][y1 - 1] - sum[x1 - 1][y2] - sum[x2][y1 - 1];
                        if (value < 0) break;
                        max = Math.max(max, value);
                    }
                }
            }
        }
        System.out.println(max);

    }
}
