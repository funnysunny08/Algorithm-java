package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 9081. 단어 맞추기
public class BOJ_9081 {

    private static String getAnswer(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;

        // Step 1: 오른쪽에서부터 감소하지 않는 첫 위치를 찾는다.
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        // 이미 마지막 순서라면 입력 그대로 반환
        if (i == -1) {
            return str;
        }

        // Step 2: i 이후에 있는 문자 중 i번째 문자보다 큰 문자 중 가장 작은 문자 찾기
        int j = n - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }

        // Step 3: i번째 문자와 j번째 문자 교환
        swap(arr, i, j);

        // Step 4: i 이후의 문자를 오름차순 정렬
        Arrays.sort(arr, i + 1, n);

        return new String(arr);
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(getAnswer(br.readLine())).append("\n");
        }
        System.out.println(sb);
    }
}
