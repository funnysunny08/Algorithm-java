package 기출.카카오;

import java.util.LinkedList;
import java.util.Queue;

// 카카오: 카드 짝 맞추기
public class 카드짝맞추기 {
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[] isNum = new boolean[7];
    static boolean[] visitedPermutation = new boolean[7];
    static int size = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0 || isNum[board[i][j]]) continue;
                isNum[board[i][j]] = true;
                size++;
            }
        }
        map = board;
        permutation(0, r, c, new int[size]);
        return answer;
    }

    public static void bfs(int[] arr, int sr, int sc) {
        Queue<Point> q = new LinkedList<>();
        // 최단거리 방문 배열
        boolean[][] visited = new boolean[4][4];
        // 현재 말판을 엔터 쳤는지 체크하는 배열
        boolean[][] mapVisited = new boolean[4][4];
        //최단 거리
        int cnt = 0;
        int answerCnt = 0;
        int idx = 0;
        boolean isSecond = false;

        q.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Point now = q.poll();

                if (map[now.x][now.y] == arr[idx] && !mapVisited[now.x][now.y]) {
                    // Enter 누적시켜주기
                    answerCnt++;
                    // 지금까지 최소 이동회수 더해주기
                    answerCnt += cnt;
                    // 이동회수 초기화 -1인 이유는 밑에서 바로 +1을 해주면 0이 되기 때문
                    cnt = -1;
                    //이미 방문한 말판임을 체크하기
                    mapVisited[now.x][now.y] = true;
                    // 큐 및 visited 비워주기(다시 최단거리로 이동하기 위해)
                    q.clear();
                    visited = new boolean[4][4];
                    // 초기 시작점 현재 좌표로 설정
                    q.add(new Point(now.x, now.y));
                    visited[now.x][now.y] = true;

                    if (!isSecond) { // 만약 처음 도착한거라면
                        isSecond = true; // 다음엔 두번째임을 체크하기 위함
                    } else {
                        isSecond = false; // 다음엔 새로운 말판 찾아라!
                        idx++; // target 말 변경
                        // index가 길이보다 길거나 같다 => 즉 모두 다 찾았다.
                        if (idx >= arr.length) {
                            //현재까지 누적한 값의 최소값 찾아줌
                            answer = Math.min(answer, answerCnt);
                            return;
                        }
                    }
                    break; // 찾았으면 그만 탐색
                }
                // 한 칸 움직이기
                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    if (!isIn(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }

                // ctrl 움직이기
                for (int j = 0; j < 4; j++) {
                    int nx = now.x;
                    int ny = now.y;

                    while (isIn(nx + dx[j], ny + dy[j])) { // 범위 끝이면 종료
                        nx += dx[j];
                        ny += dy[j];
                        // 만약 엔터치지않은 말이 존재하면 거기에 멈춰짐
                        if(!mapVisited[nx][ny] && map[nx][ny] != 0) break;
                    }
                    if (!isIn(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            cnt++;
        }
    }

    public static boolean isIn (int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }

    public static void permutation(int depth, int r, int c, int[] arr) {
        if (depth == size) {
            bfs(arr, r, c);
            return;
        }
        for (int i = 1; i <= 6; i++) {
            if (visitedPermutation[i] || !isNum[i]) continue;
            visitedPermutation[i] = true;
            arr[depth] = i;
            permutation(depth + 1, r, c, arr);
            visitedPermutation[i] = false;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}};
        System.out.println(solution(board, 1, 0));
    }
}
