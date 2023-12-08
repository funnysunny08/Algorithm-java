package DisjointSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준 1647번: 도시 분할 계획
public class 도시분할계획 {

    static int N, M;
    static int[] parent;
    static ArrayList<Edge> edges;

    static class Edge {
        int x;
        int y;
        int cost;
        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void kruskal() {
        int totalCost = 0;
        int maxCost = Integer.MIN_VALUE;
        for (int i = 0; i < edges.size(); i++) {
            Edge curEdge = edges.get(i);
            if (findParent(curEdge.x) != findParent(curEdge.y)) {
                union(curEdge.x, curEdge.y);
                totalCost += curEdge.cost;
                maxCost = Math.max(maxCost, curEdge.cost);
            }
        }
        System.out.println(totalCost - maxCost);
    }

    public static int findParent(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = findParent(parent[x]);
            return parent[x];
        }
    }

    public static void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);
        if (px > py) {
            parent[px] = py;
        } else if (px < py) {
            parent[py] = px;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        Collections.sort(edges, ((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
        kruskal();
    }
}
