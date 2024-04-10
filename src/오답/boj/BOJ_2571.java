package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2571번: 색종이 - 3
public class BOJ_2571 {

    static int[][] arr = new int[101][101];
    static int[][] sum = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                arr[i][j] = -10001;
            }
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int r = x; r < x + 10; r++) {
                for (int c = y; c < y + 10; c++) {
                    arr[r][c] = 1;
                }
            }
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                sum[i][j] = arr[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        int max = 100;
        for (int r1 = 1; r1 <= 100; r1++) {
            for (int c1 = 1; c1 <= 100; c1++) {
                for (int r2 = r1; r2 <= 100; r2++) {
                    for (int c2 = c1; c2 <= 100; c2++) {
                        int value = sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
                        if (value < 0) break;
                        max = Math.max(max, value);
                    }
                }
            }
        }
        System.out.println(max);
    }

}
