package 오답.kakao;

import java.util.LinkedList;
import java.util.Queue;

// 카카오: 카드 짝 맞추기
public class 카드짝맞추기 {

    static int[][] map;
    static int R, C, N;
    static int[] sequence;
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visited;
    static boolean[][] mapVisited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int r, c, cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static int solution(int[][] board, int r, int c) {
        map = board;
        R = r;
        C = c;
        N = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                N = Math.max(N, board[i][j]);
            }
        }
        sequence = new int[N];
        dfs(0, new boolean[N + 1]);
        return answer;
    }

    public static int getAnswer() {
        int index = 0;
        int target = sequence[index++];
        int total = 0;
        boolean isSecond = false;

        visited = new boolean[4][4];
        mapVisited = new boolean[4][4];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R, C, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (map[now.r][now.c] == target && !mapVisited[now.r][now.c]) { // 발견!
                total += now.cost + 1;
                mapVisited[now.r][now.c] = true;

                if (isSecond) { // 짝꿍까지 찾았음! target 바꿔주기
                    if (index == sequence.length) return total;
                    target = sequence[index++];
                    isSecond = false;
                } else {
                    isSecond = true;
                }
                q.clear();
                visited = new boolean[4][4];
                q.offer(new Node(now.r, now.c, 0));
            } else {
                // 1. 일반 방향기
                for (int i = 0; i < 4; i++) {
                    int nx = now.r + dx[i];
                    int ny = now.c + dy[i];
                    if (!isIn(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.cost + 1));
                }

                // 2. ctrl 방향키
                for (int i = 0; i < 4; i++) {
                    int nx = now.r;
                    int ny = now.c;

                    while (isIn(nx + dx[i], ny + dy[i])) { // 범위 끝이면 종료
                        nx += dx[i];
                        ny += dy[i];
                        if (!mapVisited[nx][ny] && map[nx][ny] != 0) break;
                    }
                    if (!isIn(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.cost + 1));
                }
            }
        }
        return 0;
    }

    public static boolean isIn(int r, int c) {
        return !(r < 0 || c < 0 || r >= 4 || c >= 4);
    }


    public static void dfs(int depth, boolean[] visited) {
        if (depth == N) {
            answer = Math.min(answer, getAnswer());
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                sequence[depth] = i;
                visited[i] = true;
                dfs(depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
//        int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
        System.out.println(solution(board, 1, 0));
    }
}
