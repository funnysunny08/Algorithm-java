package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 15685. 드래곤 커브
public class BOJ_15685 {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map = new int[101][101];

    private static List<Integer> getRoute(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        for (int i = 1; i <= g; i++) {
            List<Integer> newList = new ArrayList<>();
            for (int k = directions.size() - 1; k >= 0; k--) newList.add((directions.get(k) + 1) % 4);
            directions.addAll(newList);
        }
        return directions;
    }

    private static void fillMap(int x, int y, List<Integer> route) {
        map[x][y] = 1;
        for (int d : route) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            map[nx][ny] = 1;
            x = nx;
            y = ny;
        }
    }

    private static boolean checkSquare(int x, int y) {
        if (x + 1 > 100 || y + 1 > 100) return false;
        return map[x + 1][y] == 1 && map[x][y + 1] == 1 && map[x + 1][y + 1] == 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            fillMap(x, y, getRoute(d, g));
        }

        int cnt = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (map[i][j] == 0) continue;
                if (checkSquare(i, j)) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
