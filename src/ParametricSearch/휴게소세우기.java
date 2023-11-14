package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1477번: 휴게소 세우기
public class 휴게소세우기 {
    static int N;
    static int M;
    static int L;
    static int[] store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력 받기
        N = Integer.parseInt(st.nextToken()); // 현재 휴게소의 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려고 하는 휴게소의 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로의 ㅁ길이
        st = new StringTokenizer(br.readLine());
        store = new int[N + 2];
        store[0] = 0;
        store[N + 1] = L;
        for (int i = 1; i < N + 1; i++) {
            store[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(store);

        int left = 1;
        int right = L - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (makeStore(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }

    public static boolean makeStore(int distance) {
        int cnt = 0;
        for (int i = 1; i < N + 2; i++) {
            cnt += (store[i] - store[i - 1] - 1) / distance;
        }
        return cnt > M;
    }
}
