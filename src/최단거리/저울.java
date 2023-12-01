package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 10159번: 저울
public class 저울 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int INF = 90000000;
        int[][] map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x > y
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 0;
        }

        for (int k = 1; k <= N; k++) { // 경유 노드
            for (int i = 1; i <= N; i++) { // 시작 노드
                for (int j = 1; j <= N; j++) { // 도착 노드
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0) {
                    result[i]++;
                    result[j]++;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.println(N - 1 - result[i]);
        }
    }
}
