package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1043번: 거짓말
public class BOJ_1043_2 {

    static int N, M;
    static boolean[] truth;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        truth = new boolean[N + 1];

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        ArrayList<Integer>[] peoples = new ArrayList[M];
        for(int i = 0; i < M; i++) {
            peoples[i] = new ArrayList<>();
        }
        int value, pre = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n > 0) {
                pre = Integer.parseInt(st.nextToken());
                peoples[i].add(pre);
            }
            for (int j = 1; j < n; j++) {
                value = Integer.parseInt(st.nextToken());
                peoples[i].add(value);
                union(pre, value);
                pre = value;
            }
        }

        int parent;
        for (int i = 1; i < truth.length; i++) {
            if (truth[i]) truth[find(i)] = true;
        }

        int total = 0;
        for (int i = 0; i < M; i++) {
            if (peoples[i].size() > 0) {
                parent = find(peoples[i].get(0));
                if (!truth[parent]) total++;
            }
        }
        System.out.println(total);
    }

    public static int find(int x) {
        if(parent[x] == x)
            return parent[x] = x;
        else return find(parent[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            if(a > b) {
                parent[a] = b;
            } else {
                parent[b] = a;
            }
            return true;
        }
        return false;
    }

}
