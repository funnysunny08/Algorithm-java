package 오답.최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1976번: 여행 가자
public class 여행가자 {

    static int N, M;
    static int[] parent;

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

        if (px < py) {
            parent[py] = px;
        } else if (px > py) {
            parent[px] = py;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    union(i, j);
                }
            }
        }

        boolean chk = true;
        st = new StringTokenizer(br.readLine());
        int p = parent[Integer.parseInt(st.nextToken())];
        for (int i = 1; i < M; i++) {
            int np = parent[Integer.parseInt(st.nextToken())];
            if (p != np) {
                chk = false;
                break;
            }
            p = np;
        }
        if (chk) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
