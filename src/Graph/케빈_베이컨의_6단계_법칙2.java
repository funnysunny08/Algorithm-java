package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1389번: 케빈 베이컨의 6단계 법칙
public class 케빈_베이컨의_6단계_법칙2 {

    static int N; // 유저 수
    static int M; // 친구 관계 수
    static ArrayList<Integer>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y); // x는 y와 친구다!
            graph[y].add(x); // y는 x와 친구다!
        }
        solution();
    }

    static void solution() {
        int minCnt = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = BFS(i);
            if (minCnt > cnt) {
                minCnt = cnt;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
    }

    static int BFS(int start) { // 다익스트라 알고리즘
        Arrays.fill(dist, -1); // 방문 여부 초기화
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y: graph[x]) { // x의 친구들 순회
                if (dist[y] != -1) continue; // 방문한 적이 있다면 pass
                dist[y] = dist[x] + 1;
                cnt += dist[y]; // 이동횟수 누적
                q.add(y);
            }
        }
        return cnt;
    }
}
