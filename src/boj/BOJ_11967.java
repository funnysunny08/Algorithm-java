package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 11967번: 불켜기
public class BOJ_11967 {

    static int N, M;
    static boolean[][] map;
    static List<int[]>[][] switches;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int bfs() {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        for (int i = 1; i <= N; i++){
            Arrays.fill(visited[i], false);
        }

        map[1][1] = true; // (1, 1)의 불을 킨다.
        visited[1][1] = true;

        boolean chk = false;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 1. 현재의 방에서 킬 수 있는 방의 불을 다 킨다.
            if (switches[curr[0]][curr[1]] != null) {
                for (int[] room : switches[curr[0]][curr[1]]) {
                    if (!map[room[0]][room[1]]) {
                        map[room[0]][room[1]] = true;
                        answer++;
                        chk = true;
                    }
                }
            }

            // 2. 4면중 이동 가능한 방으로 이동한다.
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (canGo(nx, ny)) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        // 불 하나라도 켰으면 0,0 부터 BFS 탐색. 이미 지나온 방 중 이어질 수 있으므로
        if (chk) answer += bfs();

        return answer;
    }

    public static boolean canGo(int x, int y) {
        // map 을 벗어나거나, 이미 방문했거나, 불이 꺼져있으면 false
        return !(x < 1 || y < 1 || x > N || y > N || visited[x][y] || !map[x][y]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        switches = new List[N + 1][N + 1];
        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (switches[x][y] == null) switches[x][y] = new ArrayList<>();
            switches[x][y].add(new int[]{a, b});
        }

        System.out.println(bfs() + 1); // (1, 1) 켜진 거 포함!
    }
}
