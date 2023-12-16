package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14499번: 주사위 굴리기
public class 주사위굴리기 {

    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0, 0, 0, -1, 1}; // 동, 서, 북, 남
    static int[] dy = {0, 1, -1, 0, 0};

    public static void rollDice(int k) {
        int[] copy = dice.clone();
        if (k == 1) { // 동
            dice[1] = copy[3];
            dice[3] = copy[6];
            dice[4] = copy[1];
            dice[6] = copy[4];
        } else if (k == 2) { // 서
            dice[1] = copy[4];
            dice[3] = copy[1];
            dice[4] = copy[6];
            dice[6] = copy[3];
        } else if (k == 3) { // 북
            dice[1] = copy[2];
            dice[2] = copy[6];
            dice[5] = copy[1];
            dice[6] = copy[5];
        } else { // 남
            dice[1] = copy[5];
            dice[2] = copy[1];
            dice[5] = copy[6];
            dice[6] = copy[2];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken()); // 1 -> 동, 2 -> 서, 3 -> 북, 4 -> 남

            // 주사위 이동
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 범위 벗어나는지 체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            // 주사위 돌리기 & 주사위 윗면 출력
            rollDice(k);
            sb.append(dice[6]).append("\n");

            // 바닥면 - 주사위 숫자 복사
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[1];
            } else {
                dice[1] = map[nx][ny];
                map[nx][ny] = 0;
            }

            //
            x = nx;
            y = ny;
        }

        System.out.println(sb);
    }
}
