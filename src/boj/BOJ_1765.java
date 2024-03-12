package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1765번: 닭싸움 팀 정하기
public class BOJ_1765 {

    static int N, M;
    static boolean[][] friend;
    static List<List<Integer>> enemy = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        friend = new boolean[N + 1][N + 1];
        for (int i = 0; i <= N; i++) enemy.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (Objects.equals(type, "E")) {
                enemy.get(p).add(q);
                enemy.get(q).add(p);
            } else {
                friend[p][q] = true;
                friend[q][p] = true;
            }
        }

        makeFriend();
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                cnt++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int now = q.poll();
                    for (int j = 1; j <= N; j++) {
                        if (!visited[j] && friend[now][j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
            }
        }
        System.out.println(cnt);

    }

    public static void makeFriend() {
        for (int i = 1; i <= N; i++) {
            if (enemy.get(i).size() == 0) continue;
            for (int e : enemy.get(i)) {
                for (int f : enemy.get(e)) {
                    friend[i][f] = true;
                    friend[f][i] = true;
                }
            }
        }
    }

}
