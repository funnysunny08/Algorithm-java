package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1389번: 케빈 베이컨의 6단계 법칙
public class 케빈_베이컨의_6단계_법칙 {

    static int N; // 유저 수
    static int M; // 친구 관계 수
    static int[][] graph;
    static int INF = 987654321;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }

        for (int k = 1; k <= N; k++) { // 거쳐가는 노드!
            for (int i = 1; i <= N; i++) { // 출발 노드!
                for (int j = 1; j <= N; j++) { // 도착 노드!
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int res = INF;
        int idx = -1;

        // 케빈 베이컨의 수가 가장 작은 인덱스를 탐색
        for (int i = 1; i <= N; i++) {
            int total = 0;
            for (int j = 1; j <= N; j++) {
                total += graph[i][j];
            }

            if (res > total) {
                res = total;
                idx = i;
            }
        }
        System.out.println(idx);
    }
}
