package Kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// 2022 KAKAO TECH INTERNSHIP: 등산코스 정하기
public class 등산코스정하기 {

    static List<Node>[] nodes;
    static int[] dist;
    static int resultNode = Integer.MAX_VALUE;
    static int minIntensity = Integer.MAX_VALUE;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();
        nodes = new List[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int value = path[2];
            if (gateSet.contains(from) || summitSet.contains(to)) {
                nodes[from].add(new Node(to, value));
            } else if (gateSet.contains(to) || summitSet.contains(from)) {
                nodes[to].add(new Node(from, value));
            } else {
                nodes[from].add(new Node(to, value));
                nodes[to].add(new Node(from, value));
            }
        }

        dijkstra(n, gates, summits);
        return new int[]{resultNode, minIntensity};
    }

    static void dijkstra(int n, int[] gates, int[] summits) {
        Queue<int[]> q = new LinkedList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int gate : gates) {
            q.add(new int[]{gate, 0}); // 출발지 노드는 intensity 0
        }

        while (!q.isEmpty()) {
            int e = q.peek()[0];
            int w = q.peek()[1];
            q.poll();

            if (dist[e] < w) continue; //

            for (Node next : nodes[e]) {
                int dis = Math.max(dist[e], next.w);
                if (dist[next.e] > dis) {
                    dist[next.e] = dis;
                    q.add(new int[]{next.e, dis});
                }
            }
        }

        Arrays.sort(summits);
        for (int summit : summits) {
            if (minIntensity > dist[summit]) {
                minIntensity = dist[summit];
                resultNode = summit;
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};
        int[] sol = solution(n, paths, gates, summits);
        System.out.println(sol[0]);
        System.out.println(sol[1]);
    }
}

class Node {
    int e;
    int w;
    Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}
