package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1911번 - 흙길 보수하기
public class 흙길보수하기 {

    static class Puddle implements Comparable<Puddle> {
        int start;
        int end;

        Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Puddle o) {
            if(this.start == o.start)
                return o.end - this.end;
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        PriorityQueue<Puddle> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Puddle(start, end));
        }

        int lastIdx = -1;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Puddle puddle = pq.poll();
            if (puddle.end <= lastIdx)
                continue;
            if (puddle.start >= lastIdx) {
                int howMany = (puddle.end - puddle.start) / L;
                if ((puddle.end - puddle.start) % L != 0) {
                    howMany ++;
                }
                lastIdx = puddle.start + howMany * L;
                cnt += howMany;
                continue;
            }

            int howMany = (puddle.end - lastIdx) / L;
            if ((puddle.end - lastIdx) % L != 0) {
                howMany++;
            }
            lastIdx += howMany * L;
            cnt += howMany;
        }

        System.out.println(cnt);

    }
}
