package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 22945번: 팀 빌딩
public class BOJ_22945 {

    static int N;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = N - 1;
        while (right > left + 1) {
            answer = Math.max(answer, (right - left - 1) * Math.min(arr[right], arr[left]));

            // left, right 둘 중 무엇을 옮겨야 할까?
            // 큰 값을 옮기는 것은 의미가 없음 => 큰 값을 옮기게 되면 최솟값이 그대로거나 더 작아지기만 할 뿐!
            // 작은 값을 옮기게 되면, 최솟값이 더 커져 answer를 업데이트 할 수도 혹은 최솟값이 더 작아져 answer를 업데이트 안 하고 넘어갈 수도 있음!
            // 하지만 (right - left- 1)이 작아진 상황에서 큰 값을 옮기게 되면 무조건 작아지기만 할 뿐!!!
            if (arr[right] < arr[left]) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        System.out.println(answer);
    }
}
