package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 1774. 우주신과의 교감
public class BOJ_1774 {
    static int N, M;
    static Loc[] planet;
    static int[] parent;

    private static double solve() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (getParent(i) == getParent(j)) continue;
                double dist = Math.sqrt(Math.pow(planet[i].x - planet[j].x, 2) + Math.pow(planet[i].y - planet[j].y, 2));
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges, (o1, o2) -> Double.compare(o1.dist, o2.dist));

        double sum = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (getParent(e.a) != getParent(e.b)) {
                union(e.a, e.b);
                sum += e.dist;
            }
        }
        return sum;
    }

    private static int getParent(int a) {
        if (parent[a] == a) return a;
        return parent[a] = getParent(parent[a]);
    }

    private static void union(int a, int b) {
        int pA = getParent(a);
        int pB = getParent(b);
        if (pA < pB) parent[pB] = pA;
        else parent[pA] = pB;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        planet = new Loc[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            planet[i] = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        System.out.println(String.format("%.2f", solve()));
    }

    static class Edge {
        int a, b;
        double dist;

        public Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
