package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 프로그래머스: 가장 먼 노드
public class 가장먼노드 {

    static int[] dist;
    static List<List<Integer>> info = new ArrayList<>();

    public static int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        for (int i = 0; i <= n; i++) info.add(new ArrayList<>());
        for (int i = 0; i < edge.length; i++) {
            info.get(edge[i][0]).add(edge[i][1]);
            info.get(edge[i][1]).add(edge[i][0]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : info.get(now)) {
                if (dist[next] != -1) continue;
                dist[next] = dist[now] + 1;
                q.offer(next);
            }
        }

        int cnt = 0;
        Arrays.sort(dist);
        int value = dist[n];
        for (int i = n; i >= 0; i--) {
            if (dist[i] < value) break;
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(6, vertex));
    }
}
