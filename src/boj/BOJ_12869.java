package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 12869번: 뮤탈리스크
public class BOJ_12869 {

    static int N;
    static int[] scv;
    static int answer = Integer.MAX_VALUE;
    static boolean[][][] visited = new boolean[61][61][61];

    public static void dfs(int a, int b, int c, int cnt) {
        // 음수인 애들 0으로 만들어주기
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        // 현재 계산중인 cnt 가 이미 나온 최솟값보다 크다면, 탐색 종료! => 탐색해도 최솟값 갱신을 안 될 테니깐
        if (cnt > answer) return;

        // 모든 scv 가 죽었을 경우
        if (a == 0 && b == 0 && c == 0) {
            answer = Math.min(answer, cnt);
            return;
        }

        // a,b,c 순서로 큰 수대로 입력
        int[] sorting = {a, b, c};
        Arrays.sort(sorting);
        c = sorting[0];
        b = sorting[1];
        a = sorting[2];

        if (visited[a][b][c]) return;
        else visited[a][b][c] = true;

        dfs(a - 9, b - 3, c - 1, cnt + 1);
        dfs(a - 9, b - 1, c - 3, cnt + 1);
        dfs(a - 3, b - 9, c - 1, cnt + 1);
        dfs(a - 3, b - 1, c - 9, cnt + 1);
        dfs(a - 1, b - 3, c - 9, cnt + 1);
        dfs(a - 1, b - 9, c - 3, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) scv[i] = Integer.parseInt(st.nextToken());

        for (int i = N; i < 3; i++) scv[i] = 0;

        int s1 = scv[0], s2 = scv[1], s3 = scv[2];
        dfs(s1, s2, s3, 0);
        System.out.println(answer);
    }
}
