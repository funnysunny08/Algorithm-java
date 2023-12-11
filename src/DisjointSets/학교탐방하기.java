package DisjointSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 13418번: 학교 탐방하기
public class 학교탐방하기 {

    static int N;
    static int M;
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

    public static long kruskal() {
        long result = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge now = edges.get(i);
            int x = now.x;
            int y = now.y;
            int cost = now.cost;
            if (findParent(x) != findParent(y)) {
                union(x, y);
                if (cost == 0) {
                    result++;
                }
            }
        }
        return result * result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        edges = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken()); // 0: 오르막길, 1: 내리막길
            edges.add(new Edge(x, y, value));
        }

        edges.sort(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
        long bad = kruskal();

        edges.sort(((o1, o2) -> Integer.compare(o2.cost, o1.cost)));
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        long good = kruskal();
        System.out.println(bad - good);
    }
}
