package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1916번: 최소비용 구하기
public class 최소비용구하기 {

    static int N; // 도시의 개수
    static int M; // 버스의 개수
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static int start, end;

    static class Node {
        int idx; // 도착 노드 인덱스
        int cost; // 비용
        Node (int x, int cost) {
            this.idx = x;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(x).add(new Node(y, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 비용순으로 오름차순!
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        pq.offer(new Node(start, 0));
        dist[start] = 0; // 해당 노드를 선택한 거나 마찬가지므로 0으로 초기화!
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (curNode.idx == end) break; // 타겟 노드 차례 시 그냥 종료!!
            // 타겟 노드가 꺼내졌다는 것은 타겟 노드까지 최솟값 확정이 완료되었다는 것! => 종료해도 됨

            // 꺼낸 노드 = 현재 최소 비용을 갖는 노드
            // 즉, 해당 노드의 비용이 현재 dist 배열에 기록된 내용보다 크다면 고려할 필요가 없으므로 스킵한다.
            // 중복 노드도 방지함
            if (dist[curNode.idx] < curNode.cost) continue;

            for (Node next : graph.get(curNode.idx)) { // 현재 노드와 이어진 모든 노드에 대해
                if (dist[next.idx] > curNode.cost + next.cost) {
                    dist[next.idx] = curNode.cost + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        System.out.println(dist[end]);
    }
}
