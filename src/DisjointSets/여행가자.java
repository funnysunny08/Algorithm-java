package DisjointSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1976번: 여행 가자
public class 여행가자 {

    static int[] parent;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 여행 경로에서 도시의 개수
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1)
                    union(i, j);
            }
        }

        boolean chk = true;
        st = new StringTokenizer(br.readLine());
        int x = parent[Integer.parseInt(st.nextToken())];
        for (int i = 1; i < M; i++) {
            int tmp = parent[Integer.parseInt(st.nextToken())];
            if (x != tmp) {
                chk = false;
                break;
            }
            x = tmp;
        }
        if (chk) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
