package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 21608번: 상어 초등학교
public class BOJ_21608 {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashMap<Integer, Set<Integer>> hm = new HashMap<>();
    static class Info{
        int x, y, friends, blanks;

        public Info(int x, int y, int friends, int blanks) {
            this.x = x;
            this.y = y;
            this.friends = friends;
            this.blanks = blanks;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            hm.put(num, new HashSet<>());
            for (int j = 0; j < 4; j++) hm.get(num).add(Integer.parseInt(st.nextToken()));

            PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.friends != o2.friends) return o2.friends - o1.friends;
                else if (o1.blanks != o2.blanks) return o2.blanks - o1.blanks;
                else if (o1.x != o2.x) return o1.x - o2.x;
                else return o1.y - o2.y;
            });

            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c] != 0) continue;
                    int b = 0;
                    int f = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = r + dx[d];
                        int ny = c + dy[d];
                        if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                        if (map[nx][ny] == 0) b++;
                        else if (hm.get(num).contains(map[nx][ny])) f++;
                    }
                    pq.offer(new Info(r, c, f, b));
                }
            }
            Info info = pq.poll();
            map[info.x][info.y] = num;
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                    if (hm.get(map[i][j]).contains(map[nx][ny])) cnt++;
                }
                if (cnt == 4) answer += 1000;
                else if (cnt == 3) answer += 100;
                else if (cnt == 2) answer += 10;
                else if (cnt == 1) answer += 1;
            }
        }
        System.out.println(answer);

    }

}
