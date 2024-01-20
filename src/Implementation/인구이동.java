package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 16234번: 인구 이동
public class 인구이동 {

    static int N, L, R;
    static int[][] map;
    static int[][] round;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};

    public static boolean checkBorder() {
        round = new int[N][N];
        int cnt = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (round[i][j] != 0) continue; // 방문한 적 있음 (= 갱신된 적 있음)
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j});
                round[i][j] = cnt;

                while (!q.isEmpty()) {
                    int[] curr = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || round[nx][ny] != 0) continue;
                        int diff = Math.abs(map[nx][ny] - map[curr[0]][curr[1]]);
                        if (diff >= L && diff <= R) {
                            round[nx][ny] = cnt;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
                cnt++;
            }
        }
        return cnt != N * N + 1;
    }

    public static void makeMove() {
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                Queue<int[]> q = new LinkedList<>(); // 국경 탐색용
                Stack<int[]> stack = new Stack<>(); // 인구 수 바꿔주기용

                visited[i][j] = true;
                int sum = map[i][j];
                q.add(new int[]{i, j});
                stack.push(new int[]{i, j});

                while (!q.isEmpty()) {
                    int[] curr = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || round[nx][ny] != round[i][j] || visited[nx][ny]) continue;
                        visited[nx][ny] = true;
                        sum += map[nx][ny];
                        q.add(new int[]{nx, ny});
                        stack.add(new int[]{nx, ny});
                    }
                }
                // 인구수 바꿔주기!!!
                int people = sum / stack.size();
                while (!stack.isEmpty()) {
                    int[] curr = stack.pop();
                    map[curr[0]][curr[1]] = people;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (checkBorder()) {
            answer++;
            makeMove();
        }
        System.out.println(answer);
    }
}
