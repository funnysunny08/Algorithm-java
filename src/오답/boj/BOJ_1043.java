package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 1043번: 거짓말
public class BOJ_1043 {

    static int N, M;
    static int[] parent;
    static boolean[] truth;
    static List<Integer>[] party;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        truth = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int people = Integer.parseInt(st.nextToken());
        for (int i = 0; i < people; i++) {
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        party = new List[M];
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n > 0) {
                int prev = Integer.parseInt(st.nextToken());
                party[i].add(prev);
                int next;
                for (int j = 1; j < n; j++) {
                    next = Integer.parseInt(st.nextToken());
                    union(prev, next);
                    party[i].add(next);
                    prev = next;
                }
            }
        }

        for (int i = 1; i < truth.length; i++) {
            if (truth[i]) truth[find(i)] = true;
        }

        int total = 0;
        for (int i = 0; i < M; i++) {
            if (party[i].size() > 0 && !truth[find(party[i].get(0))]) total++;
        }
        System.out.println(total);
    }

    public static int find(int x) {
        if (x == parent[x]) return parent[x] = x;
        else return find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else if (a < b) {
            parent[b] = a;
        }
    }
}
