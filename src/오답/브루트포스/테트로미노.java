package 오답.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14500번: 테트로미노
public class 테트로미노 {

    /*
    * 테트로미노? 4개의 칸이 연결만 되어 있으면 됨!!
    * 하지만 "ㅗ" 모양은 예외임! 따로 처리해 줘야 함!
    * 1. N x M 모두 돌면서 해당 칸에서 시작하는 테트로미노 구하기
    * 2. answer 업데이트
    * */

    static int N, M;
    static int[][] map;
    static int answer = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void dfs(int L, int x, int y, int sum) {
        if (L == 4) {
            answer = Math.max(answer, sum);
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                dfs(L + 1, nx, ny, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(1, i, j, map[i][j]);
                visited[i][j] = false;
                checkO(i, j);
            }
        }
        System.out.println(answer);
    }

    public static void checkO(int x, int y) {
        int value;
        // 1. ㅜ
        if (y - 1 >= 0 && y + 1 < M && x + 1 < N) {
            value = map[x][y - 1] + map[x][y] + map[x][y + 1] + map[x + 1][y];
            answer = Math.max(value, answer);
        }

        // 2. ㅓ
        if (y - 1 >= 0 && x - 1 >= 0 && x + 1 < N) {
            value = map[x][y - 1] + map[x][y] + map[x - 1][y] + map[x + 1][y];
            answer = Math.max(value, answer);
        }

        // 3. ㅗ
        if (y - 1 >= 0 && y + 1 < M && x - 1 >= 0) {
            value = map[x][y - 1] + map[x][y] + map[x][y + 1] + map[x - 1][y];
            answer = Math.max(value, answer);
        }

        // 4. ㅏ
        if (x - 1 >= 0 && x + 1 < N && y + 1 < M) {
            value = map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y + 1];
            answer = Math.max(value, answer);
        }
    }
}
