package 오답.kakao;

import java.util.LinkedList;
import java.util.Queue;

public class 카드짝맞추기_2 {

    static int[][] map;
    static int[] sequence;
    static int answer = Integer.MAX_VALUE;
    static int R, C;
    static int N;
    static boolean[][] mapVisited, visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int solution(int[][] board, int r, int c) {
        map = board;
        R = r;
        C = c;
        N = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                N = Math.max(N, map[i][j]);
            }
        }
        sequence = new int[N];
        makeSequence(0, new boolean[N + 1]);
        return answer;
    }

    public static int getResult() {
        int time = 0;
        mapVisited = new boolean[4][4];
        visited = new boolean[4][4];
        int index = 0;
        int target = sequence[index++];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R, C, 0));
        visited[R][C] = true;
        boolean isSecond =false;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (map[now.x][now.y] == target && !mapVisited[now.x][now.y]) {
                time += now.time + 1;
                mapVisited[now.x][now.y] = true;

                if (isSecond) {
                    if (index == sequence.length) return time;
                    target = sequence[index++];
                    isSecond = false;
                } else {
                    isSecond = true;
                }
                q.clear();
                visited = new boolean[4][4];
                q.offer(new Node(now.x, now.y, 0));
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if (!isIn(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.time + 1));
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x;
                    int ny = now.y;

                    while (isIn(nx + dx[i], ny + dy[i])) {
                        nx += dx[i];
                        ny += dy[i];
                        if (!mapVisited[nx][ny] && map[nx][ny] != 0) break;
                    }
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny, now.time + 1));
                    }
                }
            }
        }
        return 0;
    }

    public static void makeSequence(int depth, boolean[] visited) {
        if (depth == N) {
            answer = Math.min(answer, getResult());
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                sequence[depth] = i;
                visited[i] = true;
                makeSequence(depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    static boolean isIn(int x, int y) {
        return !(x < 0 || x >= 4 || y < 0 || y >= 4);
    }

    public static void main(String[] args) {
        int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
//        int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
        System.out.println(solution(board, 1, 0));
    }
}
