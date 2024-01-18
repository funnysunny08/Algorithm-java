package Backtracking2;

// 프로그래머스: 아이템 줍기
public class 아이템줍기 {

    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int[] dx = {-1, 1, 0 , 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public static void dfs(int x, int y, int itemX, int itemY, int cost) {
        if (x == itemX && y == itemY) { // 목적지 도달 완료!
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 101 || ny >= 101) continue;
            if (!visited[nx][ny] && map[nx][ny] == 2) { // 방문한 적 없고, 테두리에 해당하는 칸이라면!
                visited[nx][ny] = true;
                dfs(nx, ny, itemX, itemY, cost + 1);
                visited[nx][ny] = false;
            }
        }
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1. map 에 rectangle 을 배치해 채운다.
        drawRectangle(rectangle);

        // 2.
        dfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, 0);

        // 3.
        return answer / 2;
    }

    public static void drawRectangle(int[][] rectangle) {
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (map[x][y] == 1) continue; // 새롭게 그려질 사각형에서는 테두리 노드라도, 이미 안쪽 노드로 정의된 노드는 계속 안쪽 노드!
                    map[x][y] = 1;
                    if (x == x1 || x == x2 || y == y1 || y == y2) // 테두리에 위치한 칸은 '2'로 채운다.
                        map[x][y] = 2;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int[][] rectangle = {{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
        int answer = solution(rectangle, 9, 7, 6, 1);
        System.out.println(answer);
    }
}
