package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2212_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        List<Integer> diff = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] loc = new int[N];
        for (int i = 0; i < N; i++) loc[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(loc);
        int prev = loc[0];
        for (int i = 1; i < N; i++) {
            diff.add(loc[i] - prev);
            prev = loc[i];
        }

        diff.sort(Collections.reverseOrder());
        long answer = 0;
        for (int i = K - 1; i < diff.size(); i++) {
            answer += diff.get(i);
        }
        System.out.println(answer);
    }
}
