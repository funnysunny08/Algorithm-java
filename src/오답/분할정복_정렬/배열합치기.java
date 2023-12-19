package 오답.분할정복_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11728번: 배열 합치기
public class 배열합치기 {

    static int N, M;
    static int[] arrA;
    static int[] arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arrA = new int[N];
        arrB = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < N && j < M) {
            sb.append(arrA[i] < arrB[j] ? arrA[i++] : arrB[j++]).append(" ");
        }
        while (i < N) sb.append(arrA[i++]).append(" ");
        while (j < M) sb.append(arrB[j++]).append(" ");
        System.out.println(sb);
        br.close();
    }
}
