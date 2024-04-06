package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 15961번: 회전 초밥
public class BOJ_15961 {

    static int N, d, k, c;
    static int[] arr;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        visited = new int[d + 1];

        System.out.println(slidingWindow());
    }

    public static int slidingWindow() {
        int result = 1;
        visited[c]++;
        for (int i = 0; i < k; i++) {
            if (visited[arr[i]] == 0) result++;
            visited[arr[i]]++;
        }

        int cnt = result;
        // 슬라이드 이동시키면서 체크!
        for (int i = 1; i < N; i++) {
            visited[arr[i - 1]]--; // 삭제되는 원소!
            if (visited[arr[i - 1]] == 0) cnt--;

            if (visited[arr[(i + k - 1) % N]] == 0) cnt++; // 추가되는 원소!
            visited[arr[(i + k - 1) % N]]++;

            result = Math.max(result, cnt);
        }

        return result;
    }
}
