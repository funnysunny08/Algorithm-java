package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2618. 경찰차
public class BOJ_2618 {
    static int N, W;
    static Node[] arr;
    static int[][] dp; // dp[one][two] : 경찰차1이 one 번쨰 사건 위치에 있고, 경찰차2가 two 번째 사건 위치에 있을 때, 나머지 사건들을 모두 해결했을 때의

    private static int dfs(int e, int one, int two) {
        if (e > W) return 0;
        if (dp[one][two] != 0) return dp[one][two];

        // 경찰차1이 출동
        int first = dfs(e + 1, e, two) + getDistance(1, one, e);
        // 경찰차2가 출동
        int second = dfs(e + 1, one, e) + getDistance(2, two, e);
        return dp[one][two] = Math.min(first, second);
    }

    private static int getDistance(int num, int start, int end) {
        Node st = arr[start];
        Node ed = arr[end];
        if (start == 0) {
            if (num == 1) st = new Node(1, 1);
            else st = new Node(N, N);
        }
        return Math.abs(st.x - ed.x) + Math.abs(st.y - ed.y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        arr = new Node[W + 1];
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[W + 1][W + 1];
        StringBuilder sb = new StringBuilder();
        sb.append(dfs(1, 0, 0)).append("\n");

        int one = 0;
        int two = 0;
        for (int i = 1; i <= W; i++) {
            int dist = getDistance(1, one, i);
            if (dp[one][two] - dist == dp[i][two]) { // 각 단계에서 1이 선택되었는지, 2가 선택되었는지 확인해본다!
                one = i;
                sb.append(1).append("\n");
            } else {
                two = i;
                sb.append(2).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
