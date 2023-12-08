package DisjointSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 백준 16398번: 행성 연결
public class 행성연결 {

    static int N;
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

    public static int findParent(int x) {
        if (x == parent[x]) {
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

    public static void kruskal() {
        long cost = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge now = edges.get(i);
            int x = now.x;
            int y = now.y;
            if (findParent(x) != findParent(y)) { // 합칠 수 있다면
                cost += now.cost;
                union(x, y);
            }
        }
        System.out.println(cost);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (i != j) {
                    edges.add(new Edge(i, j, x));
                }
            }
        }
        Collections.sort(edges, ((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
        kruskal();
    }
}
