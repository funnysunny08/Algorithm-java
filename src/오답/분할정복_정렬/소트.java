package 오답.분할정복_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 1083번: 소트
public class 소트 {

    static int N;
    static List<Integer> list = new ArrayList<>();
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        S = Integer.parseInt(br.readLine());

        int startIdx = 0;

        while (S > 0 && startIdx < N) { // 교환이 가능한 상태라면,
            // start ~ start + S 범위 내에서 가장 큰 값 찾는다!
            int max = Integer.MIN_VALUE;
            int maxIdx = -1;
            for (int i = startIdx; i <= startIdx + S && i < N; i++) {
                if (list.get(i) > max) {
                    max = list.get(i);
                    maxIdx = i;
                }
            }

            if (maxIdx == startIdx) { // start 값이 제일 커서 바꿔줄 필요가 없는 상황
                startIdx++;
                continue;
            }

            list.add(startIdx, max);
            list.remove(maxIdx + 1);

            S -= (maxIdx - startIdx);
            startIdx++;
        }

        StringBuilder sb = new StringBuilder();
        for (int val : list) {
            sb.append(val).append(" ");
        }
        System.out.println(sb);
    }
}
