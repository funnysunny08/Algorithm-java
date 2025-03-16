package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2026. 소풍
public class BOJ_2026 {
    static int K, N, F;
    static int[][] arr;
    static int[] friends;
    static boolean done;
    static boolean[] visit;

    private static void dfs(int now, int depth) {
        if (depth == K) { // K 명 선택 완료
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) if (visit[i]) sb.append(i).append("\n");
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = now + 1; i <= N; i++) {
            if (arr[now][i] == 1 && isFriend(i)) {
                visit[i] = true;
                dfs(i, depth + 1);
                visit[i] = false;
            }
        }
    }

    static boolean isFriend(int target) { // 지금까지 포함함 친구들과 모두 친구인지 확인
        for (int i = 1; i <= N; i++) {
            if (visit[i] && arr[i][target] != 1) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];
        friends = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 0 ; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1;
            friends[a]++;
            friends[b]++;
        }

        for (int i = 1; i <= N; i++) {
            if (friends[i] < K - 1) continue; // 본인 포함해도 k 명의 친구 그룹 못 만듦
            visit[i] = true;
            dfs(i, 1);
            visit[i] = false;
        }
        System.out.println(-1);
    }
}
