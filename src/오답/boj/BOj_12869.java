package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 12869번: 뮤탈리스트
public class BOj_12869 {

    static int answer = Integer.MAX_VALUE;
    static boolean[][][] visited = new boolean[61][61][61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) scv[i] = Integer.parseInt(st.nextToken());
        for (int i = n; i < 3; i++) scv[i] = 0;

        Arrays.sort(scv);
        dfs(scv[2], scv[1], scv[0], 0);
        System.out.println(answer);
    }

    public static void dfs(int a, int b, int c, int cnt) {
        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);

        if (cnt > answer) return;

        if (a == 0 && b == 0 && c ==0) {
            answer = Math.min(answer, cnt);
            return;
        }

        int[] arr = {a, b, c};
        Arrays.sort(arr);

        if (visited[arr[2]][arr[1]][arr[0]]) return;
        visited[arr[2]][arr[1]][arr[0]] = true;

        dfs(arr[2] - 9, arr[1] - 3, arr[0] - 1, cnt + 1);
        dfs(arr[2] - 9, arr[1] - 1, arr[0] - 3, cnt + 1);
        dfs(arr[2] - 3, arr[1] - 9, arr[0] - 1, cnt + 1);
        dfs(arr[2] - 3, arr[1] - 1, arr[0] - 9, cnt + 1);
        dfs(arr[2] - 1, arr[1] - 9, arr[0] - 3, cnt + 1);
        dfs(arr[2] - 1, arr[1] - 3, arr[0] - 9, cnt + 1);
    }
}
