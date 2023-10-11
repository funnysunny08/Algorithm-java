package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2798번: 블랙잭
public class 블랙잭 {

    static int N;
    static int M;
    static int[] arr;
    static int[] combi;
    static int answer = -1;

    static void combination(int L, int s) { // L: 몇번째(현재 자리), s: arr idx 어디부터 돌면 돼?
        if (L == 3) { // 3개 다 선택!
            int sum = Arrays.stream(combi).sum();
            if (sum <= M) // 합이 M 초과면 pass 하겠다!
                answer = Math.max(answer, Arrays.stream(combi).sum());
        } else {
            for (int i = s; i < N; i++) {
                combi[L] = arr[i];
                combination(L + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        combi = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        combination(0, 0);
        System.out.println(answer);
    }
}
