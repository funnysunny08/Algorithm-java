package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 11967번: 불켜기
public class BOJ_11967 {

    static int N, M;
    static boolean[][] map;
    static List<int[]>[][] switches;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int bfs() {
        map[1][1] = true; // (1, 1) 불켜기
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        visited[1][1] = true;
        int answer = 0;

        boolean chk = false;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 1. 현재의 방에서 킬 수 있는 불 다 켜기!
            if (switches[curr[0]][curr[1]] != null) {
                for (int[] room : switches[curr[0]][curr[1]]) {
                    if (!map[room[0]][room[1]]) { // 새롭게 불을 키는 방!
                        answer++;
                        map[room[0]][room[1]] = true;
                        chk = true;
                    }
                }
            }

            // 2. 이동
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > N || !map[nx][ny] || visited[nx][ny]) continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if (chk) answer += bfs();
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1][N + 1];
        switches = new List[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (switches[x][y] == null) switches[x][y] = new ArrayList<>();
            switches[x][y].add(new int[]{a, b});
        }

        System.out.println(bfs() + 1);

    }
}
