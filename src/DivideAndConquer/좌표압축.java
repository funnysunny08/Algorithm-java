package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 백준 18870번: 좌표 압축
public class 좌표압축 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] origin = new int[N]; // 원본 배열
        int[] sorted = new int[N]; // 정렬할 배열
        HashMap<Integer, Integer> hm = new HashMap<>(); // 순위 매길 hash map

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            origin[i] = sorted[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sorted); // 정렬할 배열만 정렬!!

        int rank = 0;
        for (int v: sorted) {
            if (!hm.containsKey(v)) { // hash map에 담긴 적이 없다면!!
                hm.put(v, rank); // 담고
                rank++; // 순위 올림
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int v: origin) {
            sb.append(hm.get(v)).append(" ");
        }
        System.out.println(sb);
    }

}
