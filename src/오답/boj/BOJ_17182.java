package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17182번: 우주 탐사선
public class BOJ_17182 {

    static int N, K;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void dfs(int depth, int node, int sum) {
        if (depth == N - 1) {
            answer = Math.min(answer, sum);
            return;
        }
        visited[node] = true;
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(depth + 1, i, sum + map[node][i]);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드워셜로 최단 거리 계산
        for (int k = 0; k < N; k++) { // 경유 노드
            for (int i = 0; i < N; i++) { // 출발 노드
                for (int j = 0; j < N; j++) { // 도착 노드
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        visited = new boolean[N];
        dfs(0, K, 0);
        System.out.println(answer);
    }
}
