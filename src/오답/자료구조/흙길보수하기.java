package 오답.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 1911번: 흙길 보수하기
public class 흙길보수하기 {

    static int N, L; // 물 웅덩이 개수, 널빤지 길이
    static List<Puddle> puddles = new ArrayList<>();

    static class Puddle {
        int start;
        int end;
        Puddle (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            puddles.add(new Puddle(start, end));
        }

        Collections.sort(puddles, ((o1, o2) -> Integer.compare(o1.start, o2.start)));

        int cnt = 0;
        int lastIdx = 0;
        for (Puddle puddle : puddles) {
            if (lastIdx >= puddle.end) continue;
            if (lastIdx < puddle.start) {
                lastIdx = puddle.start;
            }
            int need = (puddle.end - lastIdx) / L;
            if ((puddle.end - lastIdx) % L != 0) need++;

            lastIdx = lastIdx + need * L;
            cnt += need;
        }
        System.out.println(cnt);
    }
}
