package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2343번: 기타 레슨
public class BOJ_2343 {

    static int N, M; // M개의 묶음
    static int[] records;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        records = new int[N];
        long left = 0, right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            records[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, records[i]);
            right += records[i];
        }

        long answer = Integer.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean check(long size) {
        int cnt = 1;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + records[i] > size) {
                cnt++;
                sum = 0;
            }
            sum += records[i];
        }
        return cnt <= M;
    }
}
