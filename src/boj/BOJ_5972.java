package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 5972. 택배 배송
public class BOJ_5972 {

    static int N, M;
    static List<List<Road>> map = new ArrayList<>();
    static int[] dist;
    static class Road implements Comparable<Road> {
        int idx, cows;

        public Road(int idx, int cows) {
            this.idx = idx;
            this.cows = cows;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.cows, o.cows);
        }
    }

    private static int dijkstra() {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (now.idx == N) return dist[N];
            if (dist[now.idx] < now.cows) continue; // 이미 dist[now.idx]는 갱신 됐어! 필요 없어.
            for (int i = 0; i < map.get(now.idx).size(); i++) {
                Road next = map.get(now.idx).get(i);
                if (dist[next.idx] > now.cows + next.cows) { // now.idx 통해서 가는 게 더 낫네
                    dist[next.idx] = now.cows + next.cows;
                    pq.offer(new Road(next.idx, dist[next.idx]));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) map.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cows = Integer.parseInt(st.nextToken());
            map.get(s).add(new Road(e, cows));
            map.get(e).add(new Road(s, cows));
        }
        System.out.println(dijkstra());
    }
}
