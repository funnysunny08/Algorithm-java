package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11403번: 경로 찾기
public class 경로찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 0) map[i][j] = INF;
                else map[i][j] = 1;
            }
        }

        for (int k = 0; k < N; k++) { // 거쳐가는 노드!
            for (int i = 0; i < N; i++) { // 출발 노드!
                for (int j = 0; j < N; j++) { // 도착 노드!
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) sb.append(1).append(" ");
                else sb.append(0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
