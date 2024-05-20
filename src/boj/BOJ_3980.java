package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3980번: 선발 명단
public class BOJ_3980 {

    static int[][] infos = new int[11][11];
    static int result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 입력받기
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    infos[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = -1;
            visited = new boolean[11];
            dfs(0, 0);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int member, int score) {
        if (member == 11) {
            result = Math.max(result, score);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (visited[i] || infos[member][i] == 0) continue;
            visited[i] = true;
            dfs(member + 1, score + infos[member][i]);
            visited[i] = false;
        }
    }
}
