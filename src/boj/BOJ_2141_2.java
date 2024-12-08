package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2141_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        long total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            total += arr[i][1];
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // 전체 인원의 절반을 넘게 되는 첫번째 위치를 찾는다.
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i][1];
            if (sum >= (total + 1) / 2) { // 여기서 1을 더하는 이유는 홀수를 위해서다. 그렇게 되면 짝수와 홀수 모두 같은 값을 가질 수 있음. ex) 4, 3
                System.out.println(arr[i][0]);
                return;
            }
        }
    }
}
