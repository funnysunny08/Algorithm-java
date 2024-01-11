package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1300번: K번째 수
public class K번째수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = (long) n * n;

        // lower-bound
        while (start <= end) {
            long mid = (start + end) / 2;

            long cnt = 0;
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(n, mid / i);
            }
            // mid 값보다 작거나 같은 값이 cnt개 있다.

            if (cnt >= k) { // => cnt == k를 맞춰야 하기 때문에 cnt 를 줄인다. => mid 를 줄인다!!!
                end = mid -1;
            } else { // cnt < k
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}
