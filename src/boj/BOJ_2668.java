package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 2668. 숫자 고르기
public class BOJ_2668 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    private static void dfs(int start, int target) {
        if (!visited[arr[start]]) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }
        if (arr[start] == target) answer.add(target);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int i : answer) sb.append(i).append("\n");
        System.out.println(sb);
    }
}
