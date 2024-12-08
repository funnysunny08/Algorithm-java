package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2668_2 {
    static int[] arr;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();
    private static void chk(int num, int target) {
        if (!visited[arr[num]]) {
            visited[arr[num]] = true;
            chk(arr[num], target);
            visited[arr[num]] = false;
        }
        if (arr[num] == target) answer.add(target);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            chk(i, i);
            visited[i] = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int ans : answer) sb.append(ans).append("\n");
        System.out.println(sb);
    }
}
