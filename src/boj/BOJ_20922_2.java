package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20922_2 {
    static int N, K;
    static int[] count = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int max = -1;
        int left = 0;
        for (int right = 0; right < N; right++) {
            count[arr[right]]++; // 오른쪽으로 이동한 포인터가 가리키는 수의 count 증가
            if (count[arr[right]] <= K) { // 만약 새롭게 변경된 수의 count가 기준을 초과하지 않는다면
                max = Math.max(max, right - left + 1); // 최댓값 갱신
            } else { // 기준을 초과한다면
                for (int i = left; i < right; i++) { // 기준을 충족시킬 때까지 left 포인터를 앞으로 땡겨옴
                    count[arr[i]]--;
                    if (count[arr[right]] <= K) { // 기준을 충족시킬 때까지
                        left = i + 1;
                        break;
                    }
                }
            }
        }
        System.out.println(max);
    }
}
