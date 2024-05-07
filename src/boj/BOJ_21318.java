package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 21318번: 피아노 체조
public class BOJ_21318 {

    static int N;
    static int[] musics;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        musics = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) musics[i] = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1];
            if (musics[i] < musics[i - 1]) {
                arr[i]++;
            }
        }

        int  q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(arr[y] - arr[x]).append("\n");
        }
        System.out.println(sb);
    }
}
