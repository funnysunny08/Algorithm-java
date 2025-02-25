package boj;

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
    static Info[] gods;
    static int[] parent;

    private static double distance(Info p1, Info p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static double solve() {
        List<Info> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (parent[i] == parent[j]) continue;
                list.add(new Info(i, j, distance(gods[i], gods[j])));
            }
        }
        double sum = 0;
        Collections.sort(list);
        for (Info e : list) {
            int a = e.x;
            int b = e.y;
            if (getParent(a) != getParent(b)) {
                union(a, b);
                sum += e.dist;
            }
        }
        return sum;
    }

    private static int getParent(int n) {
        if (parent[n] == n) return n;
        return parent[n] = getParent(parent[n]);
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
        gods = new Info[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            gods[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
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

    static class Info implements Comparable<Info> {
        int x, y;
        double dist;

        public Info(int x, int y, double dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            if (dist < o.dist) return -1;
            return 1;
        }
    }
}
