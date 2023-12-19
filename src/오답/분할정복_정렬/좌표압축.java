package 오답.분할정복_정렬;

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
        int n = Integer.parseInt(br.readLine());

        int[] origin = new int[n]; // 원본 배열
        int[] sorted = new int[n]; // 정렬할 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            origin[i] = sorted[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sorted); // 정렬
        HashMap<Integer, Integer> hm = new HashMap<>(); // 순위 매기기 용도
        int rank = 0;
        for (int s : sorted) {
            if (!hm.containsKey(s)) {
                hm.put(s, rank);
                rank++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int o : origin) {
            sb.append(hm.get(o)).append(' ');
        }
        System.out.println(sb);
    }
}
