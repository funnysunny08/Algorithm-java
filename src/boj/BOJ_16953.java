package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16953. A → B
public class BOJ_16953 {
    static long A, B;

    private static void dfs(long number, int cnt) {
        if (number == B) {
            System.out.println(cnt);
            System.exit(0);
        } else if (number > B || number < 0) return;

        dfs(number * 2, cnt + 1);
        dfs(number * 10 + 1, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        dfs(A, 1);
        System.out.println(-1);
    }
}
