package 오답.최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 16398번: 행성 연결 (크루스칼)
public class 행성연결_크루스칼 {

    static int N;
    static int[] parent;
    static List<Edge> list = new ArrayList<>();

    static class Edge {
        int start, end, weight;
        Edge (int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
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
        for (Edge edge : list) {
            int x = edge.start;
            int y = edge.end;
            if (findParent(x) != findParent(y)) {
                cost += edge.weight;
                union(x, y);
            }
        }
        System.out.println(cost);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (i != j) {
                     list.add(new Edge(i, j, val));
                }
            }
        }
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.weight, o2.weight));
        kruskal();
    }
}
