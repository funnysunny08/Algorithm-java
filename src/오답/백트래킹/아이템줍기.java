package 오답.백트래킹;

// 프로그래머스: 아이템 줍기
public class 아이템줍기 {

    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1. map 에 rectangle 을 채워넣는다!
        makeFill(rectangle);
        // 2. 테두리를 따라가며 탐색한다!
        dfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, 0);
        return answer;
    }

    public static void dfs(int x, int y, int itemX, int itemY, int count) {
        if (x == itemX && y == itemY) {
            answer = Math.min(answer, count / 2);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 101 || ny >= 101 || map[nx][ny] != 2 || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, itemX, itemY, count + 1);
            visited[nx][ny] = false;
        }
    }

    public static void makeFill(int[][] rectangle) {
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (map[x][y] == 1) continue; // 한 번 "1"이 된 부분은 바뀌지 않는다.
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        map[x][y] = 2;
                    } else {
                        map[x][y] = 1;
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int a = solution(rectangle, 1, 3, 7, 8);
        System.out.println(a);
    }
}
