package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16928번: 뱀과 사다리 게임
public class 뱀과사다리게임 {

    static int N;
    static int M;
    static int start = 1, end = 100;
    static boolean[] visited;
    static Map<Integer, Integer> map;
    static int result = 0;

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            result++;
            for (int i = 0, qSize = q.size(); i < qSize; i++) { // 현재 큐의 사이즈만큼만 반복
                int now = q.poll();

                for (int j = 1; j <= 6; j++) {
                    int move = now + j;
                    if (move == end) return; // 끝났음!
                    if (move > end) continue; // 범위를 벗어남
                    if (visited[move]) continue; // 이미 방문했음

                    visited[move] = true;
                    if (map.containsKey(move)) {
                        move = map.get(move);
                    }
                    q.add(move);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사다리 수
        M = Integer.parseInt(st.nextToken()); // 뱀 수

        visited = new boolean[101];
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 사다리 시작
            int y = Integer.parseInt(st.nextToken()); // 사다리 끝
            map.put(x, y);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 뱀 시작
            int v = Integer.parseInt(st.nextToken()); // 뱀 끝
            map.put(u, v);
        }
        bfs();
        System.out.println(result);
    }
}