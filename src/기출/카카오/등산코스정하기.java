package 기출.카카오;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

// 카카오 - 등산코스 정하기
public class 등산코스정하기 {
    /*
    * 최소 intensity 만들기!
    * */

    static List<List<Node>> graph = new ArrayList<>();
    static Set<Integer> summitSet = new HashSet<>();
    static int[] d;

    static class Node implements Comparable<Node> {
        int end;
        int cost;
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) { // 비용 오름차순
            if (this.cost > o.cost) {
                return 1;
            }
            return 0;
        }
    }

    public static void dijkstra(int[] gates) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int g : gates) { // 출발지 모두 queue 에 넣음!
            pq.offer(new Node(g, 0));
            d[g] = 0; // dp를 0으로 초기화함으로써 재방문 x
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int idx = now.end;
            int cost = now.cost;

            if (d[idx] < cost) continue; // 방문한 적 있음 (= 갱신된 적 있음)

            if (summitSet.contains(idx)) continue; // 봉우리면 더 이상 탐색하지 않음!

            for (int i = 0; i < graph.get(idx).size(); i++) { // 해당 노드와 연결된 모든 노드 순차 탐색
                int value = Math.max(d[idx], graph.get(idx).get(i).cost); // 최대 intensity 만 저장하면 되므로!
                if (d[graph.get(idx).get(i).end] > value) {
                    d[graph.get(idx).get(i).end] = value;
                    pq.offer(new Node(graph.get(idx).get(i).end, value));
                }
            }
        }
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        for (int s : summits) { // 봉우리를 set 에 저장!
            summitSet.add(s);
        }

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) { // 양방향 매핑
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }

        d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        dijkstra(gates);

        int[] answer = {0, Integer.MAX_VALUE}; // 봉우리 번호, intensity

        for (int s : summits) {
            if (d[s] < answer[1]) { // 제일 작은 intensity 를 찾는다!
                answer[1] = d[s]; // intensity 저장
                answer[0] = s; // 해당 봉우리 번호 저장
            } else if (d[s] == answer[1] && s < answer[0]) {
                answer[0] = s;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//        int[] gates = {1, 3};
        int[] gates = {1};
//        int[] summits = {5};
        int[] summits = {2, 3, 4};
        int[] answer = solution(7, paths, gates, summits);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}
