package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11728: 배열 합치기
public class 배열합치기2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int[] arrA = new int[N];
        int[] arrB = new int[M];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < N && j < M) {
            sb.append(arrA[i] <= arrB[j] ? arrA[i++] : arrB[j++]).append(" ");
        }

        while (i < N) sb.append(arrA[i++]).append(" ");
        while (j < M) sb.append(arrB[j++]).append(" ");
        System.out.println(sb);
        br.close();
    }

}
