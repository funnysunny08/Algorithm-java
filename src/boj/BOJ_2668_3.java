package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 2668. 숫자고르기
public class BOJ_2668_3 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    private static void chk(int idx, int target) {
        if (arr[idx] == target) {
            answer.add(target);
            return;
        }

        if (!visited[arr[idx]]) {
            visited[arr[idx]] = true;
            chk(arr[idx], target);
            visited[arr[idx]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
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
        for (Integer num : answer) sb.append(num).append("\n");
        System.out.println(sb);
    }
}
