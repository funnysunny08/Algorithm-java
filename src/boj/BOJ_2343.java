package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2343번: 기타 레슨
public class BOJ_2343 {

    static int  N, M;
    static int[] lessons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long start = 0, end = 1;
        lessons = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            end += lessons[i];
            start = Math.max(start, lessons[i]);
        }

        long answer = Integer.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (isCheck(mid)) {
                answer = Math.min(answer, mid);
                end  = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }

    static private boolean isCheck(long size) {
        long sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            sum += lessons[i];
            if (sum > size) {
                cnt++;
                sum = lessons[i];
            }
        }
        return M >= cnt + 1;
    }
}
