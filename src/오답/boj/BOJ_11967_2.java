package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 11967번: 불 켜기
public class BOJ_11967_2 {

    static int N, M;
    static boolean[][] map;
    static List<int[]>[][] info;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        visited = new boolean[N +1][N + 1];
        visited[1][1] = true;

        int newLight = 0;
        boolean hasNewLight = false;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            if (info[curr[0]][curr[1]] != null) {
                for (int[] room : info[curr[0]][curr[1]]) {
                    if (!map[room[0]][room[1]]) {
                        map[room[0]][room[1]] = true;
                        newLight++;
                        hasNewLight = true;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > N || visited[nx][ny] || !map[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        if (hasNewLight) newLight += bfs();
        return newLight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new List[N + 1][N + 1];
        map = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (info[x][y] == null) info[x][y] = new ArrayList<>();
            info[x][y].add(new int[]{a, b});
        }

        map[1][1] = true;
        int answer = bfs() + 1;
        System.out.println(answer);
    }
}
