package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1469. 숌 사이 수열
public class BOJ_1469 {
    static int N;
    static int[] arr;
    static boolean[] visit;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    private static void dfs(int idx) {
        if (idx == N) {
            if (check()) {
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                selected[idx] = arr[i];
                dfs(idx + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean check() {
        int[] temp = new int[N * 2];
        int s = 0;
        Arrays.fill(temp, -1);
        for (int i = 0; i < N * 2; i++) {
            if (temp[i] != -1) continue;
            if (s >= N) return false;
            temp[i] = selected[s];
            int next = i + selected[s] + 1;
            if (next >= N * 2 || temp[next] != -1) return false;
            temp[next] = selected[s];
            s++;
        }
        sb = new StringBuilder();
        for (int i : temp) sb.append(i).append(" ");
        return s == N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        selected = new int[N];
        visit = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0);
        System.out.println(-1);
    }
}
